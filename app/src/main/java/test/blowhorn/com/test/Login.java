package test.blowhorn.com.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText password,id;
    Button login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_login);
        password=(EditText) findViewById(R.id.password);
        id=(EditText) findViewById(R.id.loginid);
        login=(Button)findViewById(R.id.login);
        sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Boolean result= db.checkLogin(id.getText().toString(),password.getText().toString());
                Log.v("test123",""+result);
                if(result) {
                    //Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG);
                    Intent startMap=new Intent(getApplicationContext(),MapsActivity.class);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("loggedin","true");
                    editor.commit();
                    startActivity(startMap);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Login Fail",Toast.LENGTH_LONG);
            }
        });

    }
}
