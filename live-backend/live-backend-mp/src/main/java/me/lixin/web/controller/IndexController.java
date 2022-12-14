package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.IndexSwiperDTO;
import me.lixin.web.dto.QueryGuessYouLikeDTO;
import me.lixin.web.dto.QueryIndexBlockDTO;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.ActivityService;
import me.lixin.web.service.IndexService;
import me.lixin.web.service.MomentService;
import me.lixin.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "首页")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询首页轮播图")
    @PostMapping(value = "/queryIndexSwiper")
    public BaseResponse<List<IndexSwiperDTO>> queryIndexSwiper() {
        return indexService.queryIndexSwiper();
    }

    @ApiOperation(value = "查询首页板块")
    @PostMapping(value = "/queryIndexBlock")
    public BaseResponse<List<QueryIndexBlockDTO>> queryIndexBlock() {
        return indexService.queryIndexBlock();
    }

    @ApiOperation(value = "查询首页板块")
    @PostMapping(value = "/queryGuessYouLike")
    public BaseResponse<List<QueryGuessYouLikeDTO>> queryGuessYouLike() {
        return indexService.queryGuessYouLike();
    }

}
