package com.yandex.smur.marina.task6_async

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    companion object {
        private val THREADPOOLEXECUTER_HANDLER = "THREADPOOLEXECUTER_HANDLER"
        private val COMPLETABLEFUTURE_THREADPOOLEXECUTOR = "COMPLETABLEFUTURE_THREADPOOLEXECUTOR"
        private val RXJAVA = "RXJAVA"
        private val PREF_SAVE_KEY = "ASYNC_TYPE_KEY"
        private val PREF_NAME = "asyncTypePref"
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        loadPrefSettings()
        setRadioGroupListener()

        buttonBackForScreenSetting.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                finish()
            }

        })
    }

    private fun setRadioGroupListener() {
        radioGroupSettings.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(radioGroup: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.radioButtonThreadPoolExecutorHandler) {
                    saveStorageAsyncType(THREADPOOLEXECUTER_HANDLER)
                } else if (checkedId == R.id.radioButtonCompletableFutureThreadPoolExecutor) {
                    saveStorageAsyncType(COMPLETABLEFUTURE_THREADPOOLEXECUTOR)
                } else if (checkedId == R.id.radioButtonRxJava) {
                    saveStorageAsyncType(RXJAVA)
                }
            }
        })
    }

    private fun saveStorageAsyncType(asyncType: String) {
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(PREF_SAVE_KEY, asyncType)
        editor.apply()
    }

    private fun loadPrefSettings() {
        val loadAsyncType = loadAsyncType()
        when {
            THREADPOOLEXECUTER_HANDLER.equals(loadAsyncType) -> {
                radioButtonThreadPoolExecutorHandler.isChecked = true
            }
            COMPLETABLEFUTURE_THREADPOOLEXECUTOR.equals(loadAsyncType) -> {
                radioButtonCompletableFutureThreadPoolExecutor.isChecked = true
            }
            RXJAVA.equals(loadAsyncType) -> {
                radioButtonRxJava.isChecked = true
            }
        }
    }

    private fun loadAsyncType(): String {
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sharedPreferences.getString(PREF_SAVE_KEY, THREADPOOLEXECUTER_HANDLER).toString()
    }
}