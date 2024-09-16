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
import com.example.ppapb_loginflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnRegister.setOnClickListener {
                val username = binding.edtUsername.text.toString()
                val email = binding.edtEmail.text.toString()
                val telp = binding.edtTelp.text.toString()
                val password = binding.edtPassword.text.toString()

                getSharedPreferences("UserData", MODE_PRIVATE).edit().apply {
                    putString("username", username)
                    putString("email", email)
                    putString("telp", telp)
                    putString("password", password)
                    apply()
                }

                if (validateInputs()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Akun telah berhasil dibuat.",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                }
            }

            btnLogin.setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
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
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (!binding.checkboxTerms.isChecked) {
            Toast.makeText(this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
