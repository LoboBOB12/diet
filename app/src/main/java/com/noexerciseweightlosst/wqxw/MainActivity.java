package com.noexerciseweightlosst.wqxw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bMakeProfile = (Button) findViewById(R.id.makeProfileButton);
        final Button bLogin = (Button) findViewById(R.id.loginButton);
        final DatabaseManager dbm = new DatabaseManager(this);
        final SharedPreferences prefs = getDefaultSharedPreferences(this);

        //check username and password matches the database
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                Log.e("Inside button",username);

                try {
                    dbm.open();

                    //Cursor cursor = dbm.execsql.rawQuery("SELECT user_password FROM users WHERE user_username =?" , new String[]{username});
                    Cursor cursor = dbm.checkLogin(username);

                    if(cursor.getCount()<1) // UserName does not Exist
                    {
                        Toast.makeText(MainActivity.this, "Username or Password incorrect", Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else
                    {
                        cursor.moveToFirst();
                        String storedPassword = cursor.getString(cursor.getColumnIndex("user_password"));
                        int userId = cursor.getInt(cursor.getColumnIndex("_id"));
                        cursor.close();

                        // check if the password in the db matches with Password entered by user
                        if (password.equals(storedPassword))
                        {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            prefs.edit().putInt("_Id", userId).apply();

                            Intent intent = new Intent(MainActivity.this, Index.class);
                            MainActivity.this.startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Username or Password incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                    dbm.close();
                }
                catch(SQLException e){
                    Log.e("Error executing SQL…",
                            e.toString());
                }
            }

        });


        //Intent code to bring you to make profile page when you click the make profile button
        bMakeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this, MakeProfile.class);
                MainActivity.this.startActivity(profileIntent);
            }
        });



    }
}
