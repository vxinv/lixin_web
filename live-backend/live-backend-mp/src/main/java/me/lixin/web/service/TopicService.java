package me.lixin.web.service;

import me.lixin.web.dto.TopicDTO;
import me.lixin.web.request.GetTopicDetailRequest;
import me.lixin.web.request.QueryTopicRequest;
import me.lixin.web.request.UpdateTopicCoverUrlRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface TopicService {

    BaseResponse<List<TopicDTO>> queryTopic(QueryTopicRequest request);

    BaseResponse<TopicDTO> getTopicDetail(GetTopicDetailRequest request);

    BaseResponse updateTopicCoverUrl(UpdateTopicCoverUrlRequest request);
}
