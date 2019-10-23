# RequestPermissionDemo
权限申请封装依赖库

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
