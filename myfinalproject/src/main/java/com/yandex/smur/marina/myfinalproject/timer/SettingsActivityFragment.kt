package com.yandex.smur.marina.myfinalproject.timer

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.yandex.smur.marina.myfinalproject.R


class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}