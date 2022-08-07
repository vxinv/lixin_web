package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.RegionProvinceDBO;

public interface RegionProvinceQueryMapper {
    RegionProvinceDBO selectByPrimaryKey(String id);
}