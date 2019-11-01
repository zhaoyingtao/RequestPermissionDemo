package com.snow.rpermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-11-01
 * desc   : 权限验证的辅助类
 */
public class SPermissionHelper {
    private Activity activity;

    public static SPermissionHelper with(Activity activity) {
        return new SPermissionHelper(activity);
    }

    public SPermissionHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * 验证权限,然后返回 true false
     *
     * @param permissions 有一个权限没有通过直接返回false
     * @return
     */
    public boolean verifyPermissions(String[] permissions) {
        if (permissions == null) {
            return false;
        }
        for (int i = 0; i < permissions.length; i++) {
            if (!verifyPermissions(permissions[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证权限,然后返回 true false
     *
     * @param permission
     * @return
     */
    public boolean verifyPermissions(String permission) {
        /*******below android 6.0*******/
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        int checkSelfPermission = ActivityCompat.checkSelfPermission(activity, permission);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }
}
