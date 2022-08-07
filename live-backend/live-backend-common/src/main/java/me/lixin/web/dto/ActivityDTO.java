package me.lixin.web.dto;

import lombok.Data;
import me.lixin.web.dbo.ActivityDBO;

@Data
public class ActivityDTO extends ActivityDBO {

    private Boolean hasJoined;
}
