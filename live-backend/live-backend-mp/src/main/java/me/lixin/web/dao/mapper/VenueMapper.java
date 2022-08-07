package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.VenueDBO;

public interface VenueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VenueDBO record);

    int insertSelective(VenueDBO record);
    int updateByPrimaryKeySelective(VenueDBO record);

    int updateByPrimaryKey(VenueDBO record);
}