package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumNotificationCategory;

import javax.validation.constraints.NotNull;

@Data
public class ClearNotificationCountRequest {

    @NotNull
    private EnumNotificationCategory category;

}
