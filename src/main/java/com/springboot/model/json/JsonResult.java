package com.springboot.model.json;




import com.springboot.constant.Constants;

import java.io.Serializable;

/**
 *   on 2017/06/24.
 */
public class JsonResult implements Serializable {

    private static final Long SUCCESS_CODE = 0L;

    private static final Long ERROR_STATE_CODE = 1L;

    private static final Long SUCCESS_BUSSINESS_CODE = 0L;

    private static final Long ERROR_BUSSINESS_CODE = 1L;

    private static final long serialVersionUID = -1L;

    private String msg = "";

    // 0成功，1失败
    private String code = "0000";

    // 业务码
    private String businessCode = "0000";

    // 接口调用是否成功
    private Boolean success;
    
    // 业务数据
    private Object data = null;

    public JsonResult() {
    }

    /**
     * @param msg
     * @param code
     * @param data
     */
    public JsonResult(String msg, Long code, Object data) {
        this.msg = msg;
        this.code = String.format(Constants.ZERO_FILL_TEMPLATE, code);
        this.data = data;
    }


    public JsonResult(String msg, Long code, Object data, Long businessCode) {
        this.msg = msg;
        this.code = String.format(Constants.ZERO_FILL_TEMPLATE, code);
        this.data = data;
        this.businessCode = String.format(Constants.ZERO_FILL_TEMPLATE, businessCode);
    }

    /**
     * 构造成功的JsonResult
     *
     * @return JsonResult
     */
    public static JsonResult buildSuccessResult() {
        return new JsonResult("success", SUCCESS_CODE, "", SUCCESS_BUSSINESS_CODE);
    }
    public static JsonResult buildSuccessResult(Object data) {
        return new JsonResult("success", SUCCESS_CODE, data, SUCCESS_BUSSINESS_CODE);
    }
    public static JsonResult buildSuccessResult(String msg, Object data) {
        return new JsonResult(msg, SUCCESS_CODE, data, SUCCESS_BUSSINESS_CODE);
    }
    public static JsonResult buildSuccessResult(String msg, Object data, Long bussinessId) {
        return new JsonResult(msg, SUCCESS_CODE, data, bussinessId);
    }

    /**
     * 构造状态不正确的JsonResult
     *
     * @param msg  String
     * @return JsonResult
     */
    public static JsonResult buildErrorStateResult(String msg) {
        return new JsonResult(msg, ERROR_STATE_CODE, "", ERROR_BUSSINESS_CODE);
    }
    public static JsonResult buildErrorStateResult(String msg, Object data) {
        return new JsonResult(msg, ERROR_STATE_CODE, data, ERROR_BUSSINESS_CODE);
    }

    public static JsonResult buildErrorStateResult(String msg, Object data, Long busniessId) {
        return new JsonResult(msg, ERROR_STATE_CODE, data, busniessId);
    }

    public static JsonResult buildFatalErrorStateResult(String msg, Object data, Long busniessId) {
        return new JsonResult(msg, ERROR_STATE_CODE, data, busniessId);
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "businessCode='" + businessCode + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        success = "0000".equals(code);
        return success;
    }
    
}
