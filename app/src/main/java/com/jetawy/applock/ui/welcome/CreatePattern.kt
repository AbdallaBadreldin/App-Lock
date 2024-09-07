package com.jetawy.applock.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itsxtt.patternlock.PatternLockView
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





        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.patternLockView.setOnPatternListener(object : PatternLockView.OnPatternListener {
            override fun onStarted() {
                super.onStarted()
            }

            override fun onProgress(ids: ArrayList<Int>) {
                super.onProgress(ids)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}