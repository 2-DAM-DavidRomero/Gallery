package com.example.mymail

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorImg(private val items: List<Int>) :
    RecyclerView.Adapter<AdaptadorImg.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagen: ImageView
        var texto: TextView

        init {
            imagen = itemView.findViewById<ImageView?>(R.id.imageCard)
            texto = itemView.findViewById<TextView?>(R.id.textoCard)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_img, parent, false) // tu XML de la card
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.imagen.setImageResource(item)
        val posicion = pos+1
        viewHolder.texto.setText("Card "+posicion)
    }

    override fun getItemCount() = items.size
}
