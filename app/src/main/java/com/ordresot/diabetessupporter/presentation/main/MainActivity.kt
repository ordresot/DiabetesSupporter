package com.ordresot.diabetessupporter.presentation.main

import com.ordresot.diabetessupporter.presentation.wizard.WizardActivity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ordresot.diabetessupporter.R
import com.ordresot.diabetessupporter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, MainViewModel.getViewModelFactory())[MainViewModel::class.java]

        viewModel.getIsFirstRun().observe(this) { value ->
            if (value){
                startActivity(
                    Intent(
                        this,
                        WizardActivity::class.java
                    )
                )
            }
        }
    }
}