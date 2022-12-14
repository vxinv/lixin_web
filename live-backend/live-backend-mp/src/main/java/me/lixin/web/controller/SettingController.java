package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.RegionCityDTO;
import me.lixin.web.request.AddFeedbackRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "设置")
@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @ApiOperation(value = "意见反馈")
    @PostMapping(value = "/addFeedback")
    public BaseResponse addFeedback(@Valid @RequestBody AddFeedbackRequest request) {

        return settingService.addFeedback(request);
    }

    @ApiOperation(value = "查询城市列表")
    @PostMapping(value = "/queryCity")
    public BaseResponse<List<RegionCityDTO>> queryCity() {

        return settingService.queryCity();
    }

}
