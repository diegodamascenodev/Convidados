package com.cursoandroidkotlin.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroidkotlin.convidados.R
import com.cursoandroidkotlin.convidados.service.constants.GuestContants
import com.cursoandroidkotlin.convidados.view.adapter.GuestAdapter
import com.cursoandroidkotlin.convidados.view.listener.GuestListener
import com.cursoandroidkotlin.convidados.viewmodel.GuestsViewModel

class AllGuestFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter : GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //RecyclerView

        // Passo 1 - Obter o elemento RecyclerView no XML
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        // Passo 2 - Definir um layout para o Recycler
        recycler.layoutManager = LinearLayoutManager(context)

        // Passo 3 - Definir um adapter
        recycler.adapter = mAdapter

        mListener = object: GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestContants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestContants.FILTER.EMPTY)
            }
        }

        mAdapter.attachListener(mListener)

        observer()

        mViewModel.load((GuestContants.FILTER.EMPTY))

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestContants.FILTER.EMPTY)
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}