/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressPaymentService {
    public PaymentDecision evaluate(PaymentRequest request) {
        BigDecimal recognized = request.measuredAmount().add(request.approvedChangeAmount())
            .min(request.claimedAmount()).min(request.contractAmount());
        BigDecimal eligible = recognized.multiply(BigDecimal.ONE.subtract(
            request.retentionRate().divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP)))
            .setScale(2, RoundingMode.HALF_UP);
        BigDecimal variance = request.claimedAmount().subtract(eligible).setScale(2, RoundingMode.HALF_UP);
        List<String> reasons = new ArrayList<>();
        if (!request.documentsComplete()) reasons.add("计量、签证或验收资料不完整");
        if (request.safetyHold()) reasons.add("项目存在安全停付事项");
        if (request.claimedAmount().compareTo(eligible) > 0) reasons.add("申报金额高于本期可支付金额");
        if (request.claimedAmount().compareTo(request.contractAmount()) > 0) reasons.add("申报金额超过合同金额");
        String decision = !request.documentsComplete() || request.safetyHold() ? "BLOCK"
            : variance.signum() > 0 ? "REVIEW" : "APPROVE";
        if (reasons.isEmpty()) reasons.add("计量、变更、保留金和资料满足支付条件");
        return new PaymentDecision(eligible, variance, decision, reasons);
    }

    public record PaymentRequest(@NotNull @DecimalMin("0.01") BigDecimal contractAmount,
        @NotNull @DecimalMin("0.00") BigDecimal claimedAmount,
        @NotNull @DecimalMin("0.00") BigDecimal measuredAmount,
        @NotNull @DecimalMin("0.00") BigDecimal approvedChangeAmount,
        @NotNull @DecimalMin("0.00") @DecimalMax("100.00") BigDecimal retentionRate,
        @NotNull Boolean documentsComplete, @NotNull Boolean safetyHold) {}
    public record PaymentDecision(BigDecimal eligibleAmount, BigDecimal variance,
        String decision, List<String> reasons) {}
}
