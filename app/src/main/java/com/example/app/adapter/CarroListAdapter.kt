package com.example.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.model.Carro
import kotlinx.coroutines.NonDisposableHandle.parent

class CarroListAdapter(val listaCarros: ArrayList<Carro>, val onClickListener: OnClickListener) : RecyclerView.Adapter<CarroListAdapter.CarroViewHolder>() {
    var contatorOnCreate = 0
    var contatorOnBind = 0

    class CarroViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_modelo)
    }

    class OnClickListener(val ckickListener: (carro: Carro) -> Unit){
        fun onClick(carro: Carro) = ckickListener(carro)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        contatorOnCreate++
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_carro_list, parent,false)

        return CarroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        contatorOnBind++
        val carro = listaCarros[position]
        holder.textView.setText(carro.modelo)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(carro)
        }
    }

}