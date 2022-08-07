package me.lixin.web.service;

import me.lixin.web.dto.IndexSwiperDTO;
import me.lixin.web.dto.QueryGuessYouLikeDTO;
import me.lixin.web.dto.QueryIndexBlockDTO;
import me.lixin.web.response.BaseResponse;

import java.util.List;

public interface IndexService {

    BaseResponse<List<IndexSwiperDTO>> queryIndexSwiper();

    BaseResponse<List<QueryIndexBlockDTO>> queryIndexBlock();

    BaseResponse<List<QueryGuessYouLikeDTO>> queryGuessYouLike();

}
