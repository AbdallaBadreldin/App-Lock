package com.jetawy.applock.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jetawy.applock.R
import com.jetawy.applock.databinding.FragmentCreatePinBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateDigitPin : Fragment() {

    private var _binding: FragmentCreatePinBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreatePinBinding.inflate(inflater, container, false)



        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}