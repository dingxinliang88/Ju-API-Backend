-- 创建库
create database if not exists api_platform;

-- 切换库
use api_platform;

-- 接口信息表
-- 接口信息表
create table if not exists api_platform.`interface_info`
(
    `id`             bigint                               not null auto_increment comment '主键' primary key,
    `name`           varchar(256)                         not null comment '接口名',
    `description`    varchar(256)                         null comment '接口描述',
    `url`            varchar(512)                         not null comment '接口地址',
    `requestHeader`  text                                 null comment '请求头',
    `responseHeader` varchar(256)                         null comment '响应头',
    `status`         int      default 0                   not null comment '接口状态（0 - 关闭， 1 - 开启）',
    `method`         varchar(256)                         not null comment '请求类型',
    `userId`         bigint                               not null comment '创建人',
    `createTime`     datetime default 'CURRENT_TIMESTAMP' not null comment '创建时间',
    `updateTime`     datetime default 'CURRENT_TIMESTAMP' not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted`      tinyint  default 0                   not null comment '是否删除(0-未删, 1-已删)',
    `requestParam`   text                                 not null comment '请求参数'
) comment '接口信息表';


-- 模拟数据
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('尹鹤轩', '杨黎昕', 'www.lorriane-shields.com', '沈志泽', '郑峻熙', 0, 'GET', 7528670535, '龚博超');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('段梓晨', '卢鹏飞', 'www.willie-reichert.name', '田天宇', '陈浩', 0, 'GET', 9217003, '范明哲');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('张晋鹏', '何伟宸', 'www.roosevelt-marquardt.name', '陶立果', '张思源', 0, 'GET', 197658975, '邱鹏煊');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('谢金鑫', '雷潇然', 'www.tracy-parisian.co', '姜展鹏', '马博文', 0, 'GET', 79544, '沈泽洋');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('万绍齐', '韩子骞', 'www.lane-hyatt.name', '龙伟泽', '史钰轩', 0, 'GET', 24753789, '魏修杰');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('覃琪', '魏修杰', 'www.stephaine-konopelski.biz', '胡雨泽', '雷睿渊', 0, 'GET', 20, '苏志泽');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('叶晋鹏', '莫钰轩', 'www.heriberto-lemke.com', '苏峻熙', '许思聪', 0, 'GET', 9049376, '陆熠彤');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('吕彬', '余鹏煊', 'www.marian-schuppe.io', '杨梓晨', '萧鹏煊', 0, 'GET', 470330, '沈凯瑞');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('张鸿涛', '胡雨泽', 'www.fe-kassulke.name', '朱鹤轩', '黄健柏', 0, 'GET', 6290, '邵烨霖');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('钟天宇', '夏旭尧', 'www.almeta-okuneva.co', '段伟祺', '余明哲', 0, 'GET', 8802359, '何越彬');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('万煜祺', '姚越彬', 'www.ivory-stracke.biz', '谭健柏', '蔡黎昕', 0, 'GET', 3357524462, '贺俊驰');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('王志泽', '熊弘文', 'www.bertie-keeling.org', '程思远', '秦嘉熙', 0, 'GET', 867965076, '程明轩');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('任子骞', '尹浩宇', 'www.keith-fay.com', '洪煜祺', '叶伟祺', 0, 'GET', 34388300, '曹思源');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('陈俊驰', '马弘文', 'www.rich-weimann.biz', '赵致远', '黎懿轩', 0, 'GET', 98900569, '戴黎昕');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('江天磊', '汪旭尧', 'www.earl-larkin.com', '洪瑾瑜', '孙语堂', 0, 'GET', 992135145, '田钰轩');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('许志泽', '张弘文', 'www.abe-quigley.biz', '戴鹭洋', '萧潇然', 0, 'GET', 292, '卢晓博');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('秦果', '余鹤轩', 'www.graciela-dickens.info', '萧鸿煊', '马雪松', 0, 'GET', 196514, '江鸿煊');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('邱擎宇', '蔡航', 'www.chase-harris.info', '阎鸿煊', '萧航', 0, 'GET', 9237206, '萧睿渊');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('方哲瀚', '严炫明', 'www.lela-stoltenberg.biz', '熊文博', '沈雨泽', 0, 'GET', 2880150980, '邹锦程');
insert into api_platform.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`,
                                           `method`, `userId`, `requestParam`)
values ('李子涵', '潘伟祺', 'www.denny-cartwright.com', '刘语堂', '袁志强', 0, 'GET', 988784, '余明辉');

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户';



-- 用户调用接口关系表
create table if not exists api_platform.`user_interface_info`
(
    id              bigint auto_increment comment 'id' primary key,
    userId          bigint                             not null comment '调用用户id',
    interfaceInfoId bigint                             not null comment '接口id',
    totalNum        int      default 0                 not null comment '总调用次数',
    leftNum         int      default 0                 not null comment '剩余调用次数',
    status          int      default 0                 not null comment '0 - 正常，1 - 禁用',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint  default 0                 not null comment '是否删除'
) comment '用户调用接口关系表';