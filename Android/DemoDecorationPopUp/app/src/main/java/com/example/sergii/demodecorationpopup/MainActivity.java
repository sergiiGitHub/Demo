package com.example.sergii.demodecorationpopup;

import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animatable drawable = (Animatable) ((ImageView) findViewById(R.id.image)).getDrawable();
        final Animatable drawable2 = (Animatable) (findViewById(R.id.text)).getBackground();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable.start();
                drawable2.start();
            }
        });

    }




}
