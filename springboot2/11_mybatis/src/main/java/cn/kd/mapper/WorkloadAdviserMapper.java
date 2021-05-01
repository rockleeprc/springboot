package cn.kd.mapper;

import cn.kd.entity.view.WorkloadAdviserView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 加盟顾问工作量和产出
 */
public interface WorkloadAdviserMapper {

    List<WorkloadAdviserView> selectWorkloadAdviser(@Param("startTime") String startTime,
                                                    @Param("endTime") String endTime,
                                                    @Param("groupIds") Long[] groupIds,
                                                    @Param("adviserIds") Long[] adviserIds);

    List<WorkloadAdviserView> selectWorkloadAdviserGroup(@Param("startTime") String startTime,
                                                         @Param("endTime") String endTime,
                                                         @Param("groupIds") Long[] groupIds);


}
