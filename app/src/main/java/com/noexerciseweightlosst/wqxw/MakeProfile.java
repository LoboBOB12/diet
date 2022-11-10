package com.noexerciseweightlosst.wqxw;

import android.database.SQLException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

    public class MakeProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        final EditText etUsername = (EditText) findViewById(R.id.etUN);
        final EditText etPassword = (EditText) findViewById(R.id.etPW);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etHeight = (EditText) findViewById(R.id.etHeight);
        final EditText etWeight = (EditText) findViewById(R.id.etWeight);
        final RadioGroup rgGender = (RadioGroup) findViewById(R.id.radioGender);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        //instance of DatabaseManager initialised
        final DatabaseManager dbm = new DatabaseManager(this);


        //when the user clicks the make profile button this stuff goes down
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation if all fields are empty
                if (  ( etUsername.getText().toString().equals("") )
                        || ( etPassword.getText().toString().equals("") )
                        || ( etAge.getText().toString().equals("") )
                        || ( etHeight.getText().toString().equals("") )
                        || ( etWeight.getText().toString().equals("") )
                        )
                {
                    //prints error message to user
                    Toast.makeText(MakeProfile.this, "Please complete all fields", Toast.LENGTH_LONG).show();


                }
                else {
                    final String UN = etUsername.getText().toString();
                    final String PW = etPassword.getText().toString();
                    final String Age = etAge.getText().toString();
                    final Float Height = Float.parseFloat(etHeight.getText().toString());
                    final Float Weight = Float.parseFloat(etWeight.getText().toString());
                    final String Gender = ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();


                    try {
                        dbm.open();
                        dbm.insertUser(UN, PW, Age, Height, Weight, Gender);
                        dbm.close();
                    }
                    catch(SQLException e){
                        Log.e("Error executing SQL…",
                                e.toString());
                    }
                    finish();
                }

            }
        });
    }
}
