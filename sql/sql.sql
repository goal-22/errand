create schema errand collate utf8_general_ci;

create table t_goods
(
	id int auto_increment comment 'id'
		primary key,
	userId int not null comment '创建者',
	name varchar(50) not null comment '商品名称',
	detail text not null comment '商品详情',
	createTime datetime null comment '创建时间',
	price float not null comment '价格'
)
comment '商品表';

create table t_goods_img
(
	id int auto_increment comment 'id'
		primary key,
	goods_id int not null comment '商品id',
	path text not null comment '商品图片路径'
)
comment '商品图片';

create table t_notice
(
	id int auto_increment comment 'id'
		primary key,
	userId int not null comment 'userId',
	content text not null comment '公告内容'
)
comment '公告表';

create table t_order
(
	id int auto_increment comment 'id'
		primary key,
	goodsId int not null comment '商品id',
	createTime datetime null comment '创建时间',
	payTime datetime null comment '付款时间',
	finishTime datetime null comment '完成时间',
	status int default 0 not null comment '订单状态 0:进行中 1:已完成'
)
comment '订单表';

create table t_user
(
	id int auto_increment comment 'id'
		primary key,
	username varchar(50) not null comment '用户名',
	password varchar(50) not null comment '密码'
)
comment '用户表';

create table t_user_info
(
	id int auto_increment comment 'id'
		primary key,
	userId int not null comment 'userId',
	name varchar(50) not null comment '姓名',
	sex varchar(2) null comment '性别',
	phone varchar(20) not null comment '电话',
	qq varchar(20) not null comment 'qq',
	mail varchar(50) null comment '邮箱',
	imgPath text null comment '头像图片'
)
comment '用户信息表';

