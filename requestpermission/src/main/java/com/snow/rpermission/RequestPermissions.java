package com.snow.rpermission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-10-16
 * desc   :权限请求
 */
public class RequestPermissions {
    private ArrayList<String> mPermissions;
    private Activity activity;
    private ActionCallBack granted;
    private ActionCallBack onDenied;
    private RefusePermissionDialog refusePermissionDialog;
    boolean isShowRefuseDialog;

    public static RequestPermissions with(Activity activity) {
        return new RequestPermissions(activity);
    }

    public RequestPermissions(Activity activity) {
        this.activity = activity;
        refusePermissionDialog = new RefusePermissionDialog(activity);
    }

    public RequestPermissions permissions(String[] permissions) {
        mPermissions = new ArrayList<>();
        mPermissions.addAll(Arrays.asList(permissions));
        return this;
    }

    /**
     * 权限请求全部允许回调
     *
     * @param granted
     * @return
     */
    public RequestPermissions onGranted(ActionCallBack granted) {
        this.granted = granted;
        return this;
    }

    /**
     * 权限请求有一个没允许回调
     *
     * @param onDenied
     * @return
     */
    public RequestPermissions onDenied(ActionCallBack onDenied) {
        this.onDenied = onDenied;
        return this;
    }

    /**
     * 设置拒绝弹窗的内容
     *
     * @param content
     * @return
     */
    public RequestPermissions setRefuseDialogContent(String content) {
        if (refusePermissionDialog != null) {
            refusePermissionDialog.setRefuseDialogContent(content);
        }
        return this;
    }

    /**
     * 显示拒绝提示dialog,以及点击取消的后续处理
     *
     * @return
     */
    public RequestPermissions showRefuseDialog(RefuseDialogCancelListener resultListener) {
        isShowRefuseDialog = true;
        if (refusePermissionDialog != null) {
            refusePermissionDialog.setRefuseCancelListener(resultListener);
        }
        return this;
    }

    public void start() {
        if (SPermissionHelper.with(activity).verifyPermissions(mPermissions)) {
            if (granted != null) {
                granted.callBack(mPermissions);
            }
            return;
        }
        BridgeActivity.requestPermission(activity, mPermissions);
        BridgeActivity.setRequestPermissionsCall(new BridgePermissionCall() {
            @Override
            public void requestCall(int flag, List<String> callPermissions) {
                if (flag == 1) {//授权完成
                    if (granted != null) {
                        granted.callBack(callPermissions);
                    }
                } else {
                    if (onDenied != null) {
                        onDenied.callBack(callPermissions);
                    }
                    if (isShowRefuseDialog && callPermissions.size() > 0) {
                        refusePermissionDialog.show(JudgePermission.getPermissionRemind(callPermissions.get(0)));
                    }
                }
            }
        });
    }

}
