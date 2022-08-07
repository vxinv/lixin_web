package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.TagRelationDBO;

public interface TagRelationQueryMapper {
    TagRelationDBO selectByPrimaryKey(Long id);
}