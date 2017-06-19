package com.xuxiang.pj.nt;

import com.google.gson.annotations.SerializedName;

/**
 * @author 后知后觉(307817387)
 *         <p>
 *         服务端接口返回数据的基类，数据定义如下：
 *         {
 *         code:int,
 *         msg:string,
 *         data:{}
 *         }
 *         code表示返回码（0表示未知，1表示成功，其他值表示失败）。
 *         msg表示错误信息或者为空。
 *         data字段根据各个业务不同，在派生类中去自己定义。
 */
public class ResultObject {

    public static final int RESULT_UNKNOWN = 0;

    public static final int RESULT_OK = 1;

    public static final int RESULT_ERROR = -1;

    @SerializedName("code")
    private int Code;

    @SerializedName("msg")
    private String Msg;

    public ResultObject() {
        Code = RESULT_UNKNOWN;
        Msg = "";
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
