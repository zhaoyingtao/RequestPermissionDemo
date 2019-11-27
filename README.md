# RequestPermissionDemo

[ ![Download](https://api.bintray.com/packages/zhaoyingtao/maven/snowpermission/images/download.svg) ](https://bintray.com/zhaoyingtao/maven/snowpermission/_latestVersion)

权限申请封装依赖库   

![sddd](https://github.com/zhaoyingtao/RequestPermissionDemo/blob/master/app/permission_show.gif?raw=true)

引用：   
```
 api 'com.bintray.library:snowpermission:1.1.1'
```

使用方法很简单，就不做解释了：注意：不要在onresume方法中调用，会出现弹窗覆盖问题
```
 RequestPermissions.with(this)
                .setRefuseDialogContent("去设置打开权限")//可以自定义拒绝权限后提示框的内容，默认根据拒绝权限提示对应内容
                .showRefuseDialog(new RefuseDialogCancelListener() {//拒绝权限后弹窗的取消后的操作监听
                    @Override
                    public void clickCancel() {
                        finish();
                    }
                })
                .permissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE})
                .onGranted(new ActionCallBack() {//所有权限都授权成功
                    @Override
                    public void callBack(List<String> strings) {
                        Log.e("qq", "======获取权限成功");
                    }
                })
                .onDenied(new ActionCallBack() {//权限中至少有一个没有授权
                    @Override
                    public void callBack(List<String> strings) {
                        Log.e("qq", "======获取权限失败====");
                    }
                })
                .start();
```

注意：申请权限的数组只能传单个权限，不能传组权限，比如：  
```
group:android.permission-group.PHONE  
包括以下权限   
permission:android.permission.READ_CALL_LOG
permission:android.permission.READ_PHONE_STATE
permission:android.permission.CALL_PHONE
permission:android.permission.WRITE_CALL_LOG
permission:android.permission.USE_SIP
permission:android.permission.PROCESS_OUTGOING_CALLS
permission:com.android.voicemail.permission.ADD_VOICEMAIL

不能传group权限，只能传permission的权限

```
