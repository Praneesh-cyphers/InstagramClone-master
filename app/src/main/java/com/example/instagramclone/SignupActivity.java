package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText    edtEmail,edtUsername,edtPassword;
    private Button      btnSignup,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*for setting the title in the action bar*/
        setTitle("Sign-up");

        edtEmail = findViewById(R.id.editText1);
        edtUsername = findViewById(R.id.editText2);
        edtPassword = findViewById(R.id.editText3);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if((keyCode == KeyEvent.KEYCODE_ENTER) &&
                        ( KeyEvent.ACTION_DOWN == keyEvent.getAction())){
                    onClick(btnSignup);
                }
                return false;
            }
        });

        btnLogin = findViewById(R.id.button1);
        btnSignup = findViewById(R.id.button2);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:{

                if(edtEmail.getText().toString().equals("") ||
                    edtPassword.getText().toString().equals("")||
                        edtUsername.getText().toString().equals("")){

                          FancyToast.makeText(SignupActivity.this
                            , " Please Enter valid credential"
                            , FancyToast.LENGTH_SHORT
                            , FancyToast.INFO, true).show();
                }else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up" + edtUsername.getText().toString());
                    progressDialog.show();


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignupActivity.this
                                        , appUser.getUsername() + " is signed up"
                                        , FancyToast.LENGTH_SHORT
                                        , FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(SignupActivity.this
                                        , "There was an error: " + e.getMessage()
                                        , FancyToast.LENGTH_LONG
                                        , FancyToast.ERROR, true).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }
            }
                break;
            case R.id.button2:{
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
             }
            break;

        }
    }

    public void onRootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
