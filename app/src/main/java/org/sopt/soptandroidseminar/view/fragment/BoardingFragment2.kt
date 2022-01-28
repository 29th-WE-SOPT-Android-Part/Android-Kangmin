package org.sopt.soptandroidseminar.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentBoarding2Binding
import org.sopt.soptandroidseminar.util.BindingFragment

class BoardingFragment2: BindingFragment<FragmentBoarding2Binding>(R.layout.fragment_boarding2) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_boardingFragment2_to_boardingFragment3)
        }
    }
}