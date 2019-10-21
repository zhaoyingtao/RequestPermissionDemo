package com.snow.rpermission;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-10-16
 * desc   :透明的activity，获取权限
 */
public final class BridgeActivity extends Activity {

    private static final String KEY_PERMISSIONS = "KEY_PERMISSIONS";
    public static final int TYPE_PERMISSION = 2;
    private static BridgePermissionCall bridgePermissionCall;
    //授权通过获取到的权限
    private List<String> mGranted;
    //未获取的权限
    private List<String> mDenied;

    /**
     * Request for permissions.
     */
    static void requestPermission(Activity activity, ArrayList<String> permissions) {
        Intent intent = new Intent(activity, BridgeActivity.class);
        intent.putStringArrayListExtra(KEY_PERMISSIONS, permissions);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
    }

    public static void setRequestPermissionsCall(BridgePermissionCall permissionCall) {
        bridgePermissionCall = permissionCall;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGranted = new ArrayList<>();
        mDenied = new ArrayList<>();
        ArrayList<String> permissionList = getIntent().getStringArrayListExtra(KEY_PERMISSIONS);
        if (permissionList != null && permissionList.size() > 0) {
            mGranted.addAll(permissionList);
            if (Build.VERSION.SDK_INT < 23) {
                if (bridgePermissionCall != null) {
                    bridgePermissionCall.requestCall(1, mGranted);
                }
            } else {
                requestPermissions(permissionList.toArray(new String[permissionList.size()]),
                        TYPE_PERMISSION);
            }
        } else {
            if (bridgePermissionCall != null) {
                bridgePermissionCall.requestCall(1, mGranted);
            }
            finish();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int flag = 1;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == -1) {//未获得此权限
                mDenied.add(permissions[i]);
                flag = 2;
            }
        }
        if (bridgePermissionCall != null) {
            if (flag == 2) {
                bridgePermissionCall.requestCall(flag, mDenied);
            } else {
                bridgePermissionCall.requestCall(flag, mGranted);
            }
        }
        finish();
    }
}
