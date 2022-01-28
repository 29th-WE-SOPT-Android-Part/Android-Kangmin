package org.sopt.soptandroidseminar.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentBoarding3Binding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.view.signin.SignInActivity

class BoardingFragment3: BindingFragment<FragmentBoarding3Binding>(R.layout.fragment_boarding3) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            val intent = Intent(requireActivity(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}