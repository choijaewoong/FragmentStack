package com.torerov.fragmentstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Fragment[] list = { StackFragment.newInstance("one"),
                        StackFragment.newInstance("two"),
                        StackFragment.newInstance("three")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, StackFragment.newInstance("base")).commit();
        }

        Button btn = (Button)findViewById(R.id.btn_prev);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                    getSupportFragmentManager().popBackStack();
                }
            }
        });

        int count = 0;
        btn = (Button)findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getSupportFragmentManager().getBackStackEntryCount(); // backstack에 쌓인 개수
                if(count < list.length){
                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.container, list[count])
                            .add(R.id.container, list[count])   //레이아웃이 겹침
                            .addToBackStack(count++ + "")
                            .commit();
                }else{
                    getSupportFragmentManager().popBackStack("0", FragmentManager.POP_BACK_STACK_INCLUSIVE); // number 0까지만 빼
//                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });
    }
}
