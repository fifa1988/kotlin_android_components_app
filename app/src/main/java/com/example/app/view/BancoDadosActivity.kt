package com.example.app.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.data.DBHelper
import com.example.app.data.Utilizador
import com.example.app.databinding.ActivityBancoDadosBinding
import com.example.app.databinding.ActivityDadosPedidoBinding

class BancoDadosActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBancoDadosBinding
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBancoDadosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val db = DBHelper(this)
        val listaUtilizadores = db.utilizadorListSelectAll()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            binding.editId.setText("ID: ${listaUtilizadores[position].id}")
            binding.editUsername.setText(listaUtilizadores[position].username)
            binding.editPassword.setText(listaUtilizadores[position].password)
            pos = position
        }

        binding.buttonInserir.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            val result = db.utilizarInsert(username, password)
            if(result>0){
                Toast.makeText(applicationContext, "Insert OK $result", Toast.LENGTH_SHORT).show()
                listaUtilizadores.add(Utilizador(result.toInt(), username, password))
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext, "Insert Erro $result", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonAtualizar.setOnClickListener {
            if(pos>=0){
                val id = listaUtilizadores[pos].id
                val username = binding.editUsername.text.toString()
                val password = binding.editPassword.text.toString()
                val result = db.utilizarUpdate(id, username, password)
                if(result>0){
                    listaUtilizadores[pos].username = username
                    listaUtilizadores[pos].password = password
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Update OK $result", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Update Erro $result", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.buttonDeletar.setOnClickListener {
            if(pos>=0) {
                val id = listaUtilizadores[pos].id
                val result = db.utilizarDelete(id)
                if(result>0){
                    listaUtilizadores.removeAt(pos)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Delete OK $result", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Delete Erro $result", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}