package com.xuxiang.pj.entity.eventbus;

/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class ToastMessage {

    private String message;

    public ToastMessage(String message)
    {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
