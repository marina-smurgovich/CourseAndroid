package com.yandex.smur.marina.task6_async


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_contact.*


class EditContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        val contact: Contact = intent.getSerializableExtra("contact") as Contact

        editTextViewPersonNameForEdit.setText(contact.name)
        editTextViewInfoForEdit.setText(contact.info)

        buttonRemoveContact.setOnClickListener {
            val intent = Intent()
            intent.putExtra(Contact::class.java.simpleName, contact)
            setResult(RESULT_CANCELED, intent)
            finish()
        }

        buttonSaveForScreenEdiContact.setOnClickListener {
            val intent = Intent()
            val contact: Contact = Contact(contact.id, editTextViewPersonNameForEdit.text.toString(),
                    editTextViewInfoForEdit.text.toString(), contact.image)
            intent.putExtra(Contact::class.java.simpleName, contact)
            setResult(RESULT_OK, intent)
            finish()
        }

        buttonBackForScreenEditContact.setOnClickListener {
            val intent = Intent()
            intent.putExtras(intent)
            setResult(RESULT_FIRST_USER)
            finish()
        }
    }
}