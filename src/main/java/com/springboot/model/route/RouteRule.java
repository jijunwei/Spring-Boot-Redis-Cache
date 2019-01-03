package com.springboot.model.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class RouteRule {
    /**
     * 产品
     */
    Map<String,HashMap<String,List<String>>> routeByProduct=new HashMap<String,HashMap<String,List<String>>>();
    /**
     * 风险等级
     */

    Map<String,HashMap<String,List<String>>> routeByRiskDegree=new HashMap<String,HashMap<String,List<String>>>();
    /**
     * 地域
     */
    Map<String,HashMap<String,List<String>>> routeByArea=new HashMap<String,HashMap<String,List<String>>>();
    /**
     * 年龄
     */

    Map<String,HashMap<String,List<String>>> routeByAge=new HashMap<String,HashMap<String,List<String>>>();

    /**
     * 金额
     */
    Map<String,HashMap<String,List<String>>> routeByAmount=new HashMap<String,HashMap<String,List<String>>>();


    /**
     * 权重
     */
    Map<String,HashMap<String,List<String>>> routeByWeight=new HashMap<String,HashMap<String,List<String>>>();

    /**
     * 手工配置
     */
    Map<String,HashMap<String,List<String>>> routeByHand=new HashMap<String,HashMap<String,List<String>>>();

    /**
     * 随机
     */

    Map<String,HashMap<String,List<String>>> routeByRandom=new HashMap<String,HashMap<String,List<String>>>();

    public void RouteRule(){
      initRouteByHand();
    }
    public void initRouteByHand(){
        HashMap<String,List<String>> destByHand=new HashMap<String,List<String>>();
        List<String> dest=new ArrayList<String>();
        dest.add("HengShuiBank");
        destByHand.put("XWbank",dest);


        List<String> baoTouBankDest=new ArrayList<String>();
        baoTouBankDest.add("BaoTouBank");
        destByHand.put("MaShangFinTech",baoTouBankDest);

        routeByHand.put("hand",destByHand);
    }

    public String getRoute(String channelNode,String rule){
        String dest=null;
        if(rule.equals("hand")){
           HashMap<String,List<String>> destByHand=routeByHand.get("hand");
           List<String> destBank=destByHand.get(channelNode);
           if(!destBank.isEmpty()){
               dest=destBank.get(0);
           }

       }
       return dest;
    }

}
