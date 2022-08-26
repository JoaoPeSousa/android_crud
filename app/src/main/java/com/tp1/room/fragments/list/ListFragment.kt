package com.tp1.room.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp1.room.R
import com.tp1.room.databinding.FragmentListBinding
import com.tp1.room.MainActivityViewModel
import com.tp1.room.fragments.list.adapter.ListAdapter


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    var adapter = ListAdapter()
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        mainActivityViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }


        adapter.onItemClick = {
            val action = ListFragmentDirections.actionListFragmentToUpdate2(it)
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Deletar todos")
        builder.setMessage("Deseja deletar todos os usuarios?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            mainActivityViewModel.deleteAll()
            val text = "Todos os users foram deletados"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            val text = "Deletion Operation cancelled"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

        builder.show()

    }

}