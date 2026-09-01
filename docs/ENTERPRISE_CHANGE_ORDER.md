# 企业级工程变更审批

`POST /api/enterprise/construction/change-order-approval` 检查变更范围、造价、工期、安全、设计、合同索赔、资金、采购、相关方沟通、应急方案和最终审批，返回 `APPROVE / COORDINATE / BLOCKED`。

生产使用应关联图纸版本、现场签证、合同清单、预算科目和进度计划，确保未经批准的变更不能进入施工与付款环节。
