/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.construction.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service public class ScheduleVarianceService {
 public Result evaluate(Request r){double variance=Math.round((r.actualProgress()-r.plannedProgress())*10)/10.0;double pace=r.actualProgress()/r.elapsedDays();double forecast=Math.min(100,Math.round((pace*r.totalDays()+r.recoverableProgressPoints())*10)/10.0);List<String> actions=new ArrayList<>();if(r.criticalActivitiesLate()>0)actions.add("重排 "+r.criticalActivitiesLate()+" 项延误关键活动");if(r.weatherDelayDays()>0)actions.add("核对天气延误证据和工期索赔条件");if(variance<-5)actions.add("制定资源赶工与交叉施工方案");String status=variance>=-3?"ON_TRACK":forecast>=95?"RECOVERY":"DELAYED";if(actions.isEmpty())actions.add("按当前基线继续施工并更新周计划");return new Result(variance,forecast,status,actions);}
 public record Request(@DecimalMin("0") @DecimalMax("100") double plannedProgress,@DecimalMin("0") @DecimalMax("100") double actualProgress,@Min(1) int elapsedDays,@Min(1) int totalDays,@Min(0) int criticalActivitiesLate,@Min(0) int weatherDelayDays,@DecimalMin("0") double recoverableProgressPoints){}
 public record Result(double progressVariance,double forecastCompletion,String status,List<String> actions){}
}
