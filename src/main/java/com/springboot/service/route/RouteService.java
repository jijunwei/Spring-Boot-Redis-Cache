package com.springboot.service.route;

public interface RouteService {

    String process(String reqxml);
    String getOneRouteRulePath(String sourceChannelCode,String rule);
}
