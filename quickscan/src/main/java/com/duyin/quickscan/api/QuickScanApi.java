package com.duyin.quickscan.api;

import android.content.Context;

import com.duyin.quickscan.baen.ScanResult;

import java.util.List;

import rx.Observable;

/**
 * 作者：zhangzhongping on 17/2/17 16:44
 * 邮箱：android_dy@163.com
 */
public class QuickScanApi {
    public static QuickScanApi quickScanApi;
    private QuickScanListener quickScanService;

    public QuickScanApi(QuickScanListener quickScanListener) {
        quickScanService = quickScanListener;
    }

    public static QuickScanApi getInstance(QuickScanListener quickScanListener) {
        if (quickScanApi == null)
            quickScanApi = new QuickScanApi(quickScanListener);
        return quickScanApi;
    }
    public Observable<List<ScanResult>> getAllResult(Context context,String end){
        return quickScanService.getAllResult(context,end);
    }
}
