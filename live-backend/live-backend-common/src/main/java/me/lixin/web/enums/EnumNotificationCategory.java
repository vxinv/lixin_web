package me.lixin.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnumNotificationCategory {

    COMMENT("COMMENT", "评论"),
    AT("AT", "@"),
    NOTIFICATION("NOTIFICATION", "通知"),
    ;

    private String value;
    private String desc;
}
