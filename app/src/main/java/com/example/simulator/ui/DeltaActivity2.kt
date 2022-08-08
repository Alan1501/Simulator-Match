package com.example.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simulator.databinding.ActivityDelta2Binding

class DeltaActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityDelta2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDelta2Binding.inflate(layoutInflater)



        setContentView(binding.root)
    }
}