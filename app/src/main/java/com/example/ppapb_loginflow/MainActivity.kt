package com.example.ppapb_loginflow

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ppapb_loginflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
            btnRegister.setOnClickListener {
                if (validateInputs()) {
                    saveUserData()
                    Toast.makeText(this@MainActivity, "Akun telah berhasil dibuat.", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
            }

            btnLogin.setOnClickListener {
                navigateToLogin()
            }

            btnShowPassword.setOnClickListener {
                togglePasswordVisibility()
            }
        }
    }

    private fun validateInputs(): Boolean {
        with(binding) {
            when {
                !checkboxTerms.isChecked -> {
                    Toast.makeText(this@MainActivity,"Silakan setujui Syarat dan Ketentuan", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            return true
        }
    }

    private fun saveUserData() {
        val userData = getSharedPreferences("UserData", MODE_PRIVATE).edit()
        userData.apply {
            putString("username", binding.edtUsername.text.toString().trim())
            putString("email", binding.edtEmail.text.toString().trim())
            putString("telp", binding.edtTelp.text.toString().trim())
            putString("password", binding.edtPassword.text.toString())
            apply()
        }
    }

    private fun togglePasswordVisibility() {
        with(binding) {
            if (edtPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btnShowPassword.setImageResource(R.drawable.ic_visibility_on)
            } else {
                edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                btnShowPassword.setImageResource(R.drawable.ic_visibility_off)
            }
            edtPassword.setSelection(edtPassword.text!!.length)
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}