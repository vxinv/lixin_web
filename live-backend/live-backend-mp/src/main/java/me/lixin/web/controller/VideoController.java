package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.MomentImageDTO;
import me.lixin.web.request.QueryVideoCoverRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "视频")
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "获取视频封面")
    @PostMapping(value = "/queryVideoCover")
    public BaseResponse<List<MomentImageDTO>> queryVideoCover(@Valid @RequestBody QueryVideoCoverRequest request) throws Exception {

        return videoService.queryVideoCover(request);
    }


}
