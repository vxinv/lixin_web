package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.TopicRelationDBO;

import java.util.List;

public interface TopicRelationMapper {

    void insertBatch(List<TopicRelationDBO> dboList);

}