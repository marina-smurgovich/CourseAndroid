package com.yandex.smur.marina.hw3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class EditContactActivity extends AppCompatActivity {
    private EditText textName;
    private EditText textTelOrEmail;
    private int image;
    private Button buttonRemove;
    private ImageButton buttonSave;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        textName = findViewById(R.id.person_name_for_edit);
        textTelOrEmail = findViewById(R.id.phone_email_for_edit);

        textName.setText(getIntent().getExtras().getString("name"));
        textTelOrEmail.setText(getIntent().getExtras().getString("telOrEmail"));
        image = getIntent().getExtras().getInt("image");


        buttonRemove = findViewById(R.id.button_remove_contact);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtras(intent);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        buttonSave = findViewById(R.id.button_done_for_screen_edit_contact);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Contact contact = new Contact(textName.getText().toString(),
                        textTelOrEmail.getText().toString(), image);
                intent.putExtra(Contact.class.getSimpleName(), contact);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        buttonBack = findViewById(R.id.button_back_for_screen_edit_contact);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtras(intent);
                setResult(RESULT_FIRST_USER, intent);
                finish();
            }
        });
    }
}
