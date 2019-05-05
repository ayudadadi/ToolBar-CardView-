package com.example.yls.demoa;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CardView cardView;
    private int count =0;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_tb);
        setSupportActionBar(toolbar);
        floatingActionButton =findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(floatingActionButton, "Hello Snack", Snackbar.LENGTH_LONG)
                        .setAction("点击", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Hi , I'm toly", Toast.LENGTH_SHORT).show();

                            }
                        }).show();
            }
        });
        cardView = findViewById(R.id.id_cv);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runAnima();
            }
        });

    }
    private void runAnima() {
        cardView.animate().translationX(100 + count).setDuration(1000).start();
        count += 10;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_add:
                Toast.makeText(MainActivity.this, "tab_add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_about:
                Toast.makeText(MainActivity.this, "about",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tab_before:
                Toast.makeText(MainActivity.this, "before",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
