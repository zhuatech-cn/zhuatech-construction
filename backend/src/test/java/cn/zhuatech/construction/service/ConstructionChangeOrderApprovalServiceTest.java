/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ConstructionChangeOrderApprovalServiceTest {
    private final ConstructionChangeOrderApprovalService service = new ConstructionChangeOrderApprovalService();
    @Test void approvesControlledChange() {
        var r = service.assess(new ConstructionChangeOrderApprovalService.Request("C1", true, true, true,
                true, true, true, true, true, true, true, true));
        assertThat(r.decision()).isEqualTo(ConstructionChangeOrderApprovalService.Decision.APPROVE);
    }
    @Test void coordinatesDeliveryGaps() {
        var r = service.assess(new ConstructionChangeOrderApprovalService.Request("C2", true, true, true,
                true, true, true, true, false, false, false, true));
        assertThat(r.actions()).hasSize(3);
    }
    @Test void blocksUncontrolledChange() {
        var r = service.assess(new ConstructionChangeOrderApprovalService.Request("C3", false, false, false,
                false, false, false, false, true, true, true, false));
        assertThat(r.blockers()).hasSize(8);
    }
}
