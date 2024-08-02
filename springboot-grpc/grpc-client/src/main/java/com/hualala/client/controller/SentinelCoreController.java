package com.hualala.client.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@RestController
public class SentinelCoreController {
    private static final String FLOW_RESOURCE = "flow";

    /**
     * 启动类中配置SentinelResourceAspect
     *
     * @return
     */
    @SentinelResource(value = "flow1",
            fallback = "flowFallBack",// 接口异常
            // exceptionsToIgnore = , 排除哪些异常不处理
            blockHandler = "flowBlockHandler") // 流控
    @RequestMapping("/flow1")
    public String flow1() {
        int i = 1 / 0;
        return LocalDateTime.now().toString();
    }

    /**
     * 测试时不能添加BlockException参数，否则报错
     *
     * @return
     */
    public String flowFallBack() {
        return "异常降级";
    }

    /**
     * 必须是public
     * 方法返回值和原方法一致
     * 参数一致，顺序一致，可以添加BlockException参数对象（测试时必须添加BlockException否则报错）
     * 默认必须和原方法在同一个类中
     * 不和原方法在同一个类中：注解配置blockHandlerClass属性；方法必须声明为public static
     * 单独使用sentinel时需要把SentinelResourceAspect注入到ioc容器中
     *
     * @return
     */
    public String flowBlockHandler(BlockException e) {
        e.printStackTrace();
        return "流控降级";
    }

    @RequestMapping("/flow")
    public String flow() {
        Entry entry = null;
        try {
            entry = SphU.entry(FLOW_RESOURCE);
            return LocalDateTime.now().toString();
        } catch (BlockException e) {
            log.info("BlockException");
            e.printStackTrace();
            return "被流控";
        } catch (Exception e) {
            // 有降级规则时，记录业务异常
            Tracer.traceEntry(e, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        // 流控规则
        FlowRule flowRule = new FlowRule();
        flowRule.setResource(FLOW_RESOURCE); // 资源名称，一般使用方法名称
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); // QPS规则
        flowRule.setCount(1); // QPS阈值数量


        // 流控规则
        FlowRule flow1 = new FlowRule();
        flow1.setResource("flow1"); // 资源名称，一般使用方法名称
        flow1.setGrade(RuleConstant.FLOW_GRADE_QPS); // QPS规则
        flow1.setCount(1); // QPS阈值数量

        rules.add(flowRule);
        rules.add(flow1);

        // 加载规则
        FlowRuleManager.loadRules(rules);
    }

}
