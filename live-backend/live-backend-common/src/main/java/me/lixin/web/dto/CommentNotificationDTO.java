package me.lixin.web.dto;

import lombok.Data;
import me.lixin.web.dbo.NotificationDBO;

@Data
public class CommentNotificationDTO extends NotificationDBO {

    private MomentDTO moment;

    private String commentContent;

    private String parentCommentContent;

    private String sendUserName;

    private String sendUserAvatarUrl;
}
