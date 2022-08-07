package me.lixin.web.service;

import me.lixin.web.dto.CommentDTO;
import me.lixin.web.request.GetCommentDetailRequest;
import me.lixin.web.request.PublishCommentRequest;
import me.lixin.web.request.DeleteCommentRequest;
import me.lixin.web.request.QueryMomentCommentRequest;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface CommentService {


    BaseResponse<Long> publishComment(PublishCommentRequest request);

    BaseResponse deleteComment(DeleteCommentRequest request);

    BaseResponse<List<CommentDTO>> queryMomentComment(QueryMomentCommentRequest request);

    BaseResponse<CommentDTO> getCommentDetail(GetCommentDetailRequest request);

}
