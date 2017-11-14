package com.ts.farm.utils;
//修改测试
import com.android.volley.RequestQueue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    public static String DOMAIN;
    public static String KEY = "lll";
    public static String KEY_DATA = "ddd";
    public static final String NET_ERROR = "网络异常,无法连接到服务器";
    private static final String TAG = "DataHandler";
    public static String URL_DOMAIN;
    public static String URL_DOWNURL;
    public static final String WEB_RECHARGE = "/front/account/rechargeApp";
    private static RequestQueue mRequestQueue;
    private static Map<String, String> parameters;
    public static boolean update = false;
    public static String tag = DataHandler.class.getName();

    static {
        // DOMAIN = "http://www.hfqianbao.com";
        DOMAIN = "http://123.56.176.224:10789";
    }

    public static Map<String, String> getParameters(String paramString) {

        if (parameters == null)
            parameters = new HashMap();
        parameters.clear();
        URL_DOMAIN  = DOMAIN + paramString;
        return parameters;
    }


    public static String getBuildUrl(Map<String, String> paramMap) {
        try {
            HashMap localHashMap = new HashMap();
            localHashMap.putAll(paramMap);
            String str = buildUrl(URL_DOMAIN, KEY,
                    localHashMap);
            return str;
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return "";
    }

    public static String buildUrl(String urlBase, String key, Map parameters)
            throws UnsupportedEncodingException {
        if (parameters.containsKey("_s") || parameters.containsKey("_t")) {
            throw new RuntimeException("");
        }
        if (key==null||key.length()==0) {
            throw new RuntimeException("");
        }

        List parameterNames = new ArrayList(parameters.keySet());
        Collections.sort(parameterNames);
        if (!urlBase.endsWith("?") && !urlBase.endsWith("&")) {
            urlBase = (new StringBuilder(String.valueOf(urlBase))).append(
                    urlBase.indexOf("?") != -1 ? "&" : "?").toString();
        }
        String signData = "";
        for (int i = 0; i < parameters.size(); i++) {
            String _key = (String) parameterNames.get(i);
            String _value = (String) parameters.get(_key);
            signData = (new StringBuilder(String.valueOf(signData)))
                    .append(_key).append("=").append(_value).toString();
            urlBase = (new StringBuilder(String.valueOf(urlBase))).append(_key)
                    .append("=").append(URLEncoder.encode(_value, "utf-8"))
                    .toString();
            if (i < parameters.size() - 1) {
                signData = (new StringBuilder(String.valueOf(signData)))
                        .append("&").toString();
                urlBase = (new StringBuilder(String.valueOf(urlBase))).append(
                        "&").toString();
            }
        }

        return urlBase;
    }

}
