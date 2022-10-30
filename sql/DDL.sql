create table payment_order_details
(
    id            int auto_increment comment '主键
'
        primary key,
    user_id       int          not null comment '用户id
',
    order_id      varchar(32)  not null comment '订单号',
    payment_id    varchar(64)  null comment '商户订单号',
    notify_url    varchar(255) null comment '内部服务支付成功回调地址',
    order_status  int          null comment '订单状态 -1未处理 2成功 3失败',
    pay_amount    int(255)     null comment '订单金额单位分',
    order_subject varchar(255) null comment '商品标题',
    actual_amount int(255)     null comment '用户实付金额单位分',
    create_time   timestamp    null comment '创建时间',
    update_time   timestamp    null comment '更新时间',
    constraint index_order_id
        unique (order_id),
    constraint index_payment_id
        unique (payment_id)
);

create index index_create_time
    on payment_order_details (create_time);

create index index_user_id
    on payment_order_details (user_id);

create table t_deposit_withdrawal
(
    id          int auto_increment
        primary key,
    code        varchar(64) not null comment '充值/提现单号',
    opt_time    timestamp   null comment '充值/提现时间',
    type        varchar(64) null comment '类型',
    amount      double      null comment '交易金额',
    verify_time timestamp   null comment '到帐时间',
    account     varchar(64) null comment '充值帐号',
    mode        varchar(64) null comment '交易方式',
    status      varchar(64) null comment '交易状态',
    opt         varchar(64) null comment '状态',
    create_time timestamp   null comment '时间'
)
    comment '充值/提现状态表';

create table t_order
(
    id               int auto_increment comment '主键'
        primary key,
    user_id          varchar(32)  null comment '用户id',
    title            varchar(64)  null comment '工具名

',
    sub_type         varchar(8)   null comment '工具类型',
    use_time         varchar(8)   null comment '使用时间',
    specs            varchar(255) null comment '描述',
    price            varchar(8)   null comment '单价',
    total_price      varchar(16)  null comment '原总价',
    total_amount     varchar(16)  null comment '应付金额',
    create_time      timestamp    null comment '创建时间',
    order_id         varchar(32)  null comment '20位随机订单号',
    order_status     int          null comment '订单状态  -1 未支付 1支付中  2成功 3失败',
    item_id          varchar(32)  null comment '工具/套餐id',
    pay_order_id     varchar(32)  null comment '支付订单号',
    payment_order_id varchar(32)  null comment '商户订单号',
    actual_amount    varchar(16)  null comment '实际支付金额',
    order_type       int          null comment '订单类型 0工具 1套餐',
    constraint orderid
        unique (order_id)
);

create index pay_order_id
    on t_order (pay_order_id);

create index userid
    on t_order (user_id);

create table t_suit
(
    id         int auto_increment
        primary key,
    name       varchar(255) not null,
    suit_type  int          not null,
    cost_type  int          not null,
    price      double       not null,
    status     int          not null,
    create_uid varchar(255) not null,
    icon       varchar(255) null
);

create table t_tool
(
    id             int          not null
        primary key,
    title          varchar(255) null,
    sub_type       varchar(255) null,
    cover_addr     varchar(255) null,
    classify_id    int          null,
    remarks_title  varchar(255) null,
    price          double       null,
    remarks        varchar(255) null,
    type           int          null,
    open           int          null,
    open_access    int          null,
    frame          int          null,
    system_type    varchar(255) null,
    status         varchar(255) null,
    auth_status    varchar(255) null,
    hide           varchar(255) null,
    create_time    bigint       null,
    up_time        bigint       null,
    tool_url       varchar(255) null,
    tool_api       varchar(255) null,
    access_id      varchar(255) null,
    access_key     varchar(255) null,
    tool_accessid  varchar(255) null,
    tool_accesskey varchar(255) null,
    parent_uin     int          null,
    user_id        varchar(255) null,
    back_info      varchar(255) null,
    private        int          null,
    button         varchar(255) null,
    input_num      varchar(255) null,
    output_num     varchar(255) null,
    cost_type      int          null,
    spce_money     varchar(255) null
);

create table t_transaction_record
(
    id          int auto_increment
        primary key,
    code        varchar(64)                         not null comment '交易编号',
    type        char(2)                             null comment '收支类型(支出:1，收入：2，充值：3，提现：4)',
    channel     varchar(64)                         null comment '交易渠道',
    amount      double                              null comment '交易金额',
    create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '交易时间'
)
    comment '交易记录表';

create table t_user
(
    id          int auto_increment
        primary key,
    parent_uin  int                                  not null comment '主账号UIN',
    real_name   varchar(64)                          not null comment '主帐号，用户真实姓名',
    user_name   varchar(64)                          null comment '用户姓名',
    avatar      varchar(1024)                        null,
    email       varchar(64)                          null,
    type        char(2)                              null comment '用户类型 1为主账号 2为子账号',
    user_code   varchar(64)                          not null comment '用户唯一标识',
    user_id     varchar(64)                          null comment 'apaas平台用户id',
    sub_type    varchar(64)                          null comment '调用方服务简称（上架时候填写的服务简称）',
    company     varchar(64)                          null comment '集团',
    admin       tinyint(1) default 0                 null comment '是否是管理员',
    balance     double     default 0                 null comment '用户余额',
    create_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '用户创建时间'
)
    comment '用户表';

