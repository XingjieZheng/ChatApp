package com.xingjiezheng.chatapp.framework;

public class BaseBean extends BaseToStringInstance {
    /**
     * 任务执行成功(true)
     */
    public static final int STATUS_SUCCESS = 1;
    /**
     * 任务执行失败(false)
     */
    public static final int STATUS_FAILED = -1;

    /**
     * 执行消息，针对执行不同状态，返回的服务器提示消息
     **/
    private String msg;
    private int status;
    private boolean isLastPage;

    /**
     * 是否执行成功
     */
    public boolean isStatusSuccess() {
        return STATUS_SUCCESS == status;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

//    @Override
//    public String toString() {
//        StringBuilder beanString = new StringBuilder();
//        beanString.append("status=");
//        beanString.append(status);
//        beanString.append(", msg=");
//        beanString.append(msg);
//        beanString.append(", isLastPage=");
//        beanString.append(isLastPage);
//        return beanString.toString();
//    }
}
