package com.yandex.smur.marina.task8_weather.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.smur.marina.task8_weather.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingFragment : Fragment() {

    private val settings: Setting by lazy { Setting.getInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings.getSharedPreferences(activity?.applicationContext!!)
        settings.setSetting(switchView)
        showSwitchText()
        switchView.setOnClickListener {
            showSwitchText()
            settings.saveSetting(switchView)
        }
    }

    private fun showSwitchText() {
        if (switchView.isChecked) {
            switchView.text = "Celsius"
        } else {
            switchView.text = "Fahrenheit"
        }
    }

    companion object {
        const val TAG = "SettingFragment"
        fun getInstance() = SettingFragment()
    }
}

