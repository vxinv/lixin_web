package me.lixin.web.dao.mapper_group;

import me.lixin.web.model_group.GroupChat;

public interface GroupChatMapper {
    int deleteByPrimaryKey(Long gcId);

    int insert(GroupChat record);

    int insertSelective(GroupChat record);

    GroupChat selectByPrimaryKey(Long gcId);

    int updateByPrimaryKeySelective(GroupChat record);

    int updateByPrimaryKey(GroupChat record);
}