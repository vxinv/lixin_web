package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.LikeRelationDBO;

public interface LikeRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LikeRelationDBO record);

    int insertSelective(LikeRelationDBO record);

    int updateByPrimaryKeySelective(LikeRelationDBO record);

    int updateByPrimaryKey(LikeRelationDBO record);

    void delete(LikeRelationDBO likeRelationDBO);
}