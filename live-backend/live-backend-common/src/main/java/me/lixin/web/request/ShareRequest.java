package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumSharePageType;

import javax.validation.constraints.NotNull;

@Data
public class ShareRequest {

    @NotNull
    private EnumSharePageType pageType;

    private Long momentId;
}
