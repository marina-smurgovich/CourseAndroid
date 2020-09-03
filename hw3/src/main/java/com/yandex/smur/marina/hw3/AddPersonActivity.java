package com.yandex.smur.marina.hw3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class AddPersonActivity extends AppCompatActivity {

    private EditText name;
    private EditText telOrEmail;
    private RadioGroup radioGroup;
    private ImageButton buttonBack;
    private ImageButton buttonSave;

    private int image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);


        name = findViewById(R.id.add_person_name);
        telOrEmail = findViewById(R.id.add_person_phone_email);

        buttonBack = findViewById(R.id.button_back_for_screen_add_person);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonSave = findViewById(R.id.button_done_for_screen_add_person);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent();
                radioGroup = findViewById(R.id.radio_group);
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if (checkedId == -1) {
                } else {
                    findRadioButton(checkedId);
                }

                Contact contact = new Contact(name.getText().toString(),
                        telOrEmail.getText().toString(), image);
                intent.putExtra(Contact.class.getSimpleName(), contact);
                setResult(RESULT_OK, intent);
                finish();
            }

            private void findRadioButton(int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_phone_number:
                        image = R.drawable.contact_phone;
                        break;
                    case R.id.radio_button_email:
                        image = R.drawable.contact_mail;
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

