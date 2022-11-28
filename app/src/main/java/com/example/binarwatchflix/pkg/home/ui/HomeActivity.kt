package com.example.binarwatchflix.pkg.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseActivity
import com.example.binarwatchflix.data.localpref.UserPreference
import com.example.binarwatchflix.databinding.ActivityHomeBinding
import com.example.binarwatchflix.pkg.auth.AuthActivity
import com.example.binarwatchflix.pkg.auth.AuthViewModel
import com.example.binarwatchflix.pkg.chat.ui.ChatActivity
import com.example.binarwatchflix.pkg.home.adapter.HomeViewPagerAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    companion object {
        private const val EXTRAS_NAME = "EXTRAS_NAME"

        fun startActivity(context: Context, name: String) {
            context.startActivity(Intent(context, HomeActivity::class.java).apply {
                putExtra(EXTRAS_NAME, name)
            })
        }

        private const val TAG = "HomeActivity"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.filter_movies,
            R.string.filter_tv_show,
        )
    }

    private val viewModel: AuthViewModel by viewModel()

    private val dialogLogout by lazy {
        MaterialAlertDialogBuilder(this@HomeActivity)
            .setMessage(getString(R.string.logout_text))
            .setNegativeButton(getString(R.string.lbl_no)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.lbl_yes) { dialog, _ ->
                logout()
                dialog.dismiss()
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

            fabChat.setOnClickListener {
                ChatActivity.startActivity(this@HomeActivity)
            }

            initViewPagerAdapter()
        }

        subscribeObserver()
    }

    private fun initViewPagerAdapter() {
        val homeViewPagerAdapter = HomeViewPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = homeViewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun refreshData() {
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

    private fun logout() {
        viewModel.doLogout()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}