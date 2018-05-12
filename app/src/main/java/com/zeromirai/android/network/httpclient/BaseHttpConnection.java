package com.zeromirai.android.network.httpclient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by ASUS on 2018/5/7.
 */
public class BaseHttpConnection {

    public class ResponseEntity{
        public int code;
        public Map<String, List<String>> headerMap;
        public String redirectUrl;
        public String htmlBody;
        @Override
        public String toString(){
            return this.htmlBody;
        }
    }

    public static final String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";
    public static final String DEFAULT_CHARSET = "UTF-8";
    private ZRCookie zrCookie = new ZRCookie();
    private boolean instanceFollowRedirects;
    private boolean instanceCookieStorage;

    public BaseHttpConnection(){
        setInstanceFollowRedirects(true);
        setInstanceCookieStorage(true);
    }

    public void setInstanceFollowRedirects(boolean b){
        instanceFollowRedirects = b;
    }

    public void setInstanceCookieStorage(boolean b){
        instanceCookieStorage = b;
    }

    public ResponseEntity post(String url,SortedMap<String,String> params) throws Exception {

        URL loginUrl = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(instanceFollowRedirects);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("User-Agent",USERAGENT);

        if(instanceCookieStorage){
            List<String> cookieList = zrCookie.get(loginUrl);
            connection.setRequestProperty("Cookie",getFormattedCookie(cookieList));
        }


        StringBuilder paramsStringBuilder = new StringBuilder();
        for (String key:params.keySet()) {
            paramsStringBuilder
                    .append(URLEncoder.encode(key,DEFAULT_CHARSET))
                    .append('=')
                    .append(URLEncoder.encode(params.get(key),DEFAULT_CHARSET))
                    .append('&');
        }

        String requestBody = paramsStringBuilder.toString();
        PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
        printWriter.write(requestBody);
        printWriter.flush();

        Map<String, List<String>> map = connection.getHeaderFields();
        List<String> cookieList = map.get("Set-Cookie");
        if(cookieList != null){
            zrCookie.set(loginUrl,cookieList);
            //System.out.println(map.toString());
            //System.out.println(zrCookie.toString());
        }


        String body = readStringFromStream(connection.getInputStream());
        connection.disconnect();

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = connection.getResponseCode();
        responseEntity.headerMap = map;
        responseEntity.redirectUrl = null == map.get("Location") ? null : map.get("Location").get(0);
        responseEntity.htmlBody = body;

        return responseEntity;
    }

    public ResponseEntity get(String url) throws Exception {

        URL loginUrl = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();

        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(instanceFollowRedirects);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Content-Type", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        connection.setRequestProperty("User-Agent",USERAGENT);

        if(instanceCookieStorage){
            List<String> cookieList = zrCookie.get(loginUrl);
            connection.setRequestProperty("Cookie",getFormattedCookie(cookieList));
        }


        Map<String, List<String>> map = connection.getHeaderFields();
        List<String> cookieList = map.get("Set-Cookie");
        if(cookieList != null){
            zrCookie.set(loginUrl,cookieList);
            //System.out.println(map.toString());
            //System.out.println(zrCookie.toString());
        }
        String body = readStringFromStream(connection.getInputStream());
        connection.disconnect();

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = connection.getResponseCode();
        responseEntity.headerMap = map;
        responseEntity.redirectUrl = null == map.get("Location") ? null : map.get("Location").get(0);
        responseEntity.htmlBody = body;

        return responseEntity;
    }

    private String getFormattedCookie(List<String> cookieList){
        if(cookieList == null || cookieList.isEmpty()){
            return "";
        }
        int n = cookieList.size();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < n;i++){
            String item = cookieList.get(i);
            if(null != item && item.length() != 0 && item.contains(";") && item.contains("=")){
                stringBuilder.append(item.substring(0,item.indexOf(';'))).append(';');
            }else{
                System.out.println("CookieFormatError :\n"+item);
            }
        }
        return stringBuilder.toString();
    }

    private String readStringFromStream(InputStream is) throws IOException{
        if(is == null){
            return "";
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        StringBuilder responseContent = new StringBuilder();
        while(null != (s = br.readLine())){
            responseContent.append(s).append('\n');
        }
        return responseContent.toString();
    }

    public JSONObject getAllCookie(){
        return this.zrCookie.toJSONObject();
    }
}
