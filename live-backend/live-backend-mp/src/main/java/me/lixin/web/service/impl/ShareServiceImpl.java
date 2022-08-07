package me.lixin.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lixin.web.dbo.MomentDBO;
import me.lixin.web.dbo.NotificationDBO;
import me.lixin.web.dbo.ShareDBO;
import me.lixin.web.enums.*;
import me.lixin.web.util.AssertUtils;
import me.lixin.web.util.UserContext;
import me.lixin.web.dao.mapper.MomentMapper;
import me.lixin.web.dao.mapper.MomentQueryMapper;
import me.lixin.web.dao.mapper.ShareMapper;
import me.lixin.web.dao.mapper.ShareQueryMapper;
import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.request.ShareRequest;
import me.lixin.web.service.NotificationService;
import me.lixin.web.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class ShareServiceImpl implements ShareService {

    @Resource
    private MomentMapper momentMapper;
    @Resource
    private MomentQueryMapper momentQueryMapper;
    @Resource
    private ShareMapper shareMapper;
    @Resource
    private ShareQueryMapper shareQueryMapper;
    @Autowired
    private NotificationService notificationService;

    @Override
    @Transactional
    public void share(ShareRequest request) {

        LoginUserDTO loginUser = UserContext.getLoginUser();
        Long artistId = loginUser.getArtistId();
        Long userId = loginUser.getUserId();

        EnumSharePageType pageType = request.getPageType();
        Long momentId = request.getMomentId();
        ShareDBO shareDBO = new ShareDBO();
        shareDBO.setPageType(pageType.getValue());
        shareDBO.setArtistId(artistId);
        shareDBO.setMomentId(momentId);
        shareDBO.setUserId(userId);
        shareMapper.insertSelective(shareDBO);

        if (Objects.equals(pageType, EnumSharePageType.MOMENT_DETAIL)
                || Objects.equals(pageType, EnumSharePageType.VIDEO_DETAIL)) {

            MomentDBO momentDBO = momentQueryMapper.getById(artistId, momentId, EnumStatus.NORMAL.getValue());
            AssertUtils.notNull(momentDBO, "动态不存在：" + momentId);
            momentMapper.updateShareCount(artistId, momentId, 1);

            // 发消息
            if (Objects.equals(momentDBO.getType(), EnumMomentType.VIDEO.getValue())) {
                notificationService.sendNotification(NotificationDBO.builder()
                        .type(EnumNotificationType.SHARE_VIDEO.getValue())
                        .momentId(momentId)
                        .receiveUserId(momentQueryMapper.getUserId(artistId, momentId))
                        .build());
            } else {
                notificationService.sendNotification(NotificationDBO.builder()
                        .type(EnumNotificationType.SHARE_MOMENT.getValue())
                        .momentId(momentId)
                        .receiveUserId(momentQueryMapper.getUserId(artistId, momentId))
                        .build());
            }
        }
    }
}
