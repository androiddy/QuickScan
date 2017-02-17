package com.gather.kuaisu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.duyin.quickscan.QuickScanManager;
import com.duyin.quickscan.baen.ScanResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.list_item);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(arrayAdapter);

    }
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
}
