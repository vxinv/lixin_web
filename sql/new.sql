
#群聊的主要内容
drop table if exists `group_chat`;
create table `group_chat` (
    `gc_id` bigint(20) primary key auto_increment comment '群聊id',
    `gc_url` varchar(126) comment '二维码图片url',
    `gc_encode_char` varchar(256) comment '二维码的解析字段',
    `gc_update_time` datetime comment '群聊二维码更新时间',
    `gc_user_id` int comment '用户id-关联用户的id',
    `gc_topic_id` int comment '主题id-关联主题',
    `gc_local_id` int comment '地域id-关联地域',
    #群聊内容
    `gc_content_title` varchar(126) comment '发布群聊的内容标题',
    `gc_content_text` varchar(256) comment '发布群聊的内容',
    `gc_content_image_url` varchar(512) comment '发布内容的图片路径',
    #附加内容
    `gc_like` int comment '发布的点赞数量',
    `gc_view` int comment '发布的浏览数量',
    `gc_discuss` int comment '发布的评论数量',

    `create_time` datetime default now() comment '创建时间',
    `update_time` datetime default now() on update now() comment  '更新时间'

);





BEGIN ;
insert group_chat ( gc_url, gc_encode_char, gc_update_time, gc_user_id, gc_topic_id, gc_local_id)  values
('gc_url','gc_encode_char','2022-07-08 19:22:22',100,101,102);
COMMIT;


create user 'lixin'@'%' identified with mysql_native_password BY 'lixin';
grant all privileges on *.* to 'lixin'@'%' with grant option;
flush privileges;
show grants for 'lixin'@'%' ;