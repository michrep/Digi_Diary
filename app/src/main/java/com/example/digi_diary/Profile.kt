package com.example.digi_diary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Profile : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        
        // Set up back button
        findViewById<View>(R.id.btnBack).setOnClickListener {
            onBackPressed()
        }
        
        // Set up navigation
        setupBottomNavigation()
        
        // Set up profile options click listeners
        setupProfileOptions()
    }
    
    private fun setupBottomNavigation() {
        // Home button
        findViewById<View>(R.id.nav_home).setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        
        // Profile button - already on profile, so just highlight it
        findViewById<View>(R.id.nav_profile).apply {
            isSelected = true
            setOnClickListener {
                // Already on profile, do nothing
            }
        }
        
        // FAB (Add new entry)
        findViewById<View>(R.id.fab).setOnClickListener {
            startActivity(Intent(this@Profile, Create::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    private fun setupProfileOptions() {
        // Change Password
        findViewById<View>(R.id.btnChangePassword).setOnClickListener {
            // TODO: Implement change password functionality
            showChangePasswordDialog()
        }
        
        // View Developers
        findViewById<View>(R.id.btnViewDevelopers).setOnClickListener {
            val intent = Intent(this, Developer::class.java)
            startActivity(intent)
        }
        
        // Logout
        findViewById<View>(R.id.btnLogout).setOnClickListener {
            showLogoutConfirmation()
        }
    }
    
    private fun showChangePasswordDialog() {
        // TODO: Implement change password dialog
        Toast.makeText(this, "Change Password Clicked", Toast.LENGTH_SHORT).show()
    }
    
    private fun showLogoutConfirmation() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->
                // Perform logout
                val intent = Intent(this, Login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    
    override fun onResume() {
        super.onResume()
        // Update the selected state when returning to this activity
        findViewById<View>(R.id.nav_home).isSelected = false
        findViewById<View>(R.id.nav_profile).isSelected = true
    }
}