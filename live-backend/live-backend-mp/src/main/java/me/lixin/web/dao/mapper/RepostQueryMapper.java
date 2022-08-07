package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.RepostDBO;

public interface RepostQueryMapper {
    RepostDBO selectByPrimaryKey(Long id);
}