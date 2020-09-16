package com.yandex.smur.marina.task5

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity


class AddPersonActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var info: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonBack: ImageButton
    private lateinit var buttonSave: ImageButton
    private var image: Int? = null
    private var id: Double? = null
    private var count : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        name = findViewById(R.id.add_person_name)
        info = findViewById(R.id.add_person_info)

        buttonBack = findViewById(R.id.button_back_for_screen_add_person)
        buttonBack.setOnClickListener { finish() }

        buttonSave = findViewById(R.id.button_done_for_screen_add_person)
        buttonSave.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent()
                id = Math.random()
                radioGroup = findViewById(R.id.radio_group)
                val checkedId = radioGroup.checkedRadioButtonId
                if (checkedId == -1)
                else {
                    findRadioButton(checkedId)
                }
                val contact = Contact(id!!, name.text.toString(),
                        info.text.toString(), image!!)
                intent.putExtra(Contact::class.java.simpleName, contact)
                setResult(RESULT_OK, intent)
                finish()
            }

            private fun findRadioButton(checkedId: Int) {
                when (checkedId) {
                    R.id.radio_button_phone_number -> image = R.drawable.contact_phone
                    R.id.radio_button_email -> image = R.drawable.contact_email
                }
            }
        })
    }
}

