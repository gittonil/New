package com.cuelogic.myapplication.network.networkmanager;

import com.cuelogic.myapplication.interfaces.HTTPRequestStatusListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;

import org.apache.http.Header;

import java.security.KeyStore;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by cuelogic on 31/08/15.
 */
public class HTTPRequestManager {

    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private final int CONNECTION_TIME_OUT = 60000;

    public void sendGETRequest(String relativeUrl, final HTTPRequestStatusListener httpRequestStatusListener) {

        asyncHttpClient.getThreadPool().shutdownNow();
        asyncHttpClient.setThreadPool((ThreadPoolExecutor) Executors.newFixedThreadPool(20));

        MySSLSocketFactory socketFactory = getSSLSocketFactory();
        if(socketFactory != null) {
            asyncHttpClient.setSSLSocketFactory(socketFactory);
        }

        asyncHttpClient.setTimeout(CONNECTION_TIME_OUT);

        asyncHttpClient.get("http://192.168.10.104/imageData.php", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headerInfo, byte[] responseData) {
                System.out.println("onSuccess STATUS CODE >> "+statusCode);
                System.out.println("RESPONSE DATA >> "+new String(responseData));
                httpRequestStatusListener.onHTTPResponseSuccess(statusCode, headerInfo, responseData);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("onFailure STATUS CODE >> "+statusCode);
                httpRequestStatusListener.onHTTPResponseFailure(statusCode, headers, responseBody, error);
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }

        });
    }

    private MySSLSocketFactory getSSLSocketFactory() {
        KeyStore trustStore = null;
        MySSLSocketFactory socketFactory = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            socketFactory = new MySSLSocketFactory(trustStore);
            socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return socketFactory;
    }

}


