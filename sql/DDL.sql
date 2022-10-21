create table t_user
(
    id         int auto_increment,
    parent_uin int           not null comment '主账号UIN',
    real_name  varchar(64)   not null comment '主帐号，用户真实姓名',
    user_name  varchar(64)   null comment '用户姓名',
    avatar     varchar(1024) null,
    email      varchar(64)   null,
    type       char(2)       null comment '用户类型 1为主账号 2为子账号',
    user_code  varchar(64)   not null comment '用户唯一标识',
    user_id    varchar(64)   null comment 'apaas平台用户id',
    sub_type   varchar(64)   null comment '调用方服务简称（上架时候填写的服务简称）',
    company    varchar(64)   null comment '集团',
    `admin`    boolean default false comment '是否是管理员',
    balance    double  default 0 comment '用户余额',
    constraint primary_id primary key (id)
)
    comment '用户表';