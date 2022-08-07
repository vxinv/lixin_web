package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.RepostDBO;

public interface RepostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RepostDBO record);

    int insertSelective(RepostDBO record);
    int updateByPrimaryKeySelective(RepostDBO record);

    int updateByPrimaryKey(RepostDBO record);
}