package com.wikison.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class ToastUtil {

    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast toast = null;

    private static Object synObj = new Object();

    public static void showMessage(final Context context, final String msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 根据设置的文本显示
     *
     * @param msg
     */
    public static void showMessage(final Context context,final int msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个文本并且设置时长
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final Context context, final CharSequence msg, final int len) {
        if (msg == null || msg.equals("")) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(context, msg, len);
                    }
                    toast.show();
                }
            }
        });
    }

    /**
     * 资源文件方式显示文本
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final Context context, final int msg, final int len) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        //toast.cancel();
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(context, msg, len);
                    }
                    toast.show();
                }
            }
        });
    }
}
