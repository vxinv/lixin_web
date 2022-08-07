package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.RegionCityDBO;

import java.util.List;

public interface RegionCityQueryMapper {
    RegionCityDBO selectByPrimaryKey(String id);

    List<RegionCityDBO> queryAll();

}