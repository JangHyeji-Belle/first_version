package com.example.first_version.ui.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.first_version.R;

import java.util.ArrayList;

public class ListViewModel extends AppCompatActivity {

    ListView switchList, btnlist;

    ArrayList<String> dataSample;
    ArrayList<String> switchSample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSample = new ArrayList<String>();
        switchSample = new ArrayList<String>();

        switchSample.add("알림 설정");
        dataSample.add("버전 정보");
        dataSample.add("이용 약관");
        dataSample.add("개인정보처리방침");

        switchList = findViewById(R.id.mypageListview);
        btnlist = findViewById(R.id.mypageListview2);

        ButtonListAdapter buttonListAdapter = new ButtonListAdapter(this, switchSample);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataSample);

        // listView에 adapter 연결
        btnlist.setAdapter(adapter);
        switchList.setAdapter(buttonListAdapter);

    }
}
