package com.jetawy.applock.ui

import android.Manifest
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.jetawy.applock.R
import com.jetawy.applock.databinding.ActivityMainBinding
import com.jetawy.applock.ui.services.AppService

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val startServiceIntent = Intent(this, AppService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startService(startServiceIntent)
        }
        setSupportActionBar(binding.toolbar)
        detectAppStarted()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    val REQUEST_CODE_USAGE_ACCESS = 454


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_USAGE_ACCESS && resultCode == PackageManager.PERMISSION_GRANTED) {
            detectAppStarted()
        } else {
//                detectAppStarted()
            // Permission denied, handle accordingly (e.g., show a message)
        }
    }

    private fun detectAppStarted() {
        val usageStatsManager =
            applicationContext.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - (1000 * 60 * 900) // Check usage in the last 600 minutes

        val usageStats =
            usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_MONTHLY, startTime, endTime)
        if (usageStats.isNullOrEmpty()) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivityForResult(intent, REQUEST_CODE_USAGE_ACCESS)
        }
        // Iterate through usage stats and check for specific app launches
        for (usageStat in usageStats) {
            Log.e("APP STARTED", usageStat.packageName.toString())
            // Target app was launched within the last 10 minutes
            // Perform your desired action here
        }
    }

    private fun isUsageStatsPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.PACKAGE_USAGE_STATS
        ) == PackageManager.PERMISSION_GRANTED
    }
}