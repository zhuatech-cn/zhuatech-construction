/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.domain;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component public class DomainCatalog {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName(){return "知华 Construction 工程项目协同平台";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String sceneName(){return "项目进度、现场安全、材料与质量";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<SeedItem> seedItems(){return List.of(
        new SeedItem("CONSTRUCTION-20260801-001","二号楼高支模隐患整改","处理中","安全管理组","紧急"),
        new SeedItem("CONSTRUCTION-20260801-002","幕墙样板验收准备","待处理","质量管理组","高"),
        new SeedItem("CONSTRUCTION-20260801-003","钢筋进场复检闭环","已完成","物资管理组","中"),
        new SeedItem("CONSTRUCTION-20260801-004","关键线路工期纠偏","处理中","项目计划组","高"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<String> recommendedActions(){return List.of("立即隔离并处理重大安全隐患","复核关键线路资源和里程碑偏差","检查材料复检与质量验收记录");}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record SeedItem(String recordNo,String title,String status,String owner,String priority){}
}
