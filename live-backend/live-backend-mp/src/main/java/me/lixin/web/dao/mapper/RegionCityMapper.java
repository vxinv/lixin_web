package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.RegionCityDBO;

public interface RegionCityMapper {
    int deleteByPrimaryKey(String id);

    int insert(RegionCityDBO record);

    int insertSelective(RegionCityDBO record);
    int updateByPrimaryKeySelective(RegionCityDBO record);

    int updateByPrimaryKey(RegionCityDBO record);
}