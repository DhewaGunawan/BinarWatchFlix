package com.example.binarwatchflix.pkg.auth

import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseFragment
import com.example.binarwatchflix.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun initView() {
        super.initView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            tvLoginNavigate.setOnClickListener {
                Log.d("REGISTER ACTIVITY", "setOnClickListener: clicked")
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.nav_host_fragment, LoginFragment())
                    commit()
                }
            }

            etEmail.setOnFocusChangeListener { view, focused ->
                if (!focused) tilEmail.helperText =
                    isValidEmail(etEmail.text.toString())
            }

            etPassword.setOnFocusChangeListener { view, focused ->
                if (!focused) tilPassword.helperText =
                    isValidPassword(etPassword.text.toString())
            }

            etRepeatPassword.setOnFocusChangeListener { view, focused ->
                if (!focused) tilRepeatPassword.helperText =
                    isValidRepeatedPassword(etRepeatPassword.text.toString())
            }

            buttonSubmit.setOnClickListener {
                clearFocusedEditText()
                Toast.makeText(
                    activity,
                    binding.etEmail.error,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun isValidEmail(email: String): String? {
        if (email.isBlank()) {
            return "Field cannot be empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun isValidPassword(password: String): String? {
        if (password.isBlank()) {
            return "Field cannot be empty"
        }
        if (password.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (password.contains(" ")) {
            return "Password can't contain any whitespace"
        }
        return null
    }

    private fun isValidRepeatedPassword(password: String): String? {
        if (password.isBlank()) {
            return "Field cannot be empty"
        }
        if (password != binding.etPassword.text.toString()) {
            return "Password doesn't match"
        }
        return null
    }

    private fun clearFocusedEditText() {
        binding.apply {
            if (etEmail.isFocused) etEmail.clearFocus()
            if (etPassword.isFocused) etPassword.clearFocus()
            if (etRepeatPassword.isFocused) etRepeatPassword.clearFocus()
        }
    }


//    private fun isInvalidInputPresent(): Boolean {
//        binding.apply {
//            return (etEmail.error == null || etPassword.error == null || etRepeatPassword.error == null)
//        }
//
//    }

}
















