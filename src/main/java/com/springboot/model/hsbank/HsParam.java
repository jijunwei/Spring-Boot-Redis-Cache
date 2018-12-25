package com.springboot.model.hsbank;


import com.springboot.model.hsbank.req.ReqMsg;
import com.springboot.model.hsbank.resp.RespMsg;
import lombok.Data;

@Data
public class HsParam {
    ReqMsg reqMsg;
    RespMsg respMsg;
}
