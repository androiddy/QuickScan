# QuickScan
手机文件快速扫描
基于getContentResolver实现手机文件扫描

1.使用技术 rxjava rxandroid material-dialogs


    public void button(View view){
        QuickScanManager.getQuickScanManager().Init(this).getAllResult(editText.getText().toString(), new QuickScanManager.OnResultListener() {
            @Override
            public void ScanSuccess(List<ScanResult> lists) {
                list.clear();
                for(ScanResult scanResult:lists){
                    list.add(scanResult.getName());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void ScanError(String msg) {
                Toast.makeText(getApplicationContext(),msg,0).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QuickScanManager.getQuickScanManager().remove();
    }

