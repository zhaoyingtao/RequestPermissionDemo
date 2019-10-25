package com.snow.request;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.snow.rpermission.ActionCallBack;
import com.snow.rpermission.RefuseDialogCancelListener;
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
//                .setRefuseDialogContent("shdk即可获得尽快发货快")
                .showRefuseDialog(new RefuseDialogCancelListener() {
                    @Override
                    public void clickCancel() {
                        finish();
                    }
                })
                .permissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_CONTACTS,"android.permission.CALL_PHONE"})
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        permission();
    }
}
