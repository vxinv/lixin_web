package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.TagDBO;

public interface TagQueryMapper {
    TagDBO selectByPrimaryKey(Long id);
}