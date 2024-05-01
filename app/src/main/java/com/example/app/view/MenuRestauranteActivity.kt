package com.example.app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.databinding.ActivityMenuRestauranteBinding

class MenuRestauranteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuRestauranteBinding
    private val precoCafe = 1
    private val precoChocolate = 1.2
    private val precoPao = 0.5

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuRestauranteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.buttonPedido.setOnClickListener {
            val i = Intent(this, SplashScreenMenuRestauranteActivity::class.java)
            i.putExtra("quant_cafe", binding.editQuantCafe.text.toString())
            i.putExtra("quant_chocolate", binding.editQuantChocolate.text.toString())
            i.putExtra("quant_pao", binding.editQuantPao.text.toString())

            i.putExtra("preco_cafe", precoCafe)
            i.putExtra("preco_chocolate", precoChocolate)
            i.putExtra("preco_pao", precoPao)
            startActivity(i)

        }
        binding.checkCafe.setOnClickListener{
            if(binding.checkCafe.isChecked){
                binding.editQuantCafe.setText("1")
                binding.textQuantCafe.visibility = View.VISIBLE
            }else{
                binding.editQuantCafe.setText("0")
                binding.textQuantCafe.visibility = View.GONE
            }
        }
        binding.checkChocolate.setOnClickListener{
            if(binding.checkChocolate.isChecked){
                binding.editQuantChocolate.setText("1")
                binding.textQuantChocolate.visibility = View.VISIBLE
            }else{
                binding.editQuantChocolate.setText("0")
                binding.textQuantChocolate.visibility = View.GONE
            }
        }
        binding.checkPao.setOnClickListener{
            if(binding.checkPao.isChecked){
                binding.editQuantPao.setText("1")
                binding.textQuantPao.visibility = View.VISIBLE
            }else{
                binding.editQuantPao.setText("0")
                binding.textQuantPao.visibility = View.GONE
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}