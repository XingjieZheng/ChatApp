package com.xingjiezheng.chatapp.framework;


/**
 * 服务器返回Json映射对象基类，包含基本的执行状态，和消息
 * 如果发现本地名称和服务器名称不一致，可以采用SerializedName("jsonName")方法转换本地和json中的映射
 *
 * @author ZhangXiao
 */
public class BaseBean {
    /**
     * 需要重新登錄
     */
    public static final int STATUS_RELOGIN = 1;
    /**
     * 任务执行成功(true)
     */
    public static final int STATUS_SUCCESS = 1;

    /**
     * 已到达末页
     */
    public static final int STATUS_ENDPAGE = 11;

    /**
     * 任务执行失败(false)
     */
    public static final int STATUS_FAILED = -1;

    /**
     * 任务执行成功, 但返回结果为空
     */
    public static final int STATUS_EMPTY = -2;

    /**
     * 任务执行抛出错误
     */
    public static final int STATUS_EXCEPTION = -3;

    /**
     * 执行状态，具体含义参考STATUS_*开头的常量定义
     **/
    private int status;

    /**
     * 执行消息，针对执行不同状态，返回的服务器提示消息
     **/
    private String msg;

    /**
     * 执行任务的taskKey，json解析结束并不包含，需要执行任务结束动态的设置
     **/
    private int taskKey;

    /**
     * 请求的json数据，并不是所有请求都会返回json，需要在Request里边配置
     */
    private String json;

    private int aCoffeeCount = -1;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(int taskKey) {
        this.taskKey = taskKey;
    }

    /**
     * 是否执行成功
     *
     * @return true-成功，状态为STATUS_SUCCESS
     */
    public boolean isStatusSuccess() {
        return STATUS_SUCCESS == status;
    }

    private int userState;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    /**
     * 是否执行失败
     *
     * @return
     */
    public boolean isFailed() {
        return STATUS_EXCEPTION == status || STATUS_FAILED == status;
    }

    /**
     * 是否执行结果为空
     *
     * @return true-结果为空
     */
    public boolean isStatusEmpty() {
        return STATUS_EMPTY == status;
    }

    /**
     * 是否查询到最后一页
     *
     * @return true-到达末页
     */
    public boolean isStatusEndPage() {
        return STATUS_ENDPAGE == status;
    }

    public int getaCoffeeCount() {
        return aCoffeeCount;
    }

    public boolean isValidCount() {
        return aCoffeeCount != -1;
    }

    public int getUserState() {
        return userState;
    }

    @Override
    public String toString() {
        StringBuilder beanString = new StringBuilder();
        beanString.append("status=");
        beanString.append(status);
        beanString.append(", msg=");
        beanString.append(msg);
        beanString.append(", taskKey=");
        beanString.append(taskKey);
        return beanString.toString();
    }


}
