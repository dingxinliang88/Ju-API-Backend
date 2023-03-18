package com.juzi.juapigateway.filter;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.juzi.juapicommon.model.entity.InterfaceInfo;
import com.juzi.juapicommon.model.entity.User;
import com.juzi.juapicommon.service.InnerInterfaceInfoService;
import com.juzi.juapicommon.service.InnerUserInterfaceInfoService;
import com.juzi.juapicommon.service.InnerUserService;
import com.juzi.juziapiclientsdk.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.juzi.juapigateway.constant.ValidConstant.MAX_NONCE;
import static com.juzi.juapigateway.constant.ValidConstant.REQ_VALID_TIME_INTERVAL;

/**
 * 自定义全局过滤器
 *
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
@Slf4j(topic = "ju.api.gateway")
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    /**
     * 请求白名单
     */
    private static final List<String> IP_WHITE_LIST = Arrays.asList(
            "127.0.0.1",
            "127.0.0.2"
    );

    /**
     * 模拟接口主机名
     */
    private static final String INTERFACE_HOST = "localhost:8090";

    /**
     * 调用接口的用户
     */
    private User invokeUser;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.请求日志
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        String remoteAddress = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        String url = INTERFACE_HOST + request.getPath().value();
        String method = Objects.requireNonNull(request.getMethod()).toString();
        log.info("请求id: {}", request.getId());
        log.info("请求路径: {}", url);
        log.info("请求方法: {}", method);
        log.info("请求参数: {}", request.getQueryParams());
        log.info("请求头: {}", headers);
        log.info("请求来源: {}", remoteAddress);
        // 2.访问控制 —— 黑白名单
        if (!IP_WHITE_LIST.contains(remoteAddress)) {
            return handleNoAuth(response);
        }
        // 3.用户鉴权（判断ak、sk是否合法）
        boolean validRequest = checkRequestValid(headers);
        if (!validRequest) {
            return handleInvokeError(response);
        }
        // 4.请求的模拟接口是否存在 RPC远程调用
        InterfaceInfo invokeInterfaceInfo = null;
        try {
            invokeInterfaceInfo = innerInterfaceInfoService.getInvokeInterfaceInfo(url, method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(invokeInterfaceInfo == null) {
            return handleInvokeError(response);
        }
        // 判断是否还剩余调用次数
        boolean hasInvokeCount = false;
        try {
            hasInvokeCount = innerUserInterfaceInfoService.hasInvokeCount(invokeUser.getId(), invokeInterfaceInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!hasInvokeCount) {
         return handleInvokeError(response);
        }
        // 5.请求转发，调用模拟接口
        return handleResponse(exchange, chain, invokeUser.getId(), invokeInterfaceInfo.getId());
    }

    /**
     * 处理没有权限的请求
     *
     * @param response 响应结果
     * @return Mono
     */
    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    /**
     * 处理调用错误的请求
     *
     * @param response 响应结果
     * @return Mono
     */
    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    /**
     * 用户请求鉴权
     *
     * @param headers  请求头信息
     * @return true - 合法， false - 不合法
     */
    private boolean checkRequestValid(HttpHeaders headers) {
        String accessKey = headers.getFirst("accessKey");
        String body = null;
        try {
            // 防止中文乱码
            body = URLDecoder.decode(Optional.ofNullable(headers.getFirst("body")).orElse(""), "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder.decode() error");
            e.printStackTrace();
        }
        String sign = headers.getFirst("sign");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判空
        if (hasBlank) {
            return false;
        }
        // 去数据库查询secretKey
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(invokeUser == null) {
            return false;
        }
        String secretKey = invokeUser.getSecretKey();
        String generateSign = SignUtil.generateSign(body, secretKey);
        if (!StrUtil.equals(sign, generateSign)) {
            return false;
        }
        // 判断随机数 nonce
        assert nonce != null;
        if(Long.parseLong(nonce) > MAX_NONCE) {
            return false;
        }
        // 时间戳是否是数字
        if (!NumberUtil.isNumber(timestamp)) {
            return false;
        }
        // 五分钟内请求有效
        return System.currentTimeMillis() - Long.parseLong(timestamp) <= REQ_VALID_TIME_INTERVAL;
    }


    /**
     * 处理响应
     *
     * @param exchange 交换机
     * @param chain    网关联路
     * @param userId invoke user id
     * @param interfaceInfoId  interface info id
     * @return Mono
     */
    private Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long userId, long interfaceInfoId) {
        try {
            // 原始response
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓冲区工厂，拿到缓冲数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 状态码
            HttpStatus statusCode = originalResponse.getStatusCode();

            // 6.响应日志
            log.info("响应状态码：{}", statusCode);
            if (statusCode == HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        // 对象是响应式的
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 7.调用成功，接口调用次数+1
                                try {
                                    innerUserInterfaceInfoService.invokeInterfaceCount(userId, interfaceInfoId);
                                } catch (Exception e) {
                                    log.error("invokeInterfaceCount() error", e);
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                // 释放掉内存
                                DataBufferUtils.release(dataBuffer);
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                sb2.append("<--- {} {} \n");
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);
                                sb2.append(data);
                                log.info(sb2.toString(), rspArgs.toArray());
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            // 8.调用失败，返回错误状态码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置response对象为装饰后的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            //降级处理返回数据
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
