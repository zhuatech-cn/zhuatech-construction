/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class ConstructionChangeOrderApprovalService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.scopeDefined()) blockers.add("工程变更范围未明确");
        if (!request.costEstimateValidated()) blockers.add("成本估算未复核");
        if (!request.scheduleImpactAssessed()) blockers.add("工期影响未评估");
        if (!request.safetyImpactApproved()) blockers.add("施工安全影响未批准");
        if (!request.designApproved()) blockers.add("设计变更未批准");
        if (!request.contractAndClaimReviewed()) blockers.add("合同与索赔影响未复核");
        if (!request.fundingApproved()) blockers.add("变更资金未落实");
        if (!request.finalApprovalComplete()) blockers.add("变更最终审批未完成");
        if (!blockers.isEmpty()) {
            actions.add("阻断变更执行并完成设计、商务与安全评审");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (!request.procurementReady() || !request.stakeholdersNotified() || !request.contingencyReady()) {
            if (!request.procurementReady()) actions.add("完成材料、分包和采购准备");
            if (!request.stakeholdersNotified()) actions.add("通知业主、监理、总包和受影响班组");
            if (!request.contingencyReady()) actions.add("准备变更失败与现场恢复方案");
            return new Assessment(Decision.COORDINATE, blockers, actions);
        }
        actions.add("批准工程变更并锁定范围、造价、工期和审批版本");
        return new Assessment(Decision.APPROVE, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String changeOrderId, boolean scopeDefined,
                          boolean costEstimateValidated, boolean scheduleImpactAssessed,
                          boolean safetyImpactApproved, boolean designApproved,
                          boolean contractAndClaimReviewed, boolean fundingApproved,
                          boolean procurementReady, boolean stakeholdersNotified,
                          boolean contingencyReady, boolean finalApprovalComplete) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { APPROVE, COORDINATE, BLOCKED }
}
