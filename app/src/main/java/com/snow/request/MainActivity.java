package com.snow.request;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.snow.rpermission.ActionCallBack;
import com.snow.rpermission.RequestPermissions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();
    }

    /**
     * 请求权限
     */
    private void permission() {
        RequestPermissions.with(this)
                .permissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE})
                .onGranted(new ActionCallBack() {
                    @Override
                    public void callBack(List<String> strings) {
//                        ToastUtils.getInstance().showToast("获取权限成功");
                        Log.e("qq", "======获取权限成功");
                    }
                })
                .onDenied(new ActionCallBack() {
                    @Override
                    public void callBack(List<String> strings) {
                        Log.e("qq", "======获取权限失败====");
                        for (int i = 0; i < strings.size(); i++) {
                            if (i == 0) {
//                                ToastUtils.getInstance().showToast(getPermissionRemind(strings.get(i)));
                            }
                            Log.e("qq", "======" + strings.get(i));
                        }
                    }
                })
                .start();
    }

    private String getPermissionRemind(String permission) {
        if ("android.permission.READ_EXTERNAL_STORAGE".equals(permission)) {//存储
            return "进入页面后点击“权限”，打开“存储”权限。";
        } else if ("android.permission.READ_PHONE_STATE".equals(permission)) {//电话
            return "进入页面后点击“权限”，打开“电话”权限。";
        } else if ("android.permission.CAMERA".equals(permission)) {//拍照
            return "进入页面后点击“权限”，打开“相机”权限。";
        } else {
            return "请点击“设置”-“权限”-打开所需权限";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            permission();
        }
    }
}
