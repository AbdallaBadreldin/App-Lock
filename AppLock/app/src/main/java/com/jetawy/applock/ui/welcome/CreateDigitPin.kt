package com.jetawy.applock.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.jetawy.applock.R
import com.jetawy.applock.databinding.FragmentCreatePinBinding

class CreateDigitPin : Fragment() {

    private var _binding: FragmentCreatePinBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var pin = ""
    private val maxPinNumbers = 4
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreatePinBinding.inflate(inflater, container, false)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView0.setOnClickListener { addNumberToPin(0) }
        binding.imageView1.setOnClickListener { addNumberToPin(1) }
        binding.imageView2.setOnClickListener { addNumberToPin(2) }
        binding.imageView3.setOnClickListener { addNumberToPin(3) }
        binding.imageView4.setOnClickListener { addNumberToPin(4) }
        binding.imageView5.setOnClickListener { addNumberToPin(5) }
        binding.imageView6.setOnClickListener { addNumberToPin(6) }
        binding.imageView7.setOnClickListener { addNumberToPin(7) }
        binding.imageView8.setOnClickListener { addNumberToPin(8) }
        binding.imageView9.setOnClickListener { addNumberToPin(9) }
        binding.imageViewDelete.setOnClickListener { removeNumberFromPin() }
        binding.buttonCreate.setOnClickListener { checkPinAndMoveNext() }

    }

    private fun addNumberToPin(number: Int) {
        //check if 6 or 4
        if (pin.length < maxPinNumbers) {
            pin += number
            addCircle()
        }
    }

    private fun removeNumberFromPin() {
        if (pin.isNotEmpty()) {
            pin = pin.substring(0, pin.length - 1)
            removeCircle()
        }
    }


    // Function to add a circle dynamically
    fun addCircle() {
        val circleContainer = binding.circleContainer
        // Create a new ImageView
        val circle = ImageView(requireContext())
        // Set the circle drawable
        circle.setImageResource(R.drawable.empty_circle)

        // Set layout params for the circle (including margin)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 0, 10, 0) // Add some margin between circles
        circle.layoutParams = params

        // Add the circle to the container
        circleContainer.addView(circle)
    }

    fun removeCircle() {
        val circleContainer = binding.circleContainer
        if (circleContainer.childCount > 0) {
            circleContainer.removeViewAt(circleContainer.childCount - 1)
        }
    }

    private fun checkPinAndMoveNext() {
        if (pin.length == maxPinNumbers) {

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}