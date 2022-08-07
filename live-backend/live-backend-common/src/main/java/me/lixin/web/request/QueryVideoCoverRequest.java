package me.lixin.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QueryVideoCoverRequest {

    @NotBlank
    private String videoPath;
}
