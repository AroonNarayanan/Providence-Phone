package com.sorrytale.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import java.lang.Exception

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        findViewById<CheckBox>(R.id.amazonEnabled).isChecked =
            prefs.getBoolean("amazonEnabled", false)
        findViewById<Button>(R.id.amazonSave).setOnClickListener {
            prefs.edit()
                .putBoolean("amazonEnabled", findViewById<CheckBox>(R.id.amazonEnabled).isChecked)
                .putLong("amazonDelay", getDelay(R.id.amazonDelay)).apply()
        }
    }

    private fun getDelay(element: Int): Long {
        var delay: Long = 1000
        try {
            delay = findViewById<EditText>(element).text.toString().toLong()
        } catch (ex: Exception) {
        }
        return delay
    }
}
