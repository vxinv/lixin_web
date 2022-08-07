package me.lixin.web.dto;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class RepostUserDTO {

    private Long userId;

    private String userName;

    private String userAvatarUrl;

    private DateTime repostTime;

    private String textContent;
}
