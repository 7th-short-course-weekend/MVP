package com.rathana.mvp.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rathana.mvp.R;
import com.rathana.mvp.model.User;
import com.rathana.mvp.ui.login.mvp.LoginMVP;
import com.rathana.mvp.ui.login.mvp.LoginPresenter;
import com.rathana.mvp.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity
implements LoginMVP.View

{

    EditText name, password;
    Button btnLogin;
    ProgressBar progressBar;

    LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnLogin);
        progressBar=findViewById(R.id.progressBar);

        presenter=new LoginPresenter(this);

        btnLogin.setOnClickListener(v->{
            presenter.login(new User(
                    name.getText().toString(),
                    password.getText().toString()));
        });
    }

    @Override
    public void onSuccess(User user) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
