package com.example.app.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.databinding.ActivityBancoDadosBinding
import com.example.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    private var retornoNome = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)



        binding.buttonEntrar.setOnClickListener {
            var username = binding.editUsername.text.toString().trim()
            var password = binding.editPassword.text.toString().trim()

            if(username.equals("user") && password.equals("pass")){
                val i = Intent(this, ProfileActivity::class.java)
                i.putExtra("nome", username)
                //startActivity(i)
                result.launch(i)
            }else{
                Toast.makeText(applicationContext, "Login ou senha inválidos", Toast.LENGTH_SHORT).show()
            }

            binding.editUsername.setText("")
            binding.editPassword.setText("")

        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data != null && it.resultCode == 1){
                retornoNome = it.data?.getStringExtra("retornoNome").toString()
                binding.editUsername.setText(retornoNome)
            }else{
                Toast.makeText(applicationContext, "Erro ao atualizar o nome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonMenuRestaurante.setOnClickListener {
            startActivity(Intent(this, MenuRestauranteActivity::class.java))
        }

        binding.buttonListView.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        binding.buttonGoogleMap.setOnClickListener {
            startActivity(Intent(this, GoogleMapsActivity::class.java))
        }

        binding.buttonSharedPreferences.setOnClickListener {
            startActivity(Intent(this, SharedPreferencesActivity::class.java))
        }

        binding.buttonAudio.setOnClickListener {
            startActivity(Intent(this, AudioActivity::class.java))
        }

        binding.buttonFullscreen.setOnClickListener {
            startActivity(Intent(this, FullscreenActivity::class.java))
        }

        binding.buttonRecyclerview.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        binding.buttonBancodados.setOnClickListener {
            startActivity(Intent(this, BancoDadosActivity::class.java))
        }

        binding.buttonBottomnavigation.setOnClickListener {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
        }

//        binding.buttonOla.setOnClickListener {
//            val nome = binding.editNome.text.toString()
//            if(nome.isEmpty()){
//                binding.textName.text = "Nome não inserido"
//                Toast.makeText(applicationContext, "Nome não inserido", Toast.LENGTH_SHORT).show()
//            }else {
//                binding.textName.text = "Olá ${nome}"
//                Toast.makeText(applicationContext, "Olá ${nome}", Toast.LENGTH_LONG).show()
//            }
//        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}