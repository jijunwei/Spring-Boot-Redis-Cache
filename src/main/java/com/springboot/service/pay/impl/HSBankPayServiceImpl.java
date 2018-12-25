package com.springboot.service.pay.impl;

import com.springboot.constant.Constants;
import com.springboot.controller.IBaseController;
import com.springboot.mapper.UserDao;
import com.springboot.model.hsbank.HsParam;
import com.springboot.model.hsbank.req.ReqHead;
import com.springboot.model.hsbank.resp.RespMsg;
import com.springboot.model.hssocket.req.Req2004;
import com.springboot.model.hssocket.req.Req2005;
import com.springboot.model.hssocket.req.Req2006;
import com.springboot.model.hssocket.req.Req2007;
import com.springboot.model.hssocket.resp.*;
import com.springboot.model.pay.PayStatus;
import com.springboot.service.hengshui.HSBankService;
import com.springboot.service.pay.HSBankPayService;
import com.springboot.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HSBankPayServiceImpl implements HSBankPayService, IBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HSBankPayServiceImpl.class);
    @Autowired
    HSBankService hsBankService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserDao userDao;


    @Override
    public String payForHsBank(String reqXml) {

        Resp2004 resp2004 = new Resp2004();
        resp2004.setServiceId("2004");
        Req2004 req2004 = (Req2004) XmlUtil.xmlToBean2(reqXml, Req2004.class);

        if (req2004 == null) {
            resp2004.setCode("0001");
            resp2004.setMsg("报文解析异常");
            return XmlUtil.beanToXml(resp2004, Resp2004.class);
        }


        return XmlUtil.beanToXml(resp2004, Resp2004.class);
    }



    private Double getTnterest(Double amount2, Double rate, Long day) {
        BigDecimal amount = new BigDecimal(amount2);
        BigDecimal intersetRate = new BigDecimal(rate);//利率
        BigDecimal days = new BigDecimal(day);//天数
        BigDecimal anticipatedTnterest = days.multiply(intersetRate).multiply(amount).divide(new BigDecimal(36000.00), 2, BigDecimal.ROUND_HALF_DOWN);

        return anticipatedTnterest.doubleValue() / 100;
    }

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal(25000);
        BigDecimal intersetRate = new BigDecimal(10);//利率
        BigDecimal days = new BigDecimal(90);//天数
        BigDecimal anticipatedTnterest = days.multiply(intersetRate).multiply(amount).divide(new BigDecimal(36000.00), 2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(anticipatedTnterest.doubleValue());


    }

}
