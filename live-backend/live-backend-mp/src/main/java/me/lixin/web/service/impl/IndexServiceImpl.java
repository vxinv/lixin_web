package me.lixin.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lixin.web.constant.RedisKey;
import me.lixin.web.util.UserContext;
import me.lixin.web.dto.IndexSwiperDTO;
import me.lixin.web.dto.QueryGuessYouLikeDTO;
import me.lixin.web.dto.QueryIndexBlockDTO;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.IndexService;
import me.lixin.web.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    private final RedisUtils redisUtils;

    @Autowired
    public IndexServiceImpl(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public BaseResponse<List<IndexSwiperDTO>> queryIndexSwiper() {
        BaseResponse<List<IndexSwiperDTO>> response = new BaseResponse<>();
        List<IndexSwiperDTO> list = redisUtils.getList(UserContext.getRedisKeyPrefix() + RedisKey.INDEX_SWIPER, IndexSwiperDTO.class);
        response.setBody(list);
        return response;
    }

    @Override
    public BaseResponse<List<QueryIndexBlockDTO>> queryIndexBlock() {
        return null;
    }

    @Override
    public BaseResponse<List<QueryGuessYouLikeDTO>> queryGuessYouLike() {
        return null;
    }
}
