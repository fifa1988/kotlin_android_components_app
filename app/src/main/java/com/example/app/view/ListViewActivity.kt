package com.example.app.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.classes.CrudList
import com.example.app.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListViewBinding
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        var lista = ArrayList<CrudList>()
        lista.add(CrudList("user", "pass"))
        lista.add(CrudList("admin", "pass123"))
        lista.add(CrudList("aaa", "bbb"))


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            binding.editNome.setText(lista.get(position).username)
            binding.editPass.setText(lista.get(position).password)
            pos = position
            Toast.makeText(this, "Clicado em ${lista.get(position)}",Toast.LENGTH_SHORT).show()
        }

        binding.buttonInserir.setOnClickListener {
            val nome = binding.editNome.text.toString().trim()
            val pass = binding.editPass.text.toString().trim()
            if(nome.isNotEmpty() && pass.isNotEmpty()) {
                lista.add(CrudList(nome, pass))
                adapter.notifyDataSetChanged()
            }
            binding.editNome.setText("")
            binding.editPass.setText("")
        }

        binding.buttonEliminar.setOnClickListener {
            if(pos >= 0) {
                lista.removeAt(pos)
                pos = -1
                adapter.notifyDataSetChanged()
                binding.editNome.setText("")
                binding.editPass.setText("")
            }
        }

        binding.buttonEditar.setOnClickListener {
            if(pos >= 0) {
                val nome = binding.editNome.text.toString().trim()
                val pass = binding.editPass.text.toString().trim()
                if(nome.isNotEmpty() && pass.isNotEmpty()) {
                    val item = lista.get(pos)
                    item.username = nome
                    item.password = pass
                    pos = -1
                    adapter.notifyDataSetChanged()
                    binding.editNome.setText("")
                    binding.editPass.setText("")
                }
            }
        }

        binding.buttonLimpar.setOnClickListener {
            lista.clear()
            adapter.notifyDataSetChanged()
            binding.editNome.setText("")
            binding.editPass.setText("")
            pos = -1
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}