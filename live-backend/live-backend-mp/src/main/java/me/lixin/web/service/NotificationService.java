package me.lixin.web.service;

import me.lixin.web.dbo.NotificationDBO;
import me.lixin.web.dto.AtNotificationDTO;
import me.lixin.web.dto.CommentNotificationDTO;
import me.lixin.web.request.ClearNotificationCountRequest;
import me.lixin.web.request.PageRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface NotificationService {

    void sendNotification(NotificationDBO notification);

    BaseResponse<List<AtNotificationDTO>> queryAtInfo(PageRequest request);

    BaseResponse clearNotificationCount(ClearNotificationCountRequest request);

    BaseResponse<List<CommentNotificationDTO>> queryCommentInfo(PageRequest request);

}
