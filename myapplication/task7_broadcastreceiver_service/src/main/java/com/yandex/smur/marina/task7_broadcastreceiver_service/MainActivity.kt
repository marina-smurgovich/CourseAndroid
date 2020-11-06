package com.yandex.smur.marina.task7_broadcastreceiver_service


import android.content.*
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val myBroadcastReceiver : BroadcastReceiver = MyBroadcastReceiver()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var storageType: StorageType

    companion object{
        val APP_PREFERENCES : String = "mySetting"
        val KEY_RADIOBUTTON_INDEX : String = "SAVED_RADIO_BUTTON_INDEX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveSetting()
        loadSetting()

        val typeStorage = loadStorageType()
        storageType = StorageTypeConverter.fromStringToStorageType(typeStorage.toString())
        when(storageType) {
            StorageType.INTERNAL -> internal.isChecked = true
            StorageType.EXTERNAL -> external.isChecked = true
        }

        storageTypeListener ()
        createReceiver()
    }

    private fun createReceiver() {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        registerReceiver(myBroadcastReceiver, intentFilter)
    }

    private fun storageTypeListener() {
        radioGroup.setOnCheckedChangeListener { p0, checkedId ->
            when (checkedId) {
                R.id.internal -> storageType = StorageType.INTERNAL
                R.id.external -> storageType = StorageType.EXTERNAL
            }
            saveStorageType(storageType)
        }

    }

    private fun loadSetting() {
        val sharedPreferences = getSharedPreferences( APP_PREFERENCES, Context.MODE_PRIVATE)
        val saveRadioIndex = sharedPreferences.getInt( KEY_RADIOBUTTON_INDEX, 0)
        val savedCheckedRadioButton : RadioButton = radioGroup.getChildAt(saveRadioIndex) as RadioButton
        savedCheckedRadioButton.isChecked
    }

    private fun saveSetting() {
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
                val checkRadioButton : RadioButton = radioGroup.findViewById(checkedId)
                val checkedIndex = radioGroup.indexOfChild(checkRadioButton)

                savePrefences (KEY_RADIOBUTTON_INDEX, checkedIndex)
            }

        })
    }

    private fun savePrefences(key: String, checkedIndex: Int) {
        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, checkedIndex)
        editor.apply()
    }

    private fun saveStorageType(storageType: StorageType) {
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString(KEY_RADIOBUTTON_INDEX, StorageTypeConverter.fromStorageTypeToString(storageType))
        edit.apply()
    }


    private fun loadStorageType(): String? {
        sharedPreferences = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
        return sharedPreferences.getString(MainActivity.KEY_RADIOBUTTON_INDEX, StorageType.INTERNAL.toString())
    }


}
