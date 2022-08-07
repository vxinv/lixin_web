package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.MomentImageDBO;

import java.util.List;

public interface MomentImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MomentImageDBO record);

    int insertSelective(MomentImageDBO record);

    int updateByPrimaryKeySelective(MomentImageDBO record);

    int updateByPrimaryKey(MomentImageDBO record);

    void insertBatch(List<MomentImageDBO> momentImageDBOList);
}