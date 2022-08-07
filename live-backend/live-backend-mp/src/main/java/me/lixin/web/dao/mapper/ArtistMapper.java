package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.ArtistDBO;

public interface ArtistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArtistDBO record);

    int insertSelective(ArtistDBO record);
    int updateByPrimaryKeySelective(ArtistDBO record);

    int updateByPrimaryKey(ArtistDBO record);
}