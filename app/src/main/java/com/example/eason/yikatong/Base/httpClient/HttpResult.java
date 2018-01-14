package com.example.eason.yikatong.Base.httpClient;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface HttpResult {
    void onSuccess(ResponseData retData);
    void onFailed();
}
