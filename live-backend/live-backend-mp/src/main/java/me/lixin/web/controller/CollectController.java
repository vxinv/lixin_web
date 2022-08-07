package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.MomentDTO;
import me.lixin.web.request.CancelCollectRequest;
import me.lixin.web.request.CollectRequest;
import me.lixin.web.request.PageRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "收藏")
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "收藏")
    @PostMapping(value = "/collect")
    public BaseResponse collect(@Valid @RequestBody CollectRequest request) {

        collectService.collect(request);
        return new BaseResponse();
    }

    @ApiOperation(value = "取消收藏")
    @PostMapping(value = "/cancelCollect")
    public BaseResponse cancelCollect(@Valid @RequestBody CancelCollectRequest request) {

        collectService.cancelCollect(request);
        return new BaseResponse();
    }

    @ApiOperation(value = "查询收藏的视频")
    @PostMapping(value = "/queryCollectVideo")
    public BaseResponse<List<MomentDTO>> queryCollectVideo(@Valid @RequestBody PageRequest request) {
        return collectService.queryCollectVideo(request);
    }

}
