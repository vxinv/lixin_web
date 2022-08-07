package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.NotificationDBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationQueryMapper {
    NotificationDBO selectByPrimaryKey(Long id);

    List<NotificationDBO> queryByCategory(@Param("artistId") Long artistId, @Param("category") String category,
                                          @Param("receiveUserId") Long receiveUserId, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

}