package me.lixin.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lixin.web.dao.mapper.*;
import me.lixin.web.dbo.TopicDBO;
import me.lixin.web.dbo.UserDBO;
import me.lixin.web.dto.GroupCountDTO;
import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.dto.TopicDTO;
import me.lixin.web.request.GetTopicDetailRequest;
import me.lixin.web.request.QueryTopicRequest;
import me.lixin.web.request.UpdateTopicCoverUrlRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.TopicService;
import me.lixin.web.util.BeanCopyUtils;
import me.lixin.web.util.PageUtils;
import me.lixin.web.util.UserContext;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;
    @Resource
    private TopicQueryMapper topicQueryMapper;
    @Resource
    private TopicRelationMapper topicRelationMapper;
    @Resource
    private TopicRelationQueryMapper topicRelationQueryMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserQueryMapper userQueryMapper;

    @Override
    public BaseResponse<List<TopicDTO>> queryTopic(QueryTopicRequest request) {
        BaseResponse<List<TopicDTO>> response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long userId = loginUser.getUserId();
        Long artistId = loginUser.getArtistId();

        String name = request.getName();
        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        int start = PageUtils.getStart(pageNo, pageSize);

        List<TopicDBO> dboList = topicQueryMapper.queryTopic(artistId, name, start, pageSize);
        List<TopicDTO> dtoList = BeanCopyUtils.copy(dboList, TopicDTO.class);
        if (CollectionUtils.isEmpty(dtoList)) {
            response.setBody(dtoList);
            return response;
        }

        // 查询用户
        Set<Long> userIdSet = new HashSet<>();
        userIdSet.addAll(dtoList.stream().map(TopicDTO::getCreateUserId).collect(Collectors.toSet()));
        userIdSet.addAll(dtoList.stream().map(TopicDTO::getLastUpdateUserId).collect(Collectors.toSet()));
        response.setBody(dtoList);
        List<UserDBO> userDBOList = userQueryMapper.queryByUserIds(artistId, new ArrayList<>(userIdSet));
        Map<Long, UserDBO> idToUser = userDBOList.stream().collect(Collectors.toMap(UserDBO::getId, Function.identity(), (key1, key2) -> key2));

        // 查询动态个数
        List<Long> topicIdList = dtoList.stream().map(TopicDTO::getId).collect(Collectors.toList());
        List<GroupCountDTO> groupCountDTOList = topicRelationQueryMapper.countByTopicId(artistId, topicIdList);
        Map<Long, GroupCountDTO> topicIdToMomentCount = groupCountDTOList.stream().collect(Collectors.toMap(GroupCountDTO::getId, Function.identity(), (key1, key2) -> key2));

        dtoList.forEach(topic -> {
            UserDBO createUserDBO = idToUser.get(topic.getCreateUserId());
            if (Objects.nonNull(createUserDBO)) {
                topic.setCreateUserName(createUserDBO.getName());
            }
            UserDBO lastUpdateUserDBO = idToUser.get(topic.getLastUpdateUserId());
            if (Objects.nonNull(lastUpdateUserDBO)) {
                topic.setLastUpdateUserName(lastUpdateUserDBO.getName());
            }
            GroupCountDTO groupCountDTO = topicIdToMomentCount.get(topic.getId());
            if (Objects.nonNull(groupCountDTO)) {
                topic.setMomentCount(groupCountDTO.getCount());
            }
        });
        return response;
    }

    @Override
    public BaseResponse<TopicDTO> getTopicDetail(GetTopicDetailRequest request) {
        BaseResponse<TopicDTO> response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long userId = loginUser.getUserId();
        Long artistId = loginUser.getArtistId();
        String name = request.getName();

        TopicDBO topicDBO = topicQueryMapper.getByName(artistId, name);
        TopicDTO topicDTO = BeanCopyUtils.copy(topicDBO, TopicDTO.class);
        if (Objects.isNull(topicDTO)) {
            return response;
        }

        int count = topicRelationQueryMapper.countUserByTopicId(artistId, topicDTO.getId());
        topicDTO.setUserCount(count);
        response.setBody(topicDTO);
        return response;
    }

    @Override
    public BaseResponse updateTopicCoverUrl(UpdateTopicCoverUrlRequest request) {
        BaseResponse<TopicDTO> response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long userId = loginUser.getUserId();
        Long artistId = loginUser.getArtistId();
        Long topicId = request.getTopicId();
        String coverUrl = request.getCoverUrl();

        TopicDBO topicDBO = topicQueryMapper.selectByPrimaryKey(artistId, topicId);
        if (Objects.isNull(topicDBO)) {
            return response;
        }
        if (!Objects.equals(topicDBO.getCreateUserId(), userId)) {
            return response;
        }

        topicDBO.setCoverUrl(coverUrl);
        topicMapper.updateByPrimaryKeySelective(topicDBO);

        return response;
    }
}
