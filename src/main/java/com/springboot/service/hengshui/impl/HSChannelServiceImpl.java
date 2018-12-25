package com.springboot.service.hengshui.impl;


import com.springboot.controller.IBaseController;
import com.springboot.model.hssocket.HsMsg;
import com.springboot.model.hssocket.resp.Resp2007;
import com.springboot.service.hengshui.HSChannelService;
import com.springboot.service.pay.HSBankPayService;
import com.springboot.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@Transactional
@Service
public class HSChannelServiceImpl implements HSChannelService, IBaseController {

    private static String stringFormat = "%08d";
    @Autowired
    HSBankPayService hsbankPayService;

    @Override
    public String handle(String reqxml) throws UnsupportedEncodingException {
        String respXml = "";
        try {
            HsMsg hsMsg = (HsMsg) XmlUtil.xmlToBean2(reqxml, HsMsg.class);
            String serviceId = hsMsg.getServiceId();
            /*if ("2001".equals(serviceId)) {
                respXml = userService.registerForHsBank(reqxml);
            } else if ("2002".equals(serviceId)) {
                respXml = iProductService.queryForHsBank(reqxml);
            } else if ("2003".equals(serviceId)) {
                respXml = iProductService.queryItemForHsBank(reqxml);
            } else if ("2004".equals(serviceId)) {
                respXml = hsbankPayService.payForHsBank(reqxml);
            } else if ("2006".equals(serviceId)) {
                respXml = hsbankPayService.selectMyList(reqxml);
            } else if ("2007".equals(serviceId)) {
                respXml = hsbankPayService.returnForHsBank(reqxml);
            } else if ("2009".equals(serviceId)) {
                respXml = hsbankPayService.drawForHsBank(reqxml);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            Resp2007 resp2007 = new Resp2007();
            resp2007.setCode("9999");
            resp2007.setMsg("服务器异常");
            respXml =  XmlUtil.beanToXml(resp2007, Resp2007.class);
        }

        respXml = String.format(stringFormat, respXml.getBytes("GBK").length) + respXml;
        return respXml;
    }
}
