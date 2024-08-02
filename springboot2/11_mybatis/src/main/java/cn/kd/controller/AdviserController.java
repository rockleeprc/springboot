package cn.kd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加盟顾问
 */
@Validated
@RestController
@RequestMapping("/adviser")
public class AdviserController {
    private static final Logger logger = LoggerFactory.getLogger(AdviserController.class);


}
