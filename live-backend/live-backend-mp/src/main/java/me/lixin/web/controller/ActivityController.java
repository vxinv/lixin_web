package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.ActivityDTO;
import me.lixin.web.request.JoinedActivityRequest;
import me.lixin.web.request.PageRequest;
import me.lixin.web.request.QueryActivityRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "活动")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @ApiOperation(value = "查询活动列表")
    @PostMapping(value = "/queryActivity")
    public BaseResponse<List<ActivityDTO>> queryActivity(@Valid @RequestBody QueryActivityRequest request) {
        return activityService.queryActivity(request);
    }

    @ApiOperation(value = "查询参与过的活动列表")
    @PostMapping(value = "/queryJoinedActivity")
    public BaseResponse<List<ActivityDTO>> queryJoinedActivity(@Valid @RequestBody PageRequest request) {
        return activityService.queryJoinedActivity(request);
    }

    @ApiOperation(value = "参与过某活动")
    @PostMapping(value = "/joinedActivity")
    public BaseResponse joinedActivity(@Valid @RequestBody JoinedActivityRequest request) {

        return activityService.joinedActivity(request);
    }
}
