package ru.emba.cbs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CbsApp)
        super.onCreate(savedInstanceState)
    }
}