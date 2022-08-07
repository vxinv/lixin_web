package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.MomentDTO;
import me.lixin.web.request.PageRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.ActivityService;
import me.lixin.web.service.MomentService;
import me.lixin.web.service.UserService;
import me.lixin.web.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "发现")
@RestController
@RequestMapping("/discover")
public class DiscoverController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询好友动态")
    @PostMapping(value = "/queryFriendMoment")
    public BaseResponse<List<MomentDTO>> queryFriendMoment(@Valid @RequestBody PageRequest request) {
        return momentService.queryFriendMoment(request);
    }

}
