package me.lixin.web.service;

import me.lixin.web.dto.MomentImageDTO;
import me.lixin.web.request.QueryVideoCoverRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface VideoService {


    BaseResponse<List<MomentImageDTO>> queryVideoCover(QueryVideoCoverRequest request);

}
