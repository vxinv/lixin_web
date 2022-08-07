package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumCollectTargetType;

import javax.validation.constraints.NotNull;

@Data
public class CancelCollectRequest {

    @NotNull
    private EnumCollectTargetType targetType;

    @NotNull
    private Long targetId;
}
