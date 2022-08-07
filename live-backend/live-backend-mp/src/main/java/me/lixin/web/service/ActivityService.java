package me.lixin.web.service;

import me.lixin.web.dto.ActivityDTO;
import me.lixin.web.request.JoinedActivityRequest;
import me.lixin.web.request.PageRequest;
import me.lixin.web.request.QueryActivityRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface ActivityService {

    BaseResponse<List<ActivityDTO>> queryActivity(QueryActivityRequest request);

    BaseResponse joinedActivity(JoinedActivityRequest request);

    BaseResponse<List<ActivityDTO>> queryJoinedActivity(PageRequest request);

}
