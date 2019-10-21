package com.snow.rpermission;

import java.util.List;

/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-10-16
 * desc   :
 */
public interface BridgePermissionCall {
    void requestCall(int flag, List<String> callPermissions);
}
