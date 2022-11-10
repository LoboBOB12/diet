package com.noexerciseweightlosst.wqxw;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView t1, t2, t3, t4, t6, t7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Integer userId = prefs.getInt("_Id", -1);
        final DatabaseManager dbm = new DatabaseManager(this);

        t1= (TextView)findViewById(R.id.dbUN);
        t2= (TextView)findViewById(R.id.dbH);
        t3= (TextView)findViewById(R.id.dbW);
        t4= (TextView)findViewById(R.id.dbA);
        t6= (TextView)findViewById(R.id.dbBMI);
        t7= (TextView)findViewById(R.id.dbStatus);

        try {
            dbm.open();
            Cursor cursor = dbm.getUser(userId);

            String storedUsername = cursor.getString(cursor.getColumnIndex("user_username"));
            Float storedHeight = cursor.getFloat(cursor.getColumnIndex("user_height"));
            Float storedWeight = cursor.getFloat(cursor.getColumnIndex("user_weight"));
            Integer storedAge = cursor.getInt(cursor.getColumnIndex("user_age"));

            cursor.close();
            dbm.close();


            t1.setText(storedUsername);
            t2.setText(String.format("%.2f", storedHeight));
            t3.setText(String.format("%.2f", storedWeight));
            t4.setText(String.format("%d", storedAge));

            Float bmivalue = (storedWeight/(storedHeight*storedHeight));
            String bmistatus;
            String s = String.format("%.1f", bmivalue);
            t6.setText(s);

            if(bmivalue<18.5){
                bmistatus = "Underweight";
                t7.setText(bmistatus);
            }
            else if(bmivalue<24.9){
                bmistatus = "Healthy";
                t7.setText(bmistatus);
            }
            else if(bmivalue<29.9){
                bmistatus = "Overweight";
                t7.setText(bmistatus);
            }
            else{
                bmistatus = "Obese";
                t7.setText(bmistatus);
            }
        }
        catch(SQLException e){
            Log.e("Error executing SQL…",
                    e.toString());
        }
    }
}
