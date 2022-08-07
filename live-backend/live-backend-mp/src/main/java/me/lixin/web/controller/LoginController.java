package me.lixin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.request.LoginRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public BaseResponse<LoginUserDTO> login(@Valid @RequestBody LoginRequest request) throws Exception {

        return new BaseResponse<LoginUserDTO>().setBody(loginService.login(request));
    }

    @ApiOperation(value = "检查登录token有效性")
    @PostMapping(value = "/checkLoginToken")
    public BaseResponse checkToken() {
        return new BaseResponse();
    }

}
