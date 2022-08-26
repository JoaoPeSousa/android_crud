package com.tp1.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tp1.room.databinding.FragmentAddBinding
import com.tp1.room.MainActivityViewModel
import com.tp1.room.R
import com.tp1.room.database.models.Usuario

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            inserirNoDB()
        }

        return binding.root
    }

    private fun inserirNoDB() {

        val nome = binding.nome.text.toString()
        val sobrenome = binding.sobrenome.text.toString()
        val idade = binding.idade.text

        if (validaInput(nome, sobrenome, idade)) {
            val usuario = Usuario(0, nome, sobrenome, Integer.parseInt(idade.toString()))

            mainActivityViewModel.addUsuario(usuario)

            val text = "Usuario add com sucesso"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {

            val text = "Por favor preencha todos os campos"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }

    private fun validaInput(nome: String, sobrenome: String, idade: Editable?): Boolean {

        return if (idade != null) {
            !(TextUtils.isEmpty(nome) && TextUtils.isEmpty(sobrenome) && idade.isEmpty())
        } else {
            false
        }
    }


}