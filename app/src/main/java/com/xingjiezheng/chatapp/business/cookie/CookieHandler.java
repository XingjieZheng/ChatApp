package com.xingjiezheng.chatapp.business.cookie;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by XingjieZheng
 * on 2016/7/1.
 */
public class CookieHandler implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            String userId = null;
            String token = null;
            for (Cookie cookie : cookies) {
                if (CookieManager.COOKIE_USER_ID.equals(cookie.name())) {
                    userId = cookie.value();
                } else if (CookieManager.COOKIE_TOKEN.equals(cookie.name())) {
                    token = cookie.value();
                }
            }
            if (userId == null || token == null) {
                LogUtils.LOGE("saveFromResponse()", "Error, userId or token is null!");
            } else {
                CookieManager.getInstance().saveCookieList(cookies, userId, token);
            }
        }
        printCookieList("response", cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        String urlString = url.url().getFile();
        if (urlString != null && urlString.contains(ApiService.loginApi)) {
            //login
            return new ArrayList<>(0);
        } else {
            //request with cookie
            List<Cookie> cookieList = CookieManager.getInstance().getCookieList();
            if (cookieList == null) {
                //todo local cookie list is null, need to re login
                cookieList = new ArrayList<>(0);
            }
            printCookieList("request", cookieList);
            return cookieList;
        }
    }

    private void printCookieList(String from, List<Cookie> cookieList) {
        if (cookieList != null && cookieList.size() > 0) {
            String userId = null;
            String token = null;
            for (Cookie cookie : cookieList) {
                if (CookieManager.COOKIE_USER_ID.equals(cookie.name())) {
                    userId = cookie.value();
                } else if (CookieManager.COOKIE_TOKEN.equals(cookie.name())) {
                    token = cookie.value();
                }
            }
            LogUtils.LOGE(from, "userId: " + userId + " token: " + token);
        }
    }
}
