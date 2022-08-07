package me.lixin.web.service;

import me.lixin.web.dto.RepostUserDTO;
import me.lixin.web.request.QueryRepostUserRequest;
import me.lixin.web.request.RepostRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface RepostService {

    BaseResponse repost(RepostRequest request);

    BaseResponse<List<RepostUserDTO>> queryRepostUser(QueryRepostUserRequest request);
}
