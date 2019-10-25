# RequestPermissionDemo
权限申请封装依赖库   

![演示](https://git.oschina.net/vincent_zyt/PictureWarehouse/raw/master/permission/permission_show.gif)

引用：   
```
 api 'com.bintray.library:snowpermission:1.0.0'
```

使用方法很简单，就不做解释了：
```
 RequestPermissions.with(this)
                .permissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE})
                .onGranted(new ActionCallBack() {
                    @Override
                    public void callBack(List<String> strings) {
                        Log.e("qq", "======获取权限成功");
                    }
                })
                .onDenied(new ActionCallBack() {
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
