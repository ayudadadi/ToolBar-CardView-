package com.example.yls.drawlayout;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private TextView t_ct;
    private LinearLayout id_ll_left;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.id_dl_root);
        t_ct = findViewById(R.id.text_ct);
        id_ll_left = findViewById(R.id.id_ll_left);
        navigationView = findViewById(R.id.id_nv_root);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.icon_a:
                        Toast.makeText(MainActivity.this,"about clieked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                id_ll_left.setRotation(360*slideOffset);
                t_ct.setScaleX(2*slideOffset+1);
                t_ct.setScaleY(2*slideOffset+1);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        findViewById(R.id.b_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(Gravity.START);
                t_ct.setText("左侧按钮被点击了");
            }
        });
        findViewById(R.id.o_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        findViewById(R.id.o_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.END);
            }
        });
    }
}
