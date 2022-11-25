package com.example.binarwatchflix.pkg.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.binarwatchflix.base.BaseActivity
import com.example.binarwatchflix.databinding.ActivityAuthBinding
import com.example.binarwatchflix.pkg.home.ui.HomeActivity
import com.example.binarwatchflix.wrapper.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AuthActivity :
    BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    private val TAG = AuthActivity::class.java.simpleName

    private val viewModel: AuthViewModel by viewModel()

    private val googleSignInClient: GoogleSignInClient by inject{
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeData()
    }

    // Google SSO with Firebase
    // Google Auth -> Token Session -> Register To Firebase -> Firebase User
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    account.idToken?.let { firebaseAuthWithGoogle(it) }
                } catch (e: ApiException) {
                    Log.w(TAG, "Google sign in failed", e)
                }
            }
        }

    private fun observeData() {
        viewModel.loginResult.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    //nothing
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        "Failed to Login : ${it.exception?.message.orEmpty()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    showLoadingState(false)
                }
                is Resource.Loading -> {
                    showLoadingState(true)
                }
                is Resource.Success -> {
                    showLoadingState(false)
                    if (it.payload?.second != null) {
                        navigateToHome()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    private fun showLoadingState(isShow: Boolean) {
        binding.pbLogin.isVisible = isShow
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        viewModel.authenticateGoogleLogin(idToken)
    }

    private fun initView() {
        supportActionBar?.hide()
        setLoginAction()
    }

    private fun setLoginAction() {
        binding.btnSignInGoogle.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }


}