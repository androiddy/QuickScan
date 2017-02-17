package com.duyin.quickscan.api;

import android.content.Context;

import com.duyin.quickscan.baen.ScanResult;

import java.util.List;

import rx.Observable;

/**
 * 作者：zhangzhongping on 17/2/17 16:48
 * 邮箱：android_dy@163.com
 */
public interface QuickScanListener {
    Observable<List<ScanResult>> getAllResult(Context context, String end);
}
