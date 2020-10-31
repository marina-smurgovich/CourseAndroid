package com.yandex.smur.marina.myfinalproject.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        buttonBackFromActivitySettingTimer.setOnClickListener {
            finish()
        }
    }
}
