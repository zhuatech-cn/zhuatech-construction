/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction;
import cn.zhuatech.construction.service.ScheduleVarianceService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ScheduleVarianceServiceTests {private final ScheduleVarianceService service=new ScheduleVarianceService();/**
                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                 */
@Test void flagsUnrecoverableDelay(){var r=service.evaluate(new ScheduleVarianceService.Request(60,40,60,100,3,5,5));assertEquals("DELAYED",r.status());assertEquals(-20.0,r.progressVariance());}/**
                                                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                   */
@Test void keepsSmallVarianceOnTrack(){var r=service.evaluate(new ScheduleVarianceService.Request(50,48,50,100,0,0,0));assertEquals("ON_TRACK",r.status());}}
