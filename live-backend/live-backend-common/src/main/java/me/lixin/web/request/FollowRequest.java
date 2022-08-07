package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumFollowTargetType;

import javax.validation.constraints.NotNull;

@Data
public class FollowRequest {

    @NotNull
    private EnumFollowTargetType targetType;

    @NotNull
    private Long targetId;
}
