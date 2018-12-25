package com.springboot.util;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *   on 2017/8/28.
 * 调用工作流需要
 */
@Component
public class RestClient {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    public static String restTemplate(String url, String jsonString) {
        LOGGER.info("请求url：{},参数:{}", url, jsonString);
        // 核心返回结果报文字符串
        String returnJsonString;

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时
        requestFactory.setConnectTimeout(60000);
        requestFactory.setReadTimeout(60000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        //设置HTTP请求头信息，实现编码等
        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        //封装参数和请求头
        HttpEntity<String> entity = new HttpEntity<String>(jsonString, requestHeaders);
        //发送请求
        returnJsonString = restTemplate.postForObject(url, entity, String.class);
        LOGGER.info("返回信息:{}", returnJsonString);
        return returnJsonString;
    }
}
