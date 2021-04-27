package cn.kd.mapper;

import cn.kd.entity.view.WorkloadAdviserView;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface WorkloadAdviserMapper {

    List<WorkloadAdviserView> selectWorkloadAdviser(@Param("startTime") String startTime,
                                                    @Param("endTime") String endTime,
                                                    @Param("groupIds") Long[] groupIds,
                                                    @Param("adviserIds") Long[] adviserIds);
}
