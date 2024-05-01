package com.example.app.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.adapter.CarroListAdapter
import com.example.app.data.CarroMock
import com.example.app.databinding.ActivityRecyclerViewBinding
import com.example.app.model.Carro

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerViewBinding
    private lateinit var adapter : CarroListAdapter
    private var pos = -1
    private lateinit var mock : CarroMock

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        mock = CarroMock()
        adapter = CarroListAdapter(mock.listaCarros, CarroListAdapter.OnClickListener{
//            Toast.makeText(this, it.modelo, Toast.LENGTH_SHORT).show()
            pos = pesquisaPosicao(it.id)
            binding.editModelo.setText(mock.listaCarros[pos].modelo)
        })
        binding.recyclerView.adapter = adapter

        binding.buttonInserir.setOnClickListener {
            var modelo = binding.editModelo.text.toString().toInt()
            mock.listaCarros.add(Carro(modelo, modelo.toString()))
            adapter.notifyDataSetChanged()
            pos = -1
        }
        binding.buttonEditar.setOnClickListener {
            if(pos>=0){
                val modelo = binding.editModelo.text.toString()
                mock.listaCarros[pos].modelo = modelo
                mock.listaCarros[pos].id = modelo.toInt()
                pos = -1
                adapter.notifyDataSetChanged()
            }
        }
        binding.buttonEliminar.setOnClickListener {
            if(pos>=0){
                mock.listaCarros.removeAt(pos)
                pos = -1
                adapter.notifyDataSetChanged()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

   private fun pesquisaPosicao(id: Int): Int{
       for (i in 0 .. mock.listaCarros.size){
           if(mock.listaCarros[i].id == id){
               return i
           }
       }
       return -1
   }
}