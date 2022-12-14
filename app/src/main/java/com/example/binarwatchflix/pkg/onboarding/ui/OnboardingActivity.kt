package com.example.binarwatchflix.pkg.onboarding.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.binarwatchflix.R
import com.example.binarwatchflix.data.localpref.UserPreference
import com.example.binarwatchflix.pkg.auth.AuthActivity
import com.example.binarwatchflix.pkg.home.ui.HomeActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment

class OnboardingActivity : AppIntro2() {
    companion object {
        private const val TAG = "OnboardingActivity"
    }

    private val preference: UserPreference by lazy {
        UserPreference(this@OnboardingActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setupSliderFragment()
    }

    private fun setupSliderFragment() {
        isSkipButtonEnabled = false

        setIndicatorColor(
            selectedIndicatorColor = ContextCompat.getColor(this, R.color.off_white_400),
            unselectedIndicatorColor = ContextCompat.getColor(this, R.color.grey_600)
        )

        addSlide(
            AppIntroFragment.createInstance(
                description = getString(R.string.text_onboard_desc_1),
                imageDrawable = R.drawable.ic_onboard_image_1,
                descriptionTypefaceFontRes = R.font.inter_medium,
                backgroundColorRes = R.color.dark_blue_600,
                descriptionColorRes = R.color.off_white_400
            )
        )

        addSlide(
            AppIntroFragment.createInstance(
                description = getString(R.string.text_onboard_desc_2),
                imageDrawable = R.drawable.ic_onboard_image_2,
                descriptionTypefaceFontRes = R.font.inter_medium,
                backgroundColorRes = R.color.dark_blue_600,
                descriptionColorRes = R.color.off_white_400
            )
        )

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        preference.setSkipIntro(true)
        Intent(this@OnboardingActivity, AuthActivity::class.java).also {
            startActivity(it)
        }

    }


}