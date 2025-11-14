package com.example.mymail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

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

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.imagen.setImageResource(item)
        val posicion = pos+1
        viewHolder.texto.setText("Card "+posicion)

        viewHolder.imagen.setOnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Guardar tiempo de toque
                    v.tag = System.currentTimeMillis()
                }
                MotionEvent.ACTION_UP -> {
                    val downTime = v.tag as Long
                    val elapsed = System.currentTimeMillis() - downTime
                    if (elapsed < 500) { // toque corto
                        mostrarPopup(v, event)
                    } else { // toque largo
                        mostrarAction(v)
                    }
                }
            }
            true
        }

    }

    override fun getItemCount() = items.size


    private fun mostrarPopup(v: View, event: MotionEvent){
        val inflater = LayoutInflater.from(v.context)
        val menuView = inflater.inflate(R.layout.menu_img, null)

        val popup = PopupWindow(
            menuView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popup.isOutsideTouchable = true

        // Mostrar en las coordenadas exactas del clic
        popup.showAtLocation(v, Gravity.NO_GRAVITY, event.rawX.toInt(), event.rawY.toInt())
    }

    private fun mostrarAction(v: View){
        val activity = v.context as AppCompatActivity
        val toolbarContainer = activity.findViewById<ViewGroup>(R.id.toolbarInclude)

        // Quita la toolbar vieja
        toolbarContainer.removeAllViews()

        // Infla la nueva toolbar de acción
        val actionToolbar = LayoutInflater.from(v.context)
            .inflate(R.layout.action_toolbar, toolbarContainer, false) as MaterialToolbar

        val titulo = actionToolbar.findViewById<TextView>(R.id.TituloToolBar)
        val retroceder = actionToolbar.findViewById<ImageView>(R.id.Atras)
        val eliminar = actionToolbar.findViewById<ImageView>(R.id.EliminarAction)
        val editar = actionToolbar.findViewById<ImageView>(R.id.EditarAction)
        val compartir = actionToolbar.findViewById<ImageView>(R.id.CompartirAction)

        titulo?.setText("Options")
        toolbarContainer.addView(actionToolbar)


        retroceder.setOnClickListener {
            toolbarContainer.removeAllViews()
            val originalToolbar = LayoutInflater.from(v.context)
                .inflate(R.layout.toolbar_layout, toolbarContainer, false) as MaterialToolbar

            val titulo = originalToolbar.findViewById<TextView>(R.id.TituloToolBar)
            titulo?.setText("Gallery")

            // Añadir de nuevo la toolbar al contenedor
            toolbarContainer.addView(originalToolbar)
        }
        true
    }
}
