package cn.kd.controller;

import cn.kd.common.Result;
import cn.kd.service.AdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
@RestController
@RequestMapping("/adviser")
public class AdviserController {
    @Autowired
    private AdviserService adviserService;

    @PostMapping("/workloadAdviser")
    public Result workloadAdviser(@RequestParam(value = "startTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String startTime,
                                  @RequestParam(value = "endTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String endTime,
                                  @RequestParam(name = "groupIds", required = false) Long[] groupIds,
                                  @RequestParam(name = "adviserIds", required = false) Long[] adviserIds,
                                  @RequestParam(name = "pageNum", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 2000, message = "最大值2000") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 5000, message = "最大值5000") Integer pageSize) {
        return Result.success(adviserService.selectWorkloadAdviser(startTime, endTime, groupIds, adviserIds, pageNum, pageSize));
    }


    @PostMapping("/workloadAdviserGroup")
    public Result workloadAdviserGroup(@RequestParam(value = "startTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String startTime,
                                       @RequestParam(value = "endTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String endTime,
                                       @RequestParam(name = "groupIds", required = false) Long[] groupIds,
                                       @RequestParam(name = "pageNum", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 2000, message = "最大值2000") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 5000, message = "最大值5000") Integer pageSize) {
        return Result.success(adviserService.selectWorkloadAdviserGroup(startTime, endTime, groupIds, pageNum, pageSize));
    }

}
