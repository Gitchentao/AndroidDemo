package com.example.lucky.reviewbase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucky.reviewbase.adapterUtils.FileUtil;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private EditText accountName;
    private EditText passwordEdit;
    private Button btnSigin;
    private CheckBox remember_pass;

    private FileUtil fileUtil;
    private SharedPreferences shared = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fileUtil = new FileUtil(this);
        //   String str = fileUtil.loadFile();
        accountName = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);
        btnSigin = findViewById(R.id.email_sign_in_button);
        remember_pass = findViewById(R.id.remember_pass);

        shared = PreferenceManager.getDefaultSharedPreferences(this);


        // accountName.setText(str);

        boolean isRemember = shared.getBoolean("remeber_pass", false);
        if (isRemember) {
            String account = shared.getString("account", "");
            String password = shared.getString("password", "");
            accountName.setText(account);
            passwordEdit.setText(password);
            remember_pass.setChecked(true);
        }

        btnSigin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = accountName.getText().toString();
                String password = passwordEdit.getText().toString();


                if (name.equals("admin") && password.equals("123456")) {
                    editor = shared.edit();
                    Intent intent = new Intent(LoginActivity.this, DownloadActivity.class);
                    startActivity(intent);
                    if (remember_pass.isChecked()) {
                        editor.putString("account", name);
                        editor.putString("password", password);
                        editor.putBoolean("remeber_pass", true);
                        editor.apply();

                    } else {
                        editor.clear();
                    }
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   fileUtil.saveData(accountName.getText().toString());

    }
}

