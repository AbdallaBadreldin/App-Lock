package com.jetawy.applock.ui

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetawy.applock.R
import com.jetawy.applock.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)


        val packageManager = requireActivity().packageManager
        val installedApps = getInstalledApps(packageManager)
        val adapter = PackagesAdapter(installedApps)
        binding.packageRecycler.adapter = adapter
        binding.packageRecycler.adapter = adapter
        binding.packageRecycler.layoutManager = LinearLayoutManager(requireContext())

        for (appInfo in installedApps) {
            val appName = appInfo.loadLabel(packageManager).toString()
            val packageName = appInfo.packageName

            Log.e("the apps ", "$appName , $packageName")
            // Do something with the app name and package name
        }


        return binding.root

    }

    fun getInstalledApps(packageManager: PackageManager): List<ApplicationInfo> {
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}