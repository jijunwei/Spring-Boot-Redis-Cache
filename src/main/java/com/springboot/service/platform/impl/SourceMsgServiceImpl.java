package com.springboot.service.platform.impl;

import com.springboot.mapper.SourceMsgMapper;
import com.springboot.model.route.SourceMsg;
import com.springboot.service.platform.SourceMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceMsgServiceImpl implements SourceMsgService{
    @Autowired
    SourceMsgMapper sourceMsgMapper;
    @Override
    public int add(SourceMsg sourceMsg){
        int i=sourceMsgMapper.add(sourceMsg);
        return i;
    }
   /* @Override
    public int add(String sourceMsg){
        int i=sourceMsgMapper.addSourceMsg(sourceMsg);
        return i;
    }*/
}
