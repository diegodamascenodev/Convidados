package com.cursoandroidkotlin.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroidkotlin.convidados.R
import com.cursoandroidkotlin.convidados.view.adapter.GuestAdapter
import com.cursoandroidkotlin.convidados.viewmodel.AllGuestViewModel
import kotlinx.android.synthetic.main.fragment_all.*

class AllGuestFragment : Fragment() {

    private lateinit var allGuestViewModel: AllGuestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        allGuestViewModel = ViewModelProvider(this).get(AllGuestViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //RecyclerView

        // Passo 1 - Obter o elemento RecyclerView no XML
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        // Passo 2 - Definir um layout para o Recycler
        recycler.layoutManager = LinearLayoutManager(context)

        // Passo 3 - Definir um adapter
        recycler.adapter = GuestAdapter()

        return root
    }
}