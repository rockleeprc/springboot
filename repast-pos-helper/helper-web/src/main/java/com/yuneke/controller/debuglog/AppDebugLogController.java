package com.yuneke.controller.debuglog;

import com.yuneke.common.Result;
import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.debuglog.AppDebugLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import sun.swing.StringUIClientPropertyKey;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
//@Controller
@RequestMapping("/log")
public class AppDebugLogController {

    private Logger logger = LoggerFactory.getLogger(AppDebugLogController.class);

    @Autowired
    private AppDebugLogService appDebugLogService;

    @RequestMapping("/point")
    public Result<Boolean> point(Long userId, Long merchantId, Integer type, String detail) {

        if (userId == null || merchantId == null || type == null || StringUtils.isEmpty(detail)) {
            return Result.ok(false);
        }

        logger.info("userId=" + userId);
        logger.info("merchantId=" + merchantId);
        logger.info("type=" + type);
        logger.info("detail=" + detail);
        AppDebugLog appDebugLog = new AppDebugLog(userId, merchantId, type, detail, new Date());
        boolean result = appDebugLogService.save(appDebugLog);
        return Result.ok(result);
    }

    @RequestMapping("/date")
    public Result<Date> date() {
        return Result.ok(new Date());
    }

    @RequestMapping("/submit")
    public String submit(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("name", "张三");// 页面可获取，controller不能
//        redirectAttributes.addAttribute("name", "张三");//拼接url
        return "redirect:/log/show";
    }

    @RequestMapping("/show")
    @ResponseBody
    public Result<String> show(HttpServletRequest request) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        return Result.ok((String)flashMap.get("name"));
    }
}

