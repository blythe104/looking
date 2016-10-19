package com.looking.classicalparty.lib.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.graphics.drawable.BuildConfig;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.looking.classicalparty.lib.constants.Config;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";

    private static OkHttpUtils mInstance;
    private static OkHttpClient mOkHttpClient;
    private static Handler mDelivery;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());
    }

    private synchronized static OkHttpUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }

    /**
     * get请求
     *
     * @param url      请求url
     * @param callback 请求回调
     */
    public static void get(String url, ResultCallback callback) {
        getmInstance().getRequest(url, callback);
    }

    /**
     * post请求
     *
     * @param url      请求url
     * @param callback 请求回调
     * @param params   请求参数
     */
    public static void post(String url, List<Param> params, ResultCallback callback) {
        getmInstance().postRequest(url, params, callback);
    }

    /**
     * 单独上传文件
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     */
    public static void postSingleFile(String url, File file, String fileKey, ResultCallback callback) {
        getmInstance().postFile(url, file, fileKey, null, callback);
    }

    /**
     * 文件和参数一起上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @param params
     */
    public static void postFileAndParams(String url, File file, String fileKey, List<Param> params, ResultCallback
            callback) {
        getmInstance().postFile(url, file, fileKey, params, callback);
    }

    private static void sendFailCallback(final Request request, final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(request, e);
                }
            }
        });
    }

    private static void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    private static String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }


    private void getRequest(String url, ResultCallback callback) {
        Request request = new Request.Builder().url(url).build();
        deliveryResult(callback, request);
    }

    private void postRequest(String url, List<Param> params, ResultCallback callback) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    private void postFile(String url, File file, String fileKey, List<Param> params, ResultCallback callback) {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        deliveryResult(callback, request);
    }

    private void deliveryResult(final ResultCallback callback, Request request) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailCallback(request, callback, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String str = response.body().string();
                    if (callback.mType == String.class) {
                        sendSuccessCallBack(callback, str);
                    } else {
                        Object object = new Gson().fromJson(str, callback.mType);
                        sendSuccessCallBack(callback, object);
                    }
                } catch (Exception e) {
                    LogUtils.e(TAG, "failure", e);
                    sendFailCallback(response.request(), callback, e);
                }

            }
        });
    }

    private Request buildPostRequest(String url, List<Param> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        builder.add("packageName",  BuildConfig.APPLICATION_ID).add("clientType", Config.ClientType);
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    /**********************
     * 对外接口
     ************************/

    private Request buildMultipartFormRequest(String url, File[] files, String[] fileKeys, List<Param> params) {
        params = validateParam(params);
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        for (Param param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key +
                    "\""), RequestBody.create(null, param.value));
        }
        builder.addFormDataPart("packageName", BuildConfig.APPLICATION_ID).addFormDataPart("clientType", Config.ClientType);
        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" +
                        fileKeys[i] + "\"; filename=\"" + fileName + "\""), fileBody);
            }
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private List<Param> validateParam(List<Param> params) {
        if (params == null)
            return new ArrayList<>();
        else
            return params;
    }


    /**
     * 下载文件
     *
     * @param url
     * @param destFileDir
     * @param callback
     */
    public static void downloadFile(final String url, final String destFileDir, final ResultCallback callback) {
        Request request = new Request.Builder().url(url).build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailCallback(request, callback, e);
            }


            @Override
            public void onResponse(Response response) {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(destFileDir, getFileName(url));
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    sendSuccessCallBack(callback, file.getAbsolutePath());
                } catch (IOException e) {
                    sendFailCallback(response.request(), callback, e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });

    }

    /**
     * http请求回调类,回调方法在UI线程中执行
     *
     * @param <T>
     */
    public static abstract class ResultCallback<T> {

        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         *
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         *
         * @param e
         */
        public abstract void onFailure(Request request, Exception e);

        /**
         * 服务器异常
         *
         * @param request
         * @param e
         */
        public abstract void onServiceError(Request request, Exception e);

        /**
         * 网络异常
         *
         * @param request
         * @param e
         */
        public abstract void onNoNetWork(Request request, Exception e);

        public void onLoading(long total, long current, boolean isUploading) {
        }
    }

    /**
     * post请求参数类
     */
    public static class Param {

        String key;
        String value;
        File filevalue;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Param(String key, File filevalue) {
            this.key = key;
            this.filevalue = filevalue;
        }
    }
}
