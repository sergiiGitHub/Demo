package com.example.sergii.xmlparserexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sergii.xmlparserexample.xmlparser.ItemsTagParser;
import com.example.sergii.xmlparserexample.xmlparser.XmlParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test( );
    }

    private void test() {
        XmlParser x = new XmlParser();
        x.parse(this, R.xml.items, new ItemsTagParser());
    }
}
