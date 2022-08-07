package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumTagRelationEntityType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddTagRequest {

    @NotNull
    private EnumTagRelationEntityType entityType;

    @NotBlank
    private String name;
}
