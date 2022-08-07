package me.lixin.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnumSharePageType {

    INDEX("INDEX", "首页"),
    SQUARE("SQUARE", "广场"),
    PROFILE("PROFILE", "个人主页"),
    MOMENT_DETAIL("MOMENT_DETAIL", "动态详情"),
    VIDEO_DETAIL("VIDEO_DETAIL", "视频详情"),
    ;

    private String value;
    private String desc;


}
