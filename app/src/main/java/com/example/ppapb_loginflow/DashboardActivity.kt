package com.example.ppapb_loginflow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppapb_loginflow.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("UserData", MODE_PRIVATE)
        val username = prefs.getString("username", "")
        val email = prefs.getString("email", "")
        val telp = prefs.getString("telp", "")

        with(binding) {

            tvWelcome.text = "Welcome, $username!"
            tvEmail.text = "Your email ($email) has been activated"
            tvTelp.text = "Your phone number ($telp) has been registered"


            btnDial.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$telp")
                startActivity(intent)
            }
        }
    }
}