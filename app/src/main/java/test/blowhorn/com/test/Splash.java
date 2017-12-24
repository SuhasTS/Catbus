package test.blowhorn.com.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String temp=sharedpreferences.getString("loggedin","false");
        if(temp.equals("true")) {
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
            finish();
        }
    }
}
