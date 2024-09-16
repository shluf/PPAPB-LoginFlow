package com.example.ppapb_loginflow

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppapb_loginflow.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                val username = binding.edtUsername.text.toString()
                val password = binding.edtPassword.text.toString()

                val savedUsername =
                    getSharedPreferences("UserData", MODE_PRIVATE).getString("username", "")
                val savedPassword =
                    getSharedPreferences("UserData", MODE_PRIVATE).getString("password", "")

                if (username == savedUsername && password == savedPassword) {
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login gagal. Cek kembali username dan password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnShowPassword.setOnClickListener {
                if (edtPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                    edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    btnShowPassword.setImageResource(R.drawable.ic_visibility_on)
                } else {
                    edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    btnShowPassword.setImageResource(R.drawable.ic_visibility_off)
                }
            }

            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }
}