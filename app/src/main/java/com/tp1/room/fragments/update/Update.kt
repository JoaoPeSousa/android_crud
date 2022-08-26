package com.tp1.room.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tp1.room.R
import com.tp1.room.databinding.FragmentUpdateBinding
import com.tp1.room.MainActivityViewModel
import com.tp1.room.database.models.Usuario

class Update : Fragment() {


    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateArgs>()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding  = FragmentUpdateBinding.inflate(inflater,container,false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.updatesobrenome.setText(args.currentUser.sobrenome)
        binding.updatenome.setText(args.currentUser.nome)
        binding.updateidade.setText(args.currentUser.idade.toString())


        binding.btnUpdate.setOnClickListener{
            updateUsuario()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    fun updateUsuario(){


        val nome = binding.updatenome.text.toString()
        val sobrenome = binding.updatesobrenome.text.toString()
        val idade = binding.updateidade.text

        if (validaInput(nome, sobrenome, idade)) {
            val update = Usuario(args.currentUser.id, nome, sobrenome, Integer.parseInt(idade.toString()))

            mainActivityViewModel.updateUsuario(update)
            val text = "Usuario adicionado com sucesso"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            findNavController().navigate(R.id.action_update_to_listFragment2)

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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUsuario()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUsuario() {
        val builder  = AlertDialog.Builder(requireContext())
        builder.setTitle("Deletar ${args.currentUser.nome}")
        builder.setMessage("Certeza que deseja deletar esse user ${args.currentUser.nome}")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            mainActivityViewModel.deleteUsuario(args.currentUser)
            val text = "Usuário deletado com sucesso ${args.currentUser.nome}"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
            findNavController().navigate(R.id.action_update_to_listFragment2)
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            val text = "Operacao cancelada"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

        builder.show()
    }
}