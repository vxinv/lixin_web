package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.VenueDBO;

public interface VenueQueryMapper {
    VenueDBO selectByPrimaryKey(Long id);
}