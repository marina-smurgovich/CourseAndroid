package com.yandex.smur.marina.task5


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class EditContactActivity : AppCompatActivity() {
    private lateinit var etName : EditText
    private lateinit var etInfo : EditText
    private var image : Int? = null
    private var id : Double? = null
    private lateinit var btnRemove : Button
    private lateinit var btnSave : ImageButton
    private lateinit var btnBack : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        etName = findViewById(R.id.person_name_for_edit)
        etInfo = findViewById(R.id.info_for_edit)

        val contact: Contact = intent.getSerializableExtra("contact") as Contact

        etName.setText(contact.name)
        etInfo.setText(contact.info)
        image = contact.image
        id = contact.id


        btnRemove = findViewById(R.id.button_remove_contact)
        btnRemove.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent()
                intent.putExtra(Contact::class.java.simpleName, contact)
                setResult(RESULT_CANCELED, intent)
                finish()
            }

        })

        btnSave = findViewById(R.id.button_done_for_screen_edit_contact)
        btnSave.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent()
                val contact : Contact = Contact(id!!, etName.text.toString(), etInfo.text.toString(), image!!)
                intent.putExtra(Contact::class.java.simpleName, contact)
                setResult(RESULT_OK, intent)
                finish()
            }
        })

        btnBack = findViewById(R.id.button_back_for_screen_edit_contact)
        btnBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent()
                intent.putExtras(intent)
                setResult(RESULT_FIRST_USER)
                finish()
            }

        })
    }
}