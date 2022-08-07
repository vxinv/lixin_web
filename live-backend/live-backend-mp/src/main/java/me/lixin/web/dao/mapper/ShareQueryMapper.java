package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.ShareDBO;

public interface ShareQueryMapper {
    ShareDBO selectByPrimaryKey(Long id);
}