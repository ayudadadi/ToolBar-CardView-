package com.example.yls.demoa;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout mBottomSheet;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    private boolean isOpen =false;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private BottomNavigationBar bottomNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomSheet =findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e(TAG,"newState = "+newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e(TAG,"slideOffset = "+slideOffset);
            }
        });
        initBottomNavigationBar();
        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MyBottomSheetDialogFragment().show(getSupportFragmentManager(),"poem");

/*                BottomSheetDialog bottomSheetDialog =new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.poem);
                bottomSheetDialog.show();*/
                /*
                if(isOpen){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                isOpen =!isOpen;
                */
            }
        });
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"tv1 clicked",Toast.LENGTH_SHORT).show();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"tv2 clicked",Toast.LENGTH_SHORT).show();
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"tv3 clicked",Toast.LENGTH_SHORT).show();
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"tv4 clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBottomNavigationBar() {
        bottomNavigationBar = findViewById(R.id.id_bnb);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.up,"A")
                .setActiveColor(Color.RED));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.up,"B")
                .setActiveColor(Color.BLUE));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.up,"C")
                .setActiveColor(Color.GREEN));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.up,"D")
                .setActiveColor(Color.YELLOW));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.up,"E")
                .setActiveColor(Color.BLACK));
        bottomNavigationBar.initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                super.onTabSelected(position);
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this, "A", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Toast.makeText(MainActivity.this, "B", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        Toast.makeText(MainActivity.this, "C", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "D", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabReselected(int position) {
                super.onTabReselected(position);
            }

            @Override
            public void onTabUnselected(int position) {
                super.onTabUnselected(position);
            }
        });
    }
}
