package com.example.lucky.reviewbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.lucky.reviewbase.fragment.AnOtherRightFragment;
import com.example.lucky.reviewbase.fragment.RightFragment;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFrameLayout;
    private Button fragment_button;

    public static final String TAG = "FragmentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmnet);

        mFrameLayout = findViewById(R.id.frame_layout);

        fragment_button = findViewById(R.id.fragment_button);

        fragment_button.setOnClickListener(this);
        replaceFragment(new RightFragment());

        Log.d(TAG, "onCreate: 11111111111111111111111");


    }

    @Override
    public void onClick(View view) {
        replaceFragment(new AnOtherRightFragment());
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void getFragment(){

    }

}
