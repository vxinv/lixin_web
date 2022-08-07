package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.MomentDTO;
import me.lixin.web.dto.MomentImageDTO;
import me.lixin.web.request.QuerySquareImageRequest;
import me.lixin.web.request.QuerySquareMomentRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.ActivityService;
import me.lixin.web.service.MomentService;
import me.lixin.web.service.UserService;
import me.lixin.web.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.TreeMap;

@Api(description = "广场")
@RestController
@RequestMapping("/square")
public class SquareController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询广场动态")
    @PostMapping(value = "/querySquareMoment")
    public BaseResponse<List<MomentDTO>> querySquareMoment(@Valid @RequestBody QuerySquareMomentRequest request) {
        return momentService.querySquareMoment(request);
    }

    @ApiOperation(value = "查询广场相册")
    @PostMapping(value = "/querySquareImage")
    public BaseResponse<TreeMap<String, List<MomentImageDTO>>> querySquareImage(@Valid @RequestBody QuerySquareImageRequest request) {
        return momentService.querySquareImage(request);
    }
}
