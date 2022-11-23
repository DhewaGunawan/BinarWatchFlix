package com.example.binarwatchflix.pkg.auth

import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseFragment
import com.example.binarwatchflix.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun initView() {
        super.initView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            tvSignupNavigate.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.nav_host_fragment, RegisterFragment())
                    commit()
                }
            }
            buttonSubmit.setOnClickListener {

            }
        }
    }
}