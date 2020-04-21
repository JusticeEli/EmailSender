package com.justice.emailsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText subjectEdtTxt, composeEdtTxt, emailsEdtTxt;
    private Button button;

    private String subject, compose;
    private String[] emailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBtnTapped();
            }
        });
    }

    private void submitBtnTapped() {
        if (subjectEdtTxt.getText().toString().trim().isEmpty() || composeEdtTxt.getText().toString().trim().isEmpty() || emailsEdtTxt.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            return;
        }
        subject = subjectEdtTxt.getText().toString().trim();
        compose = composeEdtTxt.getText().toString().trim();
        emailsList = getEmailsList(emailsEdtTxt.getText().toString().trim());


        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,emailsList);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,compose);

        startActivity(Intent.createChooser(intent,"Choose and app for sending the Email"));


    }

    private String[] getEmailsList(String emails) {

        String[] emailsList = emails.split(",");
        return emailsList;
    }

    private void initWidgets() {
        subjectEdtTxt = findViewById(R.id.subjectEdtTxt);
        composeEdtTxt = findViewById(R.id.composeEmailEdtTxt);
        emailsEdtTxt = findViewById(R.id.emailEdtTxt);
        button = findViewById(R.id.button);

    }
}
