package me.lixin.web.dto;

import lombok.Data;
import me.lixin.web.dbo.UserDBO;

@Data
public class UserDTO extends UserDBO {

    private Boolean hasFollowed;
}
