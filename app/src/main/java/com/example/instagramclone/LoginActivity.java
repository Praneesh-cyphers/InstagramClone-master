package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText    edtLoginEmail,edtLoginPassword;
    private Button      btnLoginActivity,btnSignupActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log-In");

        edtLoginEmail = findViewById(R.id.editText4);
        edtLoginPassword = findViewById(R.id.editText5);

        btnLoginActivity = findViewById(R.id.button3);
        btnSignupActivity = findViewById(R.id.button4);

        btnLoginActivity.setOnClickListener(this);
        btnSignupActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.button3:{
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Login");
                progressDialog.show();

                ParseUser.logInInBackground(edtLoginEmail.getText().toString()
                        , edtLoginPassword.getText().toString()
                        , new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user != null && e == null){
                                    FancyToast.makeText(LoginActivity.this
                                            ,user.getUsername()+" is logged in"
                                            ,FancyToast.LENGTH_SHORT
                                            ,FancyToast.SUCCESS,true).show();
                                }else{
                                    FancyToast.makeText(LoginActivity.this
                                            ,"Error while logged in "+e.getMessage()
                                            ,FancyToast.LENGTH_LONG
                                            ,FancyToast.ERROR,true).show();

                                }
                                progressDialog.dismiss();
                            }
                        });

            }
                break;
            case R.id.button4:{

                finish();

            }
                break;
        }

    }
}
