package com.noexerciseweightlosst.wqxw;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MealPlan extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Integer userId = prefs.getInt("_Id", -1);
        final DatabaseManager dbm = new DatabaseManager(this);

        t1= (TextView)findViewById(R.id.BMI2);
        t2= (TextView)findViewById(R.id.BF2);
        t3= (TextView)findViewById(R.id.S2);
        t4= (TextView)findViewById(R.id.L2);
        t5= (TextView)findViewById(R.id.D2);
        t6= (TextView)findViewById(R.id.W2);

        try {
            dbm.open();
            Cursor cursor = dbm.getUser(userId);

            Float storedHeight = cursor.getFloat(cursor.getColumnIndex("user_height"));
            Float storedWeight = cursor.getFloat(cursor.getColumnIndex("user_weight"));

            cursor.close();
            dbm.close();

            Float bmivalue = (storedWeight/(storedHeight*storedHeight));
            String bmistatus;

            if(bmivalue<18.5){
                bmistatus = "Underweight";
                t1.setText(bmistatus);

                t2.setText("Protein Porridge");
                t3.setText("Banana and an energy bar");
                t4.setText("Boojum Burrito");
                t5.setText("Rauls Chicken Tikka");
                t6.setText(">2 Litres");
            }
            else if(bmivalue<24.9){
                bmistatus = "Healthy";
                t1.setText(bmistatus);

                t2.setText("Porridge & Berries");
                t3.setText("Dark Chocolate Rice Cakes");
                t4.setText("Tangine and couscous");
                t5.setText("Spaghetti Bolognese");
                t6.setText(">2.5 Litres");
            }
            else if(bmivalue<29.9){
                bmistatus = "Overweight";
                t1.setText(bmistatus);

                t2.setText("Creamy Oatmeal");
                t3.setText("Nuts and Seedsr");
                t4.setText("Chicken Salad");
                t5.setText("Homemade Pizza");
                t6.setText(">3 Litres");
            }
            else{
                bmistatus = "Obese";
                t1.setText(bmistatus);

                t2.setText("Green Smoothie");
                t3.setText("Carrots and Hummus");
                t4.setText("Spinach Salad");
                t5.setText("Vegetarian Lasagne");
                t6.setText(">3.5 Litres");
            }
        }
        catch(SQLException e){
            Log.e("Error executing SQL…",
                    e.toString());
        }

    }
}
