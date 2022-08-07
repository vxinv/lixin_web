package me.lixin.web.dao.mapper;

import me.lixin.web.dbo.JoinedActivityDBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JoinedActivityQueryMapper {
    JoinedActivityDBO selectByPrimaryKey(Long id);

    List<JoinedActivityDBO> queryByUserId(@Param("artistId") Long artistId, @Param("userId") Long userId,
                                          @Param("start") Integer start, @Param("pageSize") Integer pageSize);

}