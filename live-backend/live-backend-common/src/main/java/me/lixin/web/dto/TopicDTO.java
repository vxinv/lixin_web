package me.lixin.web.dto;

import lombok.Data;
import me.lixin.web.dbo.TopicDBO;

@Data
public class TopicDTO extends TopicDBO {

    private String createUserName;

    private String lastUpdateUserName;

    private Integer momentCount;

    private Integer userCount;

}
