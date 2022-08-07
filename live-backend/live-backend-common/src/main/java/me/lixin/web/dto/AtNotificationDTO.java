package me.lixin.web.dto;

import lombok.Data;
import me.lixin.web.dbo.NotificationDBO;

@Data
public class AtNotificationDTO extends NotificationDBO {

    private MomentDTO moment;
}
