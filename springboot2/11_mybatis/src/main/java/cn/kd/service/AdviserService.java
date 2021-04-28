package cn.kd.service;

import cn.kd.entity.view.WorkloadAdviserView;
import cn.kd.mapper.WorkloadAdviserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviserService {

    @Autowired
    private WorkloadAdviserMapper workloadAdviserMapper;

    public PageInfo<WorkloadAdviserView> selectWorkloadAdviser(String startTime, String endTime,
                                                               Long[] groupIds, Long[] adviserIds,
                                                               Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<WorkloadAdviserView> list = workloadAdviserMapper.selectWorkloadAdviser(startTime, endTime, groupIds, adviserIds);
        PageInfo<WorkloadAdviserView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public PageInfo<WorkloadAdviserView> selectWorkloadAdviserGroup(String startTime, String endTime,
                                                                Long[] groupIds,
                                                                Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<WorkloadAdviserView> list = workloadAdviserMapper.selectWorkloadAdviserGroup(startTime, endTime, groupIds);
        PageInfo<WorkloadAdviserView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
