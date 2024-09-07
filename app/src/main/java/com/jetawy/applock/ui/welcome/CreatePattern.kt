package com.jetawy.applock.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.itsxtt.patternlock.PatternLockView
import com.jetawy.applock.R
import com.jetawy.applock.databinding.FragmentCreatePatternBinding

class CreatePattern : Fragment() {

    private var _binding: FragmentCreatePatternBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreatePatternBinding.inflate(inflater, container, false)
        binding.circleContainer.visibility = View.GONE



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        binding.patternLockView.setOnPatternListener(object : PatternLockView.OnPatternListener {
            override fun onProgress(ids: java.util.ArrayList<Int>) {
                super.onProgress(ids)
            }

            override fun onStarted() {
                super.onStarted()
            }

            override fun onComplete(ids: ArrayList<Int>): Boolean {
                /*
                 * A return value required
                 * if the pattern is not correct and you'd like change the pattern to error state, return false
                 * otherwise return true
                 */

                return true
            }
        })
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
        spinner.setSelection(2) // Example: Set default to "6-Digit PIN"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}