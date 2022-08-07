package me.lixin.web.service;

import me.lixin.web.dto.RegionCityDTO;
import me.lixin.web.request.AddFeedbackRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface SettingService {

    BaseResponse addFeedback(AddFeedbackRequest request);

    BaseResponse<List<RegionCityDTO>> queryCity();

}
