package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.MomentDTO;
import me.lixin.web.dto.TopicDTO;
import me.lixin.web.request.GetTopicDetailRequest;
import me.lixin.web.request.QueryTopicMomentRequest;
import me.lixin.web.request.QueryTopicRequest;
import me.lixin.web.request.UpdateTopicCoverUrlRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.MomentService;
import me.lixin.web.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "话题")
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private MomentService momentService;

    @ApiOperation(value = "查询话题")
    @PostMapping(value = "/queryTopic")
    public BaseResponse<List<TopicDTO>> queryTopic(@Valid @RequestBody QueryTopicRequest request) {
        return topicService.queryTopic(request);
    }

    @ApiOperation(value = "查询话题动态")
    @PostMapping(value = "/queryTopicMoment")
    public BaseResponse<List<MomentDTO>> queryTopicMoment(@Valid @RequestBody QueryTopicMomentRequest request) {
        return momentService.queryTopicMoment(request);
    }

    @ApiOperation(value = "获取话题详情")
    @PostMapping(value = "/getTopicDetail")
    public BaseResponse<TopicDTO> getTopicDetail(@Valid @RequestBody GetTopicDetailRequest request) {
        return topicService.getTopicDetail(request);
    }

    @ApiOperation(value = "更新话题封面")
    @PostMapping(value = "/updateTopicCoverUrl")
    public BaseResponse updateTopicCoverUrl(@Valid @RequestBody UpdateTopicCoverUrlRequest request) {
        return topicService.updateTopicCoverUrl(request);
    }

}
