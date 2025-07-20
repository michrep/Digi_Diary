package com.example.digi_diary

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.digi_diary.Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        
        // Set up window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Set up navigation
        setupNavigation()
    }
    
    private fun setupNavigation() {
        // Home button - already on home, so just highlight it
        findViewById<View>(R.id.nav_home).apply {
            isSelected = true
            setOnClickListener {
                // Already on home, do nothing
            }
        }
        
        // Profile button
        findViewById<View>(R.id.profile_button_container).setOnClickListener {
            startActivity(Intent(this, Create::class.java))
            }

        
        // FAB - Add new entry
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, Create::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Update the selected state when returning to this activity
        findViewById<View>(R.id.nav_home).isSelected = true
        findViewById<View>(R.id.nav_profile).isSelected = false
    }
}