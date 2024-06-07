package com.chavez.yahaira.poketinder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    val context: Context
): ViewModel() {

    val errorEmail = MutableLiveData<Boolean>()
    val errorCoincidenciaPassword = MutableLiveData<Boolean>()
    val registroExitoso = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun registrarUsuario(email: String, password: String, confirmPassword: String) {
        if (validarCampos(email, password, confirmPassword)) {
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            registroExitoso.postValue(true)
        }
    }

    private fun validarCampos(email: String, password: String, confirmPassword: String): Boolean {
        if (isEmptyInputs(email, password, confirmPassword)) {
            errorEmail.postValue(true)
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorEmail.postValue(true)
            return false
        }

        if (password != confirmPassword) {
            errorCoincidenciaPassword.postValue(true)
            return false
        }

        return true
    }

    private fun isEmptyInputs(email: String, password: String, confirmPassword: String) = email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
}