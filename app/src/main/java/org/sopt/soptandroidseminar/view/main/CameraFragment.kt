package org.sopt.soptandroidseminar.view.main

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentCameraBinding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.view.showToast

class CameraFragment : BindingFragment<FragmentCameraBinding>(R.layout.fragment_camera) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonEvent()

    }

    private fun buttonEvent() {
        binding.btnCamera.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            filterActivityLauncher.launch(intent)
        }
    }

    private val filterActivityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                var currentImageUri = it.data?.data
                Glide.with(requireActivity()).load(currentImageUri).into(binding.imageCamera)

            } else if (it.resultCode == RESULT_CANCELED) {
                requireActivity().showToast("사진 선택 취소")
            } else {
                Log.d("ActivityResult", "something wrong")
            }
        }

}