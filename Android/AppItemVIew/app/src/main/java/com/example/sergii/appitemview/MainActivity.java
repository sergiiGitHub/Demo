package com.example.sergii.appitemview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sergii.appitemview.databinding.BaseItemBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseItemBinding binding = DataBindingUtil.setContentView(this, R.layout.base_item);
        binding.setBasicItem(new BasicItem());
    }
}
