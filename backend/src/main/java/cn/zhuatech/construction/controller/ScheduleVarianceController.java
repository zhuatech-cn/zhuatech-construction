/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.controller;
import cn.zhuatech.construction.common.ApiResponse;import cn.zhuatech.construction.service.ScheduleVarianceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/construction/insights/schedule-variance") public class ScheduleVarianceController {private final ScheduleVarianceService service;/**
                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                        */
public ScheduleVarianceController(ScheduleVarianceService service){this.service=service;}/**
                                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                 */
@PostMapping ApiResponse<ScheduleVarianceService.Result> evaluate(@Valid @RequestBody ScheduleVarianceService.Request request){return ApiResponse.ok(service.evaluate(request));}}
