package com.tp1.room.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp1.room.databinding.CustomRowBinding
import com.tp1.room.database.models.Usuario

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHodler>() {

    private var usuarioList = emptyList<Usuario>()
    var onItemClick: ((Usuario) -> Unit)? = null

    inner class ViewHodler(private val itemViewa: CustomRowBinding) :RecyclerView.ViewHolder(itemViewa.root) {



        fun bind(usuario: Usuario) {

            itemViewa.textView2.text = usuario.id.toString()
            itemViewa.primeironome.text = usuario.nome
            itemViewa.ultimonome.text = usuario.sobrenome
            itemViewa.idadeShow.text = usuario.idade.toString()

            itemViewa.row.setOnClickListener{

                onItemClick?.invoke(usuario)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHodler(binding)
    }

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        val user = usuarioList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = usuarioList.size

    fun setData(usuario : List<Usuario>){
        this.usuarioList = usuario
        notifyDataSetChanged()
    }



}