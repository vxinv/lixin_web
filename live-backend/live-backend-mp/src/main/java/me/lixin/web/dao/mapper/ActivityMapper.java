package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.ActivityDBO;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityDBO record);

    int insertSelective(ActivityDBO record);
    int updateByPrimaryKeySelective(ActivityDBO record);

    int updateByPrimaryKey(ActivityDBO record);
}