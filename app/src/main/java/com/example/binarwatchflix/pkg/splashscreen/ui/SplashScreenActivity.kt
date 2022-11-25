package com.example.binarwatchflix.pkg.splashscreen.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseActivity
import com.example.binarwatchflix.data.localpref.UserPreference
import com.example.binarwatchflix.databinding.ActivitySplashScreenBinding
import com.example.binarwatchflix.pkg.auth.AuthActivity
import com.example.binarwatchflix.pkg.home.ui.HomeActivity
import com.example.binarwatchflix.pkg.onboarding.ui.OnboardingActivity
import com.example.binarwatchflix.pkg.splashscreen.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding>(ActivitySplashScreenBinding::inflate) {

    private val viewModel: SplashViewModel by viewModel()

    private val preference: UserPreference by lazy {
        UserPreference(this@SplashScreenActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeData()
    }


    private fun initView() {
        supportActionBar?.hide()
        viewModel.getCurrentUser()
    }

    private fun observeData() {
        viewModel.currentUserLiveData.observe(this) { user ->
            if (preference.isSkipIntro()) {
                if (user == null) {
                    lifecycleScope.launch {
                        delay(2000)
                        startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                } else {
                    lifecycleScope.launch {
                        delay(2000)
                        startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                }
            } else {
                lifecycleScope.launch {
                    delay(2000)
                    startActivity(Intent(this@SplashScreenActivity, OnboardingActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }
            }

        }
    }

}