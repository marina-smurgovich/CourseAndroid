package com.yandex.smur.marina.task6_async

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

        private val THREADPOOLEXECUTER_HANDLER = "THREADPOOLEXECUTER_HANDLER"
        private val COMPLETABLEFUTURE_THREADPOOLEXECUTOR = "COMPLETABLEFUTURE_THREADPOOLEXECUTOR"
        private val RXJAVA = "RXJAVA"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var str = ""

        radioGroupSettings.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.radioButtonThreadPoolExecutorHandler) {
               str = THREADPOOLEXECUTER_HANDLER
            }
            else if (checkedId == R.id.radioButtonCompletableFutureThreadPoolExecutor) {
                str = COMPLETABLEFUTURE_THREADPOOLEXECUTOR
            }
            else if (checkedId == R.id.radioButtonRxJava) {
                str = RXJAVA
            }
        }

        buttonBackForScreenSetting.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("async", str)
            startActivity(intent)
        }
    }
}