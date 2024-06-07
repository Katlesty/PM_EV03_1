package com.chavez.yahaira.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chavez.yahaira.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = RegisterViewModel(this)
        observeValues()
    }

    private fun observeValues() {
        registerViewModel.errorEmail.observe(this) {
            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT)
                .show()
        }
        registerViewModel.errorCoincidenciaPassword.observe(this) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                .show()
        }
        registerViewModel.registroExitoso.observe(this) {
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegistrar.setOnClickListener {
            registerViewModel.registrarUsuario(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                confirmPassword = binding.edtPassword2.text.toString()
            )
        }

        binding.txtLoginUser.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnBackClose.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            }
        }
}