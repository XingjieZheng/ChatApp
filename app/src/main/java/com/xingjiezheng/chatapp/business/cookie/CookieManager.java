package com.xingjiezheng.chatapp.business.cookie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xingjiezheng.chatapp.business.account.AccountManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;

/**
 * Created by XingjieZheng
 * on 2016/7/1.
 */
public class CookieManager {

    public static final String COOKIE_USER_ID = "cookie_user_id";
    public static final String COOKIE_TOKEN = "cookie_token";

    private static CookieManager instance;

    private CookieManager() {

    }

    public static CookieManager getInstance() {
        if (instance == null) {
            synchronized (CookieManager.class) {
                if (instance == null) {
                    instance = new CookieManager();
                }
            }
        }
        return instance;
    }

    public List<Cookie> getCookieList() {
        if (AccountManager.getInstance().getLoginAccount() == null) {
            return null;
        }
        return AccountManager.getInstance().getLoginAccount().getCookieList();
    }

    public void saveCookieList(List<Cookie> cookieList, int userId, String token) {

        AccountManager.getInstance().saveLoginAccountCookieList(userId, token, cookieList);

    }

    public String getCookieListJson(List<Cookie> cookieList) {
        if (cookieList == null || cookieList.size() == 0) {
            return null;
        } else {
            Gson gson = new Gson();
            return gson.toJson(cookieList);
        }
    }

    public List<Cookie> getCookieListFromJson(String json) {
        if (json == null || json.equals("")) {
            return new ArrayList<>(0);
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cookie>>() {
            }.getType();
            return gson.fromJson(json, type);
        }
    }

    public String getCookieToken(List<Cookie> cookieList) {
        String token = null;
        if (cookieList != null && cookieList.size() > 0) {
            for (Cookie cookie : cookieList) {
                if (CookieManager.COOKIE_TOKEN.equals(cookie.name())) {
                    token = cookie.value();
                }
            }
        }
        return token;
    }
}
