/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.controller;

import cn.zhuatech.construction.common.ApiResponse;
import cn.zhuatech.construction.service.ProgressPaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/progress-payment")
public class ProgressPaymentController {
    private final ProgressPaymentService service;
    public ProgressPaymentController(ProgressPaymentService service) { this.service = service; }
    @PostMapping
    ApiResponse<ProgressPaymentService.PaymentDecision> evaluate(
        @Valid @RequestBody ProgressPaymentService.PaymentRequest request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
