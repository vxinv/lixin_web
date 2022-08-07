package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.PrivateLetterDBO;

public interface PrivateLetterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrivateLetterDBO record);

    int insertSelective(PrivateLetterDBO record);
    int updateByPrimaryKeySelective(PrivateLetterDBO record);

    int updateByPrimaryKey(PrivateLetterDBO record);
}