package com.app.recylercard.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.recylercard.R;

public class Main3Activity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv = (TextView) findViewById(R.id.tt);
        tv.setText("我是Git!!!");
    }
}
