package com.snow.rpermission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-10-16
 * desc   :
 */
public class RequestPermissions {
    private ArrayList<String> mPermissions;
    private Activity activity;
    private ActionCallBack granted;
    private ActionCallBack onDenied;

    public static RequestPermissions with(Activity activity) {
        return new RequestPermissions(activity);
    }

    public RequestPermissions(Activity activity) {
        this.activity = activity;
    }

    public RequestPermissions permissions(String[] permissions) {
        mPermissions = new ArrayList<>();
        mPermissions.addAll(Arrays.asList(permissions));
        return this;
    }

    public RequestPermissions onGranted(ActionCallBack granted) {
        this.granted = granted;
        return this;
    }

    public RequestPermissions onDenied(ActionCallBack onDenied) {
        this.onDenied = onDenied;
        return this;
    }

    public void start() {
        BridgeActivity.requestPermission(activity, mPermissions);
        BridgeActivity.setRequestPermissionsCall(new BridgePermissionCall() {
            @Override
            public void requestCall(int flag, List<String> callPermissions) {
                if (flag == 1) {//授权完成
                    granted.callBack(callPermissions);
                } else {
                    onDenied.callBack(callPermissions);
                }
            }
        });
    }

}
