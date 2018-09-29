package com.autotest.demo;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class HttpUtils {

    /**
     * get请求(用于key-value格式的参数)
     * @param url
     * @return
     */
    public static String doGet(String url,List<NameValuePair> list) {

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {

            String s = EntityUtils.toString(new UrlEncodedFormEntity(list,"utf-8"));

            System.out.println("键值对参数"+s);
            HttpGet httpGet = new HttpGet(url+'?'+s);
            //发送get请求
            httpGet.setHeader("Cookie","remember_web_59ba36addc2b2f9401580f014c7f58ea4e30989d=eyJpdiI6ImRKZUhUbWFTcWxjT3UzNk5VdjFFMHc9PSIsInZhbHVlIjoiY3ZGZXhEbzdBNmVVbDNMZWVvMk1Cb3FPQk00aGgrclwvXC83Q3AxT2ZtR1JTcFFRdEMxcDF6eFA4cHQ2TWVYOFFyOWM3UU1QZFJ5cEV0a0xoYXhvQ2pSRHBiYkRST01VdXdlWldybVNhR2xGdnpES1BDR25GQXZzOGlDNlFKQnRJMUgwVG5uUUpVN0ppYW9aN0JNZWhITHl2bkdMWG5OT01PWjhpSlhVV2xUVnc9IiwibWFjIjoiZDI0NDczZjg4NzVhMzEzMTQxMWYxMzBlZWIxMGZiZGQxYTM0YzBhOTViMTkxOTBkZWNkZGE4N2RkOGYwNTdiYSJ9; XSRF-TOKEN=eyJpdiI6ImdCOHY2YmRweVhQdWVPTjZEXC9LdVF3PT0iLCJ2YWx1ZSI6IlplQysrT21NOXdlUEs4SXU3SDh5ZTZaZDRwWmtuOFpMMnlXbEZKbzR6TFNTTmZ5TFA3d0V3ckJ6eFMxcVRtVU8iLCJtYWMiOiI3NDEzYjgyMjFlMjcyMTdhODljNTYxMzU0NzNiY2ZhMzk4ZTIwMzBkYjkwZGZhNzRhOGZhMjg1ZmJlYTQ4YmU2In0%3D; laravel_session=eyJpdiI6ImIxM25PRHVKTkhmMGRIZ1RpbzJtbUE9PSIsInZhbHVlIjoiQVwvTlI0bmlqMWhFMzhadVZBdGpXVHRya0VTWWJBeFwvWE5DUktybUZaRGNvMXA3SVNVWWtmcEw2TnNUYlpXOU56IiwibWFjIjoiMTQ2NWVkOTYyZmI5NWQzN2U0OTU2MmEzMzkyNzkyZjhiMDY3MDY2YmU0NDNkZDkwOThjYjM4ZDBhYjljZDBkNCJ9");
            response = client.execute(httpGet);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == 200) {
                // 读取服务器返回过来的json字符串数据
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println("结果是"+strResult);
                return strResult;
            }else
                System.out.println("请求状态"+response.getStatusLine().getStatusCode()) ;
            return null;

        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            //消耗实体内容
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭相应 丢弃http连接
            if(client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }

}
