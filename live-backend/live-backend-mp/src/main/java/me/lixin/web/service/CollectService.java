package me.lixin.web.service;

import me.lixin.web.dto.MomentDTO;
import me.lixin.web.request.CancelCollectRequest;
import me.lixin.web.request.CollectRequest;
import me.lixin.web.request.PageRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface CollectService {


    void collect(CollectRequest request);

    void cancelCollect(CancelCollectRequest request);

    BaseResponse<List<MomentDTO>> queryCollectVideo(PageRequest request);

}
