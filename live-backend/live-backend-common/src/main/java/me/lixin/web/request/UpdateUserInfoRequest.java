package me.lixin.web.request;

import lombok.Data;
import me.lixin.web.enums.EnumGender;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateUserInfoRequest {

    @Length(max = 50)
    private String name;

    private EnumGender gender;

    @Length(max = 255)
    private String avatarUrl;

    @Length(max = 50)
    private String description;

    @Length(max = 50)
    private String cityId;

    @Length(max = 50)
    private String university;


}
