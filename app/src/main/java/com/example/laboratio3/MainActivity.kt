package com.example.laboratio3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var lista_modales = ArrayList<Modal>()
    var titulos = arrayOf("Netflix", "Darwin", "Gamer", "F.C. Internazionale Milano", "Copenhague", "Oslo", "Manchuria", "Honolulu", "Piña")
    var descripciones = arrayOf("Servicio de Streaming", "Australia, Oceania", "Gaming", "Squadra di calcio italiana", "Denmark, Europe", "Norway, Europe", "China, Asia", "Hawaii, Oceania", "Fruta tropical")
    var imagenes = intArrayOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val agregar: Button = findViewById(R.id.button)
            agregar.setOnClickListener { addItem() }

            for(i in titulos.indices) {
                lista_modales.add(Modal(titulos[i], descripciones[i], imagenes[i]))
            }

            renderGrid(lista_modales)

    }

    private fun addItem() {
        val numero_aleatorio = Random.nextInt(0, 10)
        lista_modales.add(Modal(titulos[numero_aleatorio], descripciones[numero_aleatorio], imagenes[numero_aleatorio]))
        renderGrid(lista_modales)
    }

    private fun renderGrid(listado: ArrayList<Modal>) {
        var adaptador_personalizado = AdaptadorPersonalizado(listado, this)

        var grid_view = findViewById<GridView>(R.id.gridView)
        grid_view.adapter = adaptador_personalizado
    }

    class AdaptadorPersonalizado(var elementoModelo: ArrayList<Modal>, var contexto: Context) : BaseAdapter() {

        var diseño_plano = contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return elementoModelo.size
        }

        override fun getItem(position: Int): Any {
            return elementoModelo[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var vista = convertView
            if(vista == null) {
                vista = diseño_plano.inflate(R.layout.row_items, parent, false)
            }

            var tv_titulo_imagen = vista?.findViewById<TextView>(R.id.ImageTitle)
            var tv_descripcion_imagen = vista?.findViewById<TextView>(R.id.ImageInfo)
            var tv_vista_imagen = vista?.findViewById<ImageView>(R.id.ImageView)

            tv_titulo_imagen?.text = elementoModelo[position].titulo
            tv_descripcion_imagen?.text = elementoModelo[position].descripcion
            tv_vista_imagen?.setImageResource(elementoModelo[position].imagen!!)

            return vista!!

        }

    }

}