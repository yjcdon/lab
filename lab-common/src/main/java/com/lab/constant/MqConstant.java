package com.lab.constant;

public class MqConstant {
    /*
    * 用于通知的固定值
    * */
    public static final String EXCHANGE_NOTIFY = "notify";

    public static final String QUEUE_NOTIFY = "notify";

    public static final String ROUTING_KEY_NOTIFY_ALL = "notify.*";

    public static final String ROUTING_KEY_NOTIFY_ADD = "notify.add";

    public static final String ROUTING_KEY_NOTIFY_DELETE = "notify.delete";

    public static final String ROUTING_KEY_NOTIFY_UPDATE = "notify.update";

    /*
    * 用于发送邮件的固定值
    * */
    public static final String EXCHANGE_EMAIL="email";

    public static final String QUEUE_EMAIL="email";

    public static final String ROUTING_KEY_EMAIL_ALL = "email.*";

    public static final String ROUTING_KEY_EMAIL_ADD = "email.add";

    public static final String ROUTING_KEY_EMAIL_DELETE = "email.delete";

    public static final String ROUTING_KEY_EMAIL_UPDATE = "email.update";

}
