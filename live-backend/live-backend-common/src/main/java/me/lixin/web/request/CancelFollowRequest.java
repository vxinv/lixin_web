package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumFollowTargetType;

import javax.validation.constraints.NotNull;

@Data
public class CancelFollowRequest {

    @NotNull
    private EnumFollowTargetType targetType;

    @NotNull
    private Long targetId;
}
