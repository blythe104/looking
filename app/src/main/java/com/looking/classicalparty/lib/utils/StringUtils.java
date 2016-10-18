package com.looking.classicalparty.lib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xin on 2016/10/18.
 */
public class StringUtils {
    public static String quchuxiaoshubufen(String str) {
        int j = str.lastIndexOf(".");

        return str.substring(0, j);
    }


    public static String subPhoneNumber(String phone) {
        return phone.substring(0, phone.length() - (phone.substring(3)).length()) + "****" + phone.substring(7);
    }

    /**
     * 获取字符串中的数字
     *
     * @param str
     * @return
     */
    public static String getNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 截取非数字
     *
     * @param str
     * @return
     */
    public static String getNotNumber(String str) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 验证手机号
     *
     * @param mobiles 手机号
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {

        String str = "^([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\\.][a-z]{2,3}([\\.][a-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();

    }

    /**
     * 验证密码格式
     *
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd) {
        String str = "^(?![a-zA-Z]+$)[a-zA-Z][a-zA-Z0-9]{5,19}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    /**
     * 去除获取金额过大产生的科学计数问题
     *
     * @param money
     * @return
     */
    public static String normalMoney(double money) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(money);
    }

}
