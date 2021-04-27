package cn.kd.controller;

import cn.kd.common.Result;
import cn.kd.service.AdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("/adviser")
public class AdviserController {
    @Autowired
    private AdviserService adviserService;

    @GetMapping("/workloadAdviser")
    public Result workloadAdviser(@RequestParam(value = "startTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}[\\d]{2}[\\d]{2}", message = "日期格式:yyyyMMdd") String startTime,
                                  @RequestParam(value = "endTime", required = false) @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}[\\d]{2}[\\d]{2}", message = "日期格式:yyyyMMdd") String endTime,
                                  @RequestParam(name = "groupIds", required = false) Long[] groupIds,
                                  @RequestParam(name = "adviserIds", required = false) Long[] adviserIds) {
        return Result.success(adviserService.selectWorkloadAdviser(startTime, endTime, groupIds, adviserIds));
    }


}
