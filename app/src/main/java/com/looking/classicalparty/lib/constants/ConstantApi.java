package com.looking.classicalparty.lib.constants;

/**
 * Created by xin on 2016/10/20.
 */
public class ConstantApi {
    private static String BaseUrl = "http://www.jingdian.party/api/";//BuildConfig.BASE_URL;
    public static final String login = BaseUrl + "login/login";
    public static final String getkey = BaseUrl + "common/getAppkey";

    public static final String checkUser = BaseUrl + "register/checkMemberIsHas";

    public static final String register = BaseUrl + "register/registerMember";
    //发现页面数据
    public static final String HOME = BaseUrl + "home";
    public static final String UPDATEPWD = BaseUrl + "member/UpdatePassword";
    public static final String DETAIL = BaseUrl + "member/detail";
    public static final String MESSAGE = BaseUrl + "member/contact";

    public static final String MUSIC = BaseUrl + "content/getMusic";

    public static final String CATEGORY = BaseUrl + "content/getVideoCategory";
    public static final String VIDEO = BaseUrl + "content/getVideo";
    public static final String VIDEODETAIL = BaseUrl + "content/getVideoDetail";

    public static final String COMMENT = BaseUrl + "member/sendComment";
    public static final String VERSION = BaseUrl + "home/getAppVersion";

}
