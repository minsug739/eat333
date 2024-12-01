package com.example.eat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class bmi_bmrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi_bmr);

        EditText weightInput = findViewById(R.id.weight);
        EditText heightInput = findViewById(R.id.height);
        EditText ageInput = findViewById(R.id.age);
        Spinner genderSpinner = findViewById(R.id.gender);
        Button bmi_bmrButton = findViewById(R.id.bmi_bmrbtn);
        TextView bmiResult = findViewById(R.id.bmiResult);
        TextView bmiStatus = findViewById(R.id.bmiStatus);
        TextView bmrResult = findViewById(R.id.bmrResult);

        Button btnDietRecommendation = findViewById(R.id.btn_diet_recommendation);

        btnDietRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bmi_bmrActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bmi_bmrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();
                String ageStr = ageInput.getText().toString();

                if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(bmi_bmrActivity.this, "모든 값을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    double weight = Double.parseDouble(weightStr);
                    double height = Double.parseDouble(heightStr) / 100;
                    int age = Integer.parseInt(ageStr);
                    double bmi = weight / (height * height);

                    String gender = genderSpinner.getSelectedItem().toString();
                    String status = getBmiStatus(bmi, gender, age);
                    double bmr = BMR(weight, height, age, gender);



                    bmiResult.setText(String.format("결과: %.2f", bmi));
                    bmiResult.setVisibility(View.VISIBLE);
                    bmiStatus.setText(String.format("상태 : %s", status));
                    bmiStatus.setVisibility(View.VISIBLE);
                    bmrResult.setText(String.format("기초대사량: %.2f kcal", bmr));
                    bmrResult.setVisibility(View.VISIBLE);

                } catch (NumberFormatException e) {
                    Toast.makeText(bmi_bmrActivity.this, "숫자를 올바르게 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }

            private double BMR(double weight, double height, int age, String gender) {
                double bmr = 0;

                if (gender.equals("남성")) {
                    bmr = 88.362 + (13.397 * weight) + (4.799 * height * 100) - (5.677 * age);
                } else if (gender.equals("여성")) {
                    bmr = 447.593 + (9.247 * weight) + (3.098 * height * 100) - (4.330 * age);
                }

                return bmr;
            }




        });
    }

    private String getBmiStatus(double bmi, String gender, int age) {
        if (gender.equals("남성")) {
            if (age < 20) {

                if (bmi < 18.5) {
                    return "저체중";
                } else if (bmi < 23) {
                    return "정상";
                } else if (bmi < 28) {
                    return "과체중";
                } else {
                    return "비만";
                }
            } else if (age >= 20 && age < 50) {
                if (bmi < 18.5) {
                    return "저체중";
                } else if (bmi < 25) {
                    return "정상";
                } else if (bmi < 30) {
                    return "과체중";
                } else {
                    return "비만";
                }
            } else {
                // 50세 이상 남성
                if (bmi < 18.5) {
                    return "저체중";
                } else if (bmi < 24) {
                    return "정상";
                } else if (bmi < 29) {
                    return "과체중";
                } else {
                    return "비만";
                }
            }
        } else if (gender.equals("여성")) {

            if (age < 20) {
                if (bmi < 17.0) {
                    return "저체중";
                } else if (bmi < 22) {
                    return "정상";
                } else if (bmi < 27) {
                    return "과체중";
                } else {
                    return "비만";
                }
            } else if (age >= 20 && age < 50) {
                if (bmi < 17.0) {
                    return "저체중";
                } else if (bmi < 24) {
                    return "정상";
                } else if (bmi < 29) {
                    return "과체중";
                } else {
                    return "비만";
                }
            } else {
                if (bmi < 18.0) {
                    return "저체중";
                } else if (bmi < 23) {
                    return "정상";
                } else if (bmi < 28) {
                    return "과체중";
                } else {
                    return "비만";
                }
            }
        }
        return gender;
    }
}



