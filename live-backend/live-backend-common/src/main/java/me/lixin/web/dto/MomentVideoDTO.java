package me.lixin.web.dto;

import lombok.Data;
import me.lixin.web.dbo.MomentVideoDBO;

@Data
public class MomentVideoDTO extends MomentVideoDBO {

    private Boolean hasCollected;

}
