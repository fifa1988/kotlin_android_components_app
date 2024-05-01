package com.example.app.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.databinding.ActivityDadosPedidoBinding

class DadosPedidoActivity : AppCompatActivity() {
    private lateinit var bindind: ActivityDadosPedidoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bindind = ActivityDadosPedidoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindind.root)

        val i = intent
        val quant_cafe = i.getStringExtra("quant_cafe").toString().toInt()
        val quant_chocolate = i.getStringExtra("quant_chocolate").toString().toInt()
        val quant_pao = i.getStringExtra("quant_pao").toString().toInt()
        val preco_cafe = i.getDoubleExtra("preco_cafe", 0.0)
        val preco_chocolate = i.getDoubleExtra("preco_chocolate", 0.0)
        val preco_pao = i.getDoubleExtra("preco_pao", 0.0)

        val texto = "Resumo do pedido: \n" +
                "Café: $quant_cafe Preço: ${String.format("%.2f",quant_cafe*preco_cafe).toDouble()}E \n" +
                "Chocolate: $quant_chocolate Preço: ${String.format("%.2f",quant_chocolate*preco_chocolate).toDouble()}E \n" +
                "Pão: $quant_pao Preço: ${String.format("%.2f",quant_pao*preco_pao).toDouble()}E \n"
        bindind.textResumo.setText(texto)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}