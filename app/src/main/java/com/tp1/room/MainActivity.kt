package com.tp1.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
    }


    override fun onSupportNavigateUp(): Boolean {
        val navcon = findNavController(R.id.fragmentContainerView)
        return navcon.navigateUp() || super.onSupportNavigateUp()
    }

}