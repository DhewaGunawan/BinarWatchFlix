package com.example.binarwatchflix.pkg.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseActivity
import com.example.binarwatchflix.data.localpref.UserPreference
import com.example.binarwatchflix.databinding.ActivityHomeBinding
import com.example.binarwatchflix.pkg.onboarding.ui.OnboardingActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    companion object {
        private const val EXTRAS_NAME = "EXTRAS_NAME"

        fun startActivity(context: Context, name: String) {
            context.startActivity(Intent(context, HomeActivity::class.java).apply {
                putExtra(EXTRAS_NAME, name)
            })
        }

        private const val TAG = "HomeActivity"
    }

    private val dialogLogout by lazy {
        MaterialAlertDialogBuilder(this@HomeActivity)
            .setMessage(getString(R.string.logout_text))
            .setNegativeButton(getString(R.string.lbl_no)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.lbl_yes) { _, _ ->
                preference.clearUserToken()
                Intent(this@HomeActivity, OnboardingActivity::class.java).also {
                    startActivity(it)
                }
            }
    }

//    private var _viewPagerAdapter: HomeViewPagerAdapter? = null
//    private val viewPagerAdapter get() = _viewPagerAdapter!!

    private val preference: UserPreference by lazy {
        UserPreference(this@HomeActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        supportActionBar?.hide()
        binding.apply {
            includeToolbar.apply {
                titleName.text = preference.getUserToken()
                btnLogout.setOnClickListener {
                    dialogLogout.show()
                }
            }

            fabChat.setOnClickListener { view ->
                Snackbar.make(view, "On Progress", Snackbar.LENGTH_LONG).show()
            }

            initViewPagerAdapter()
        }

        subscribeObserver()
    }

    private fun initViewPagerAdapter() {

    }

    fun refreshData() {
        initViewPagerAdapter()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun subscribeObserver() {
        observeData()
    }

    private fun observeData() {

    }
}