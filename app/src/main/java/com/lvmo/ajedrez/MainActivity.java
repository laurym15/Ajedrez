package com.lvmo.ajedrez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {
ExtendedFloatingActionButton fab,fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab=findViewById(R.id.extended_fab);
        fab1=findViewById(R.id.extended_fab1);
        fab.shrink();
        fab1.shrink();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtendedFloatingActionButton extFab =(ExtendedFloatingActionButton)v;
                if(extFab.isExtended())
                {
                    extFab.shrink();
                }else
                {extFab.extend();
                }
            }
        };
        fab.setOnClickListener(clickListener);
        fab1.setOnClickListener(clickListener);
    }
}