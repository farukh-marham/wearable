package com.example.watchsampleapp.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.watchsampleapp.R
import com.example.watchsampleapp.databinding.ActivityMainBinding


class MainActivity : Activity(), View.OnClickListener, LifecycleOwner {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainActivityViewModel()
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        setListeners()
        observeData()
    }

    private fun observeData() {
        viewModel.counterValue.observeForever {
            binding.counterText.text = it.toString()
        }
    }

    private fun setListeners() {
        binding.apply {
            plusCounter.setOnClickListener(this@MainActivity)
            minusCounter.setOnClickListener(this@MainActivity)
            resetBtn.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.plusCounter -> {
                viewModel.incrementCounter()
            }
            R.id.minusCounter -> {
                viewModel.decrementCounter()
            }
            R.id.resetBtn -> {
                viewModel.resetCounter()
            }
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }


    public override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }
}
