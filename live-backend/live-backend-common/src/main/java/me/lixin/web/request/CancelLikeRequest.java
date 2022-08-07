package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumLikeTargetType;

import javax.validation.constraints.NotNull;

@Data
public class CancelLikeRequest {

    @NotNull
    private EnumLikeTargetType targetType;

    @NotNull
    private Long targetId;
}
