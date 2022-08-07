package me.lixin.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lixin.web.dbo.RegionCityDBO;
import me.lixin.web.dbo.RegionProvinceDBO;
import me.lixin.web.dbo.UserDBO;
import me.lixin.web.enums.EnumGender;
import me.lixin.web.util.BeanCopyUtils;
import me.lixin.web.util.UserContext;
import me.lixin.web.dao.mapper.RegionCityQueryMapper;
import me.lixin.web.dao.mapper.RegionProvinceQueryMapper;
import me.lixin.web.dao.mapper.UserMapper;
import me.lixin.web.dao.mapper.UserQueryMapper;
import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.dto.UserDTO;
import me.lixin.web.request.GetUserInfoRequest;
import me.lixin.web.request.UpdateUserInfoRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserQueryMapper userQueryMapper;
    @Resource
    private RegionCityQueryMapper regionCityQueryMapper;
    @Resource
    private RegionProvinceQueryMapper regionProvinceQueryMapper;

    @Override
    public BaseResponse<UserDTO> getUserInfo(GetUserInfoRequest request) {
        BaseResponse<UserDTO> response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long userId = loginUser.getUserId();
        Long artistId = loginUser.getArtistId();

        if (request.getUserId() != null) {
            userId = request.getUserId();
        }

        UserDBO userDBO = userQueryMapper.getById(artistId, userId);
        UserDTO dto = BeanCopyUtils.copy(userDBO, UserDTO.class);
        response.setBody(dto);
        return response;
    }

    @Override
    public BaseResponse updateUserInfo(UpdateUserInfoRequest request) {
        BaseResponse response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long userId = loginUser.getUserId();
        Long artistId = loginUser.getArtistId();

        UserDBO dbo = BeanCopyUtils.copy(request, UserDBO.class);
        dbo.setArtistId(artistId);
        dbo.setId(userId);

        EnumGender gender = request.getGender();
        if (Objects.nonNull(gender)) {
            dbo.setGender(gender.getValue());
        }

        String cityId = request.getCityId();
        if (StringUtils.isNotBlank(cityId)) {
            RegionCityDBO city = regionCityQueryMapper.selectByPrimaryKey(cityId);
            if (Objects.nonNull(city)) {
                String provinceId = city.getProvinceId();
                RegionProvinceDBO province = regionProvinceQueryMapper.selectByPrimaryKey(provinceId);
                if (Objects.nonNull(province)) {
                    dbo.setProvince(province.getName());
                    dbo.setCity(city.getName());
                }
            }
        }

        userMapper.updateByPrimaryKeySelective(dbo);
        return response;
    }
}
