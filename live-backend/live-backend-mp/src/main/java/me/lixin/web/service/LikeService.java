package me.lixin.web.service;

import me.lixin.web.dto.UserDTO;
import me.lixin.web.request.CancelLikeRequest;
import me.lixin.web.request.LikeRequest;
import me.lixin.web.request.QueryLikeUserRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface LikeService {


    void like(LikeRequest request);

    void cancelLike(CancelLikeRequest request);

    BaseResponse<List<UserDTO>> queryLikeUser(QueryLikeUserRequest request);

}
