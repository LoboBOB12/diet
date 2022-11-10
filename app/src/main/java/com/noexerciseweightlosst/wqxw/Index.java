package com.noexerciseweightlosst.wqxw;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        final Button BMIButton = (Button) findViewById(R.id.BMIbutton);
        final Button MealPlanButton = (Button) findViewById(R.id.MealPlanButton);
        final Button ProfileButton = (Button) findViewById(R.id.ProfileButton);
        final Button FeedbackButton = (Button) findViewById(R.id.FeedbackButton);




        BMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmiIntent = new Intent(Index.this, BMI.class);
                Index.this.startActivity(bmiIntent);
            }
        });


        MealPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mealIntent = new Intent(Index.this, MealPlan.class);
                Index.this.startActivity(mealIntent);
            }
        });

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(Index.this, Profile.class);
                Index.this.startActivity(profileIntent);
            }
        });

        FeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PieChartIntent = new Intent(Index.this, Feedback.class);
                Index.this.startActivity(PieChartIntent);
            }
        });
    }
}
