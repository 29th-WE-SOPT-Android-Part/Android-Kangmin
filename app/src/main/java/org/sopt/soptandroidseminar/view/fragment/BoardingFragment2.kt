package org.sopt.soptandroidseminar.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentBoarding2Binding

class BoardingFragment2: Fragment() {
    private var _binding: FragmentBoarding2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoarding2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_boardingFragment2_to_boardingFragment3)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}