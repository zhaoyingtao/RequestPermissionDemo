package com.snow.rpermission;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-10-24
 * desc   :拒绝权限后的弹窗提示
 */

public class RefusePermissionDialog extends Dialog {

    private Context mContext;

    private TextView mTvContent;
    private TextView mTvSure;
    private TextView mTvCancel;

    public RefusePermissionDialog(Context context) {
        super(context, R.style.permissionDialogTheme);
        this.mContext = context;
        initView();
    }

    public RefusePermissionDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_refuse_permission, null);
        setContentView(contentView);
        mTvContent = contentView.findViewById(R.id.tv_content_msg);
        mTvSure = contentView.findViewById(R.id.tv_sure);
        mTvCancel = contentView.findViewById(R.id.tv_cancel);
        setCanceledOnTouchOutside(true);

        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JudgePermission.startAppInfoSettings((Activity) mContext);
                dismiss();
            }
        });
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultListener != null) {
                    resultListener.clickCancel();
                }
                dismiss();
            }
        });
    }

    private String contentMsg;

    public void setRefuseDialogContent(String content) {
        this.contentMsg = content;
    }

    public void show(String content) {
        show(content, "", "");
    }

    public void show(String content, String sure, String cancel) {
        if (TextUtils.isEmpty(contentMsg)) {
            mTvContent.setText(TextUtils.isEmpty(content) ? "" : content);
        } else {
            mTvContent.setText(contentMsg);
        }
        mTvSure.setText(TextUtils.isEmpty(sure) ? "设置" : sure);
        mTvCancel.setText(TextUtils.isEmpty(cancel) ? "取消" : cancel);
        show();
    }

    RefuseDialogCancelListener resultListener;

    public void setRefuseCancelListener(RefuseDialogCancelListener resultListener) {
        this.resultListener = resultListener;
    }
}
