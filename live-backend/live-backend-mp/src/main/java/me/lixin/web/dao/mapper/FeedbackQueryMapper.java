package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.FeedbackDBO;

public interface FeedbackQueryMapper {
    FeedbackDBO selectByPrimaryKey(Long id);
}