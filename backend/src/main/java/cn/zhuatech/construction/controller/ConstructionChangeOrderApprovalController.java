/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.controller;

import cn.zhuatech.construction.common.ApiResponse;
import cn.zhuatech.construction.service.ConstructionChangeOrderApprovalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/construction")
public class ConstructionChangeOrderApprovalController {
    private final ConstructionChangeOrderApprovalService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ConstructionChangeOrderApprovalController(ConstructionChangeOrderApprovalService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/change-order-approval")
    public ApiResponse<ConstructionChangeOrderApprovalService.Assessment> assess(
            @Valid @RequestBody ConstructionChangeOrderApprovalService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
