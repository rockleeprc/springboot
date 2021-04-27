package cn.kd.service;

import cn.kd.entity.WorkloadAdviser;
import cn.kd.entity.view.WorkloadAdviserView;
import cn.kd.mapper.WorkloadAdviserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviserService {

    @Autowired
    private WorkloadAdviserMapper workloadAdviserMapper;

    public List<WorkloadAdviserView> selectWorkloadAdviser(String startTime, String endTime, Long[] groupIds, Long[] adviserIds) {
        return workloadAdviserMapper.selectWorkloadAdviser(startTime,endTime,groupIds,adviserIds);
    }
}
