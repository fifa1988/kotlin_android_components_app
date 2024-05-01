package com.example.app.view

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.databinding.ActivitySharedPreferencesBinding

class SharedPreferencesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySharedPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySharedPreferencesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sharedPreferences = this.getSharedPreferences("valores", Context.MODE_PRIVATE)
        val valor = sharedPreferences.getString("valor", "")
        binding.textShared.setText(valor)

        binding.buttonShared.setOnClickListener {
            val novovalor = binding.EditShared.text.toString()
            binding.textShared.setText(novovalor)
            val editor = sharedPreferences.edit()
            editor.putString("valor", novovalor)
            editor.apply()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}