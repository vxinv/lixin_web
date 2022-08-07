package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.ArtistDBO;

public interface ArtistQueryMapper {
    ArtistDBO selectByPrimaryKey(Long id);

    ArtistDBO getByRandomKey(String randomKey);

}