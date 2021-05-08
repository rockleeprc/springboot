package cn.kd.controller;

import cn.kd.common.Result;
import cn.kd.service.AdviserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 加盟顾问
 */
@Validated
@RestController
@RequestMapping("/adviser")
public class AdviserController {
    private static final Logger logger = LoggerFactory.getLogger(AdviserController.class);
    @Autowired
    private AdviserService adviserService;

    /**
     * 加盟顾问工作量和产出 报表
     * admin、other权限：groupIds（顾问组）、adviserIds
     * 顾问权限：adviserIds
     *
     * @param startTime
     * @param endTime
     * @param groupIds
     * @param adviserIds
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/v1/workloadAdviser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result workloadAdviser(@RequestParam(name = "startTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String startTime,
                                  @RequestParam(name = "endTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String endTime,
                                  @RequestParam(name = "groupIds", required = false) Long[] groupIds,
                                  @RequestParam(name = "adviserIds", required = false) @Valid @NotNull(message = "不能为空") Long[] adviserIds,
                                  @RequestParam(name = "pageNum", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 2000, message = "最大值2000") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 5000, message = "最大值5000") Integer pageSize) {
        return Result.success(adviserService.selectWorkloadAdviser(startTime, endTime, groupIds, adviserIds, pageNum, pageSize));
    }


    /**
     * 加盟顾问组工作量和产出 报表
     * admin、other权限：groupIds（顾问组）
     *
     * @param startTime
     * @param endTime
     * @param groupIds
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/v1/workloadAdviserGroup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result workloadAdviserGroup(@RequestParam(name = "startTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String startTime,
                                       @RequestParam(name = "endTime", required = false) @Valid @NotNull(message = "不能为空") @Pattern(regexp = "^[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "日期格式:yyyy-MM-dd") String endTime,
                                       @RequestParam(name = "groupIds", required = false) Long[] groupIds,
                                       @RequestParam(name = "pageNum", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 2000, message = "最大值2000") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false) @Valid @NotNull(message = "不能为空") @Min(value = 1, message = "最小值1") @Max(value = 5000, message = "最大值5000") Integer pageSize) {
        return Result.success(adviserService.selectWorkloadAdviserGroup(startTime, endTime, groupIds, pageNum, pageSize));
    }

    @GetMapping("info1")
    public Result info1() {
        String str = null;
        str.toUpperCase();
        return Result.success();
    }

    @GetMapping("info2")
    public Result info2() {
        int i = 1 / 0;
        return Result.success();
    }

    @GetMapping("info3")
    public Result info3() {
        int[] arr = new int[2];
        return Result.success(arr[10]);
    }

    @GetMapping("info4")
    public Result info4() {
        return Result.success("");
    }
}
