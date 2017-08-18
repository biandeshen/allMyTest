package com.bft.spider;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.params.HttpParamsNames;

/**
 * 网页下载并处理，如何设置网络 设置请求超时策略等
 * 
 * @author fanjiangpan
 *
 */
public class DownLoadFile {
    /**
     * 根据URL和网页类型生成需要保存的网页的文件名，去除URL 中的非文件名字符
     */
    public String getFileNameByUrl(String url, String contentType) {
        // 移除http:
        url = url.substring(7);
        // text/html类型
        if (contentType.indexOf("html") != -1) {
            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
            return url;
        }
        // 如application/PDF 类型
        else {
            return url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
        }

    }

    /**
     * 保存网页字节数数组到本地文件，filePath 为要保存的文件的相对地址
     */
    private void saveToLocal(byte[] data, String filePath) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i = 0; i < data.length; i++) {
                outputStream.write(data[i]);
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 下载URL指向的网页
    public String downloadFile(String url) {
        String filePath = null;
        // 1.生成HttpClient对象并设置参数
        HttpClient httpClient = new HttpClient();
        // 设置HTTP连接超时5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        // 2.生成GetMethod对象并设置参数
        GetMethod getMethod = new GetMethod(url);
        // 设置get请求超时5秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 设置请求重试处理
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        // 3.执行HTTP GET请求
        try {
            int stausCode = httpClient.executeMethod(getMethod);
            // 判断访问的状态h码
            if (stausCode != HttpStatus.SC_OK) {
                System.err.println("Method failed:" + getMethod.getStatusLine());
                filePath = null;
            }

            // 4.处理HTTP响应内容
            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
            // 根据网页URL生成保存时的文件名
            filePath = "temp\\" + getFileNameByUrl(url, getMethod.getResponseHeader("Content-Type").getValue());
            saveToLocal(responseBody, filePath);
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            e.printStackTrace();
        } finally {
            // 释放连接
            getMethod.releaseConnection();
        }
        return filePath;
    }
}
