package com.jetawy.applock.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jetawy.applock.R
import com.jetawy.applock.databinding.FragmentCreatePinBinding


class ConfirmDigitPinFragment : Fragment() {

    private var _binding: FragmentCreatePinBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val args: ConfirmDigitPinFragmentArgs by navArgs()
    private var pin = ""
    private var maxPinNumbers = args.pin.length
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreatePinBinding.inflate(inflater, container, false)
        setupSpinner()
        binding.imageView3.setImageResource(R.drawable.one_two_steps_two_selected)
        binding.spinner.setSelection(2)

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
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            // An item was selected. You can retrieve the selected item using
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        maxPinNumbers = 6
                        gotoCreateDigitPin()
                    }

                    1 -> {
                        maxPinNumbers = 4
                        gotoCreateDigitPin()

                    }

                    else -> {
                        goToPatternFragment()
                        parent?.setSelection(0)
                    }
                    // ... and so on
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//        just do the na-thing
            }
        }
    }

    private fun gotoCreateDigitPin() {
        val action = CreateDigitPinDirections.actionCreateDigitPinToCreatePattern()
        findNavController().navigate(action)
    }

    private fun goToPatternFragment() {
        val action = CreateDigitPinDirections.actionCreateDigitPinToCreatePattern()
        findNavController().navigate(action)
    }

    private fun setupSpinner() {
        // Initialize the Spinner
        val spinner = binding.spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.passcode_options, // The array we defined in strings.xml
            android.R.layout.simple_spinner_item // Default layout for the spinner
        )

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spinner.adapter = adapter

        // (Optional) Set a default selection if needed
        spinner.setSelection(1) // Example: Set default to "6-Digit PIN"
    }

    private fun addNumberToPin(number: Int) {
        //check if 6 or 4
        if (pin.length - 1 <= maxPinNumbers) {
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
    private fun addCircle() {
        val circleContainer = binding.circleContainer
        // Create a new ImageView
        val circle = ImageView(requireContext())
        // Set the circle drawable
        circle.setImageResource(R.drawable.filled_circle)

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

    fun removeAllCircles() {
        val circleContainer = binding.circleContainer
        circleContainer.removeAllViews()
        pin = ""
    }

    private fun checkPinAndMoveNext() {
        if (pin.length == maxPinNumbers && pin == args.pin) {
        //save the pin and go to the next fragment

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}