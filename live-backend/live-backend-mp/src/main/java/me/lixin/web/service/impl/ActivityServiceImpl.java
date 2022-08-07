package me.lixin.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lixin.web.dao.mapper.ActivityQueryMapper;
import me.lixin.web.dao.mapper.JoinedActivityMapper;
import me.lixin.web.dao.mapper.JoinedActivityQueryMapper;
import me.lixin.web.dbo.ActivityDBO;
import me.lixin.web.dbo.JoinedActivityDBO;
import me.lixin.web.dto.ActivityDTO;
import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.request.JoinedActivityRequest;
import me.lixin.web.request.PageRequest;
import me.lixin.web.request.QueryActivityRequest;
import me.lixin.web.response.BaseResponse;
import me.lixin.web.service.ActivityService;
import me.lixin.web.util.BeanCopyUtils;
import me.lixin.web.util.PageUtils;
import me.lixin.web.util.UserContext;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {


    @Autowired
    private ActivityQueryMapper activityQueryMapper;
    @Autowired
    private JoinedActivityMapper joinedActivityMapper;
    @Autowired
    private JoinedActivityQueryMapper joinedActivityQueryMapper;

    @Override
    public BaseResponse<List<ActivityDTO>> queryActivity(QueryActivityRequest request) {
        BaseResponse<List<ActivityDTO>> response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long artistId = loginUser.getArtistId();
        Long userId = loginUser.getUserId();

        // 查询活动
        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        int start = PageUtils.getStart(pageNo, pageSize);
        List<ActivityDBO> activityDBOList = activityQueryMapper.queryActivity(artistId, start, pageSize);
        List<ActivityDTO> activityDTOList = BeanCopyUtils.copy(activityDBOList, ActivityDTO.class);

        // 查询参与情况
        List<JoinedActivityDBO> joinedActivityDBOList = joinedActivityQueryMapper.queryByUserId(artistId, userId, null, null);
        Set<Long> joinedSet = joinedActivityDBOList.stream().map(JoinedActivityDBO::getActivityId).collect(Collectors.toSet());
        activityDTOList.forEach(activity -> {
            activity.setHasJoined(joinedSet.contains(activity.getId()));
        });

        response.setBody(activityDTOList);
        return response;
    }

    @Override
    public BaseResponse joinedActivity(JoinedActivityRequest request) {
        BaseResponse response = new BaseResponse();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long artistId = loginUser.getArtistId();
        Long userId = loginUser.getUserId();

        Long activityId = request.getActivityId();
        Boolean hasJoined = request.getHasJoined();

        if (hasJoined) {
            JoinedActivityDBO dbo = new JoinedActivityDBO();
            dbo.setArtistId(artistId);
            dbo.setUserId(userId);
            dbo.setActivityId(activityId);
            joinedActivityMapper.insertSelective(dbo);
        } else {
            JoinedActivityDBO dbo = new JoinedActivityDBO();
            dbo.setArtistId(artistId);
            dbo.setUserId(userId);
            dbo.setActivityId(activityId);
            joinedActivityMapper.delete(dbo);
        }

        return response;
    }

    @Override
    public BaseResponse<List<ActivityDTO>> queryJoinedActivity(PageRequest request) {
        BaseResponse<List<ActivityDTO>> response = new BaseResponse<>();
        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long artistId = loginUser.getArtistId();
        Long userId = loginUser.getUserId();

        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        int start = PageUtils.getStart(pageNo, pageSize);
        List<JoinedActivityDBO> joinedActivityDBOList = joinedActivityQueryMapper.queryByUserId(artistId, userId, start, pageSize);
        if (CollectionUtils.isEmpty(joinedActivityDBOList)) {
            response.setBody(new ArrayList<>());
            return response;
        }

        List<Long> activityIdList = joinedActivityDBOList.stream().map(JoinedActivityDBO::getActivityId).collect(Collectors.toList());
        List<ActivityDBO> activityDBOList = activityQueryMapper.queryActivityByIds(artistId, activityIdList);
        List<ActivityDTO> activityDTOList = BeanCopyUtils.copy(activityDBOList, ActivityDTO.class);
        activityDTOList.forEach(activity -> {
            activity.setHasJoined(true);
        });

        response.setBody(activityDTOList);
        return response;
    }
}
