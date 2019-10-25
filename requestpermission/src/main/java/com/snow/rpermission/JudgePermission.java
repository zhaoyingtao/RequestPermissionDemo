package com.snow.rpermission;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-10-24
 * desc   :
 */
public class JudgePermission {
    //跳转应用设置页面的请求码
    public final static int SKIP_SETTING_REQUEST_CODE = 4454;

    /**
     * 获取确实权限提示语
     *
     * @param permission
     * @return
     */
    public static String getPermissionRemind(String permission) {
        if ("android.permission.READ_EXTERNAL_STORAGE".equals(permission)
                || "android.permission.WRITE_EXTERNAL_STORAGE".equals(permission)) {//存储
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“存储”权限。";
        } else if ("android.permission.READ_PHONE_STATE".equals(permission)
                || "android.permission.CALL_PHONE".equals(permission)) {//电话
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“电话”权限。";
        } else if ("android.permission.CAMERA".equals(permission)) {//拍照
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“相机”权限。";
        } else if ("android.permission.RECORD_AUDIO".equals(permission)) {//录音
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“录音”权限。";
        } else if ("android.permission.WRITE_CONTACTS".equals(permission)
                || "android.permission.GET_ACCOUNTS".equals(permission)
                || "android.permission.READ_CONTACTS".equals(permission)) {//通讯录
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“通讯录”权限。";
        } else if ("android.permission.BODY_SENSORS".equals(permission)) {//身体传感器
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“身体传感器”权限。";
        } else if ("android.permission.ACCESS_FINE_LOCATION".equals(permission)
                || "android.permission.ACCESS_COARSE_LOCATION".equals(permission)) {//位置
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“位置”权限。";
        } else if ("android.permission.READ_CALENDAR".equals(permission)
                || "android.permission.WRITE_CALENDAR".equals(permission)) {//日历
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“日历”权限。";
        } else if ("android.permission.READ_SMS".equals(permission)
                || "android.permission.RECEIVE_SMS".equals(permission)
                || "android.permission.SEND_SMS".equals(permission)) {//短信
            return "请点击下方“设置”，\n进入页面后点击“权限”，\n打开“短信”权限。";
        } else {
            return "请点击“设置”-“权限”-打开所需权限";
        }
    }

    /**
     * 应用信息界面
     *
     * @param activity
     */
    public static void startAppInfoSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, SKIP_SETTING_REQUEST_CODE);
    }
}
