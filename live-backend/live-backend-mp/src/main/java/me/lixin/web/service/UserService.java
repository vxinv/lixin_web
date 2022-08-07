package me.lixin.web.service;

import me.lixin.web.dto.UserDTO;
import me.lixin.web.request.GetUserInfoRequest;
import me.lixin.web.request.UpdateUserInfoRequest;
import me.lixin.web.response.BaseResponse;

public interface UserService {

    BaseResponse<UserDTO> getUserInfo(GetUserInfoRequest request);

    BaseResponse updateUserInfo(UpdateUserInfoRequest request);
}
