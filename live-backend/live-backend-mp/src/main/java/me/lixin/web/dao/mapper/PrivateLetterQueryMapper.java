package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.PrivateLetterDBO;

public interface PrivateLetterQueryMapper {
    PrivateLetterDBO selectByPrimaryKey(Long id);
}