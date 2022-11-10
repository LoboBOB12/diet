package com.noexerciseweightlosst.wqxw;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BMI extends AppCompatActivity {
    TextView t1, t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Integer userId = prefs.getInt("_Id", -1);
        final DatabaseManager dbm = new DatabaseManager(this);

        t1= (TextView)findViewById(R.id.textViewBMI);
        t2= (TextView)findViewById(R.id.textViewStatus);

        try {
            dbm.open();

            Cursor cursor = dbm.getUser(userId);

            Float storedHeight = cursor.getFloat(cursor.getColumnIndex("user_height"));
            Float storedWeight = cursor.getFloat(cursor.getColumnIndex("user_weight"));
            cursor.close();

            dbm.close();

            Float bmivalue = (storedWeight/(storedHeight*storedHeight));

            String bmistatus;
            String s = String.format("%.1f", bmivalue);
            t1.setText(s);

            if(bmivalue<18.5){
                bmistatus = "Underweight";
                t2.setText(bmistatus);
            }
            else if(bmivalue<24.9){
                bmistatus = "Healthy";
                t2.setText(bmistatus);
            }
            else if(bmivalue<29.9){
                bmistatus = "Overweight";
                t2.setText(bmistatus);
            }
            else{
                bmistatus = "Obese";
                t2.setText(bmistatus);
            }
        }
        catch(SQLException e){
            Log.e("Error executing SQL…",
                    e.toString());
        }



    }
}
