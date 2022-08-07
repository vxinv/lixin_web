package me.lixin.web.service;

import me.lixin.web.dto.MomentDTO;
import me.lixin.web.dto.MomentDetailDTO;
import me.lixin.web.dto.MomentImageDTO;
import me.lixin.web.request.*;
import me.lixin.web.response.BaseResponse;

import java.util.List;
import java.util.TreeMap;

public interface MomentService {

    BaseResponse<List<MomentDTO>> querySquareMoment(QuerySquareMomentRequest request);

    List<MomentDTO> queryMoment(Long artistId, Long userId, String momentType, Long repostMomentId, List<Long> userIdList, Long activityId, List<Long> idList, Integer start, Integer pageSize);

    BaseResponse<List<MomentDTO>> queryFriendMoment(PageRequest request);

    BaseResponse<List<MomentDTO>> queryTopicMoment(QueryTopicMomentRequest request);

    BaseResponse<MomentDetailDTO> getMomentDetail(GetMomentDetailRequest request);

    void publishMoment(PublishMomentRequest request);

    void deleteMoment(DeleteMomentRequest request);

    BaseResponse<TreeMap<String, List<MomentImageDTO>>> querySquareImage(QuerySquareImageRequest request);

}
