package com.example.app.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.databinding.ActivityMainBundleBinding

class MainActivityBundle : AppCompatActivity() {
    private lateinit var binding: ActivityMainBundleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBundleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        var i = intent
        var nome = i.extras?.getString("nome")
        binding.textBundle.setText(nome)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}