package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.JoinedActivityDBO;

public interface JoinedActivityMapper {

    int insertSelective(JoinedActivityDBO record);

    int updateByPrimaryKeySelective(JoinedActivityDBO record);

    void delete(JoinedActivityDBO dbo);

}