package cn.kd.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface AdviserWorkloadMapper {

    List<Serializable> selectAdviserWorkload(@Param("startTime") String startTime,
                                             @Param("endTime") String endTime,
                                             @Param("groupIds") Long[] groupIds,
                                             @Param("adviserIds") Long[] adviserIds);
}
