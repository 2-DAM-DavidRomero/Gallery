package com.example.mymail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.appbar.MaterialToolbar

class Gallery : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        val listaFotos = view.findViewById<RecyclerView>(R.id.listaFotos)


        listaFotos.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val imagenes = listOf(R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9)


        listaFotos.adapter = AdaptadorImg(imagenes)

        return view
    }

    override fun onResume() {
        super.onResume()

        // Acceder al toolbar de la Activity
        val toolbar = activity?.findViewById<MaterialToolbar>(R.id.toolbar)
        val titulo = toolbar?.findViewById<TextView>(R.id.TituloToolBar)

        // Cambiar el texto
        titulo?.text = "Gallery"
    }
}