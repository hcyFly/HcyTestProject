package com.cn.burus.hcytestproject.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.cn.burus.hcytestproject.base.MyApplication;
import com.socks.library.KLog;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.TELEPHONY_SERVICE;
import static com.cn.burus.hcytestproject.activities.LoginActivity.TAG;

/**
 * Created by chengyou.huang on 2017/4/17.
 */

public class SystemInfo {

    /**
     * 生成设备唯一标识：IMEI、AndroidId、macAddress 三者拼接再 MD5
     *
     * @return
     */
    public static String generateUniqueDeviceId() {
        Context context = MyApplication.globalContext;
        String imei = "";
        String androidId = "";
        String macAddress = "";
        String ADDRESS_STR = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            try {
                imei = telephonyManager.getDeviceId();
                KLog.i(TAG, "android imei--" + imei);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver != null) {
            androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
            KLog.i(TAG, "android androidId--" + androidId);
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            macAddress = wifiManager.getConnectionInfo().getMacAddress();
            KLog.i(TAG, "android macAddress--" + macAddress);
        }

        StringBuilder longIdBuilder = new StringBuilder();
        if (imei != null) {
            longIdBuilder.append(imei);
        }
        if (androidId != null) {
            longIdBuilder.append(androidId);
        }
        if (macAddress != null) {
            longIdBuilder.append(macAddress);
        }
        try {
            ADDRESS_STR = getMD5(longIdBuilder.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        KLog.i(TAG, "android id md5--" + ADDRESS_STR);
        return ADDRESS_STR;
    }

    /**
     * String to md5
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 加密后的字符串
        byte[] by = str.getBytes("utf-8");
        byte[] digest = md5.digest(by);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte c : digest) {
            stringBuilder.append(String.format("%02x", c));
        }
        return stringBuilder.toString();
    }

    /**
     * 获取应用的缓存路径
     * 【注：有SDcard返回sd目录下缓存 否则为手机目录下缓存】
     *
     * @param context
     * @return 路径
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
