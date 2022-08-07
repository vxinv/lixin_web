package me.lixin.web.service;

import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.request.LoginRequest;

public interface LoginService {
    LoginUserDTO login(LoginRequest request) throws Exception;
}
