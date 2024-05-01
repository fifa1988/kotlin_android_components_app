package com.example.app.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val i = intent
        val nome = i.extras?.getString("nome")

        if(nome != null && !nome.equals("")){
            binding.textShowName.setText("Bem vindo, $nome")
        }

        binding.buttonVoltar.setOnClickListener {
            //Não pode usar o startActivity se não o botão voltar do android vai ficar passando de telas instanciadas
            //startActivity(Intent(this, MainActivity::class.java))
            i.putExtra("retornoNome", nome)
            setResult(1, i)
            finish()
        }

        //repassar os valores do intent para o proximo
        val bundle = i.extras

        binding.buttonBundle.setOnClickListener {
            val intent = Intent(this, MainActivityBundle::class.java)
            if(bundle != null){
                intent.putExtras(bundle)
            }
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}