package com.yandex.smur.marina.task5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_person.*


class AddPersonActivity : AppCompatActivity() {

    private var image: Int? = null
    private var id: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)


        button_back_for_screen_add_person.setOnClickListener { finish() }

        button_done_for_screen_add_person.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent()
                id = Math.random()
                val checkedId = radio_group.checkedRadioButtonId
                if (checkedId == -1)
                else {
                    findRadioButton(checkedId)
                }
                val contact = Contact(id!!, add_person_name.text.toString(),
                        add_person_info.text.toString(), image!!)
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

