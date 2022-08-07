package me.lixin.web.service;

import me.lixin.web.dto.FriendCountDTO;
import me.lixin.web.dto.UserDTO;
import me.lixin.web.request.CancelFollowRequest;
import me.lixin.web.request.FollowRequest;
import me.lixin.web.request.QueryFriendRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface FollowService {

    void follow(FollowRequest request);

    void cancelFollow(CancelFollowRequest request);

    BaseResponse<List<UserDTO>> queryFriend(QueryFriendRequest request);

    BaseResponse<FriendCountDTO> countFriend();

}

