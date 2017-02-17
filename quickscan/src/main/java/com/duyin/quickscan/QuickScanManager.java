package com.duyin.quickscan;

import android.app.Activity;
import android.content.Context;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.duyin.quickscan.api.QuickScanApi;
import com.duyin.quickscan.api.QuickScanService;
import com.duyin.quickscan.api.RxUtlis.RxSubscriber;
import com.duyin.quickscan.baen.ScanResult;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：zhangzhongping on 17/2/17 16:41
 * 邮箱：android_dy@163.com
 */
public class QuickScanManager {
    private static QuickScanManager quickScanManager = new QuickScanManager();
    private Activity activity;
    protected CompositeSubscription mCompositeSubscription;
    private QuickScanApi quickScanApi = QuickScanApi.getInstance(QuickScanService.getQuickScanService());
    protected MaterialDialog mMaterialDialog = null;

    private QuickScanManager(){

    }
    public static QuickScanManager getQuickScanManager(){
        return quickScanManager;
    }
    public QuickScanManager Init(Activity activity){
        this.activity = activity;
        return this;
    }
    public void getAllResult(final String end, final OnResultListener onResultListener){
        showload();
        Subscription typebook = quickScanApi.getAllResult(activity.getApplicationContext(), end)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<List<ScanResult>>() {
                    @Override
                    public void _onNext(List<ScanResult> liveInfos) {
                        if (liveInfos.size() > 0) {
                            onResultListener.ScanSuccess(liveInfos);
                        } else {
                            onResultListener.ScanError("暂无扫描到 "+end+" 数据");
                        }
                        delload();
                    }

                    @Override
                    public void _onError(String msg) {
                        onResultListener.ScanError(msg);
                        delload();
                    }
                });
        addSubscrebe(typebook);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
    public interface OnResultListener{
        void ScanSuccess(List<ScanResult> list);
        void ScanError(String msg);
    }
    protected void showload(){
        showProgressDeterminateDialog();
    }
    public void showProgressDeterminateDialog() {
        mMaterialDialog = new MaterialDialog.Builder(activity)
                .content("正在扫描中...")
                .progress(true, 0)
                .cancelable(false)
                .progressIndeterminateStyle(true)
                .show();
    }

    protected void delload(){
        if(mMaterialDialog!=null){
            mMaterialDialog.dismiss();
        }
    }
    public void remove(){
        unSubscribe();
        activity = null;
    }
}
