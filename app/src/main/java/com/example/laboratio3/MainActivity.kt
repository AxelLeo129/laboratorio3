package com.example.laboratio3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var lista_modales = ArrayList<Modal>()
    var titulos = arrayOf(
        "OMS no responderá ...",
        "¡Se acabaron los r...",
        "Excentricidades y ...",
        "Guatemala vs. Nica...",
        "Covid: Enfermera s...",
        "Atalanta 0 - Real ...",
        "Así están los bloq...",
        "Los cinco videojue...",
        "Groot, el reciente..."
    )
    var descripciones = arrayOf(
        "Como 'una mentira ...",
        "Después de publica...",
        "Tras la captura de...",
        "Las selecciones de...",
        "Una de las primera...",
        "Una genialidad de ...",
        "Dos manifestacione...",
        "En el tercer mes d...",
        "La finca El Amate ..."
    )
    var imagenes = arrayOf(
        "https://www.soy502.com/sites/default/files/styles/full_node/public/2021/Feb/24/covax_alejandro_giammattei_vacunas_covid_19_coronavirus_mecanismo_covas_organizacion_mundial_de_la_salud_oms_guatemala_soy502.jpg",
        "https://imagenes.milenio.com/p-jyGqXvWFWii1wxHyB4CwFn-zM=/958x596/https://www.milenio.com/uploads/media/2021/02/24/spiderman-way-home-llegara-finales.jpg",
        "https://www.soy502.com/sites/default/files/styles/full_node/public/2021/Feb/24/emma_coronel_joaquin_guzman_loera_joaquin_el_chapo_guzman_el_chapo_narcotrafico_guatemala_soy502.jpg",
        "https://futbolcentroamerica.com/__export/1614191751706/sites/futbolcentroamerica/img/2021/02/24/guatemala_vs_nicaragua_amistoso_fifa_crop1614191737560.jpg_1693159006.jpg",
        "https://www.soy502.com/sites/default/files/styles/full_node/public/2021/Feb/24/vacuna_enfermera_contagia_mundo.jpg",
        "https://as01.epimg.net/futbol/imagenes/2021/02/24/champions/1614189778_462917_1614203958_noticia_normal.jpg",
        "https://www.soy502.com/sites/default/files/styles/full_node/public/2021/Feb/24/concentraciones_militares_zona_1_trafico_guatemala_soy502.jpeg",
        "https://elcomercio.pe/resizer/k0JNPohxzVetjfN4NFUWePtY9jQ=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/BDGS3ZUXNJHLXKPYZGVPKYPO2M.jpg",
        "https://www.soy502.com/sites/default/files/styles/full_node/public/2021/Feb/18/groot_amate_personaje_guardian_finca_turismo_guatemala_soy5029.jpg"
    )
    var estado = false

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
        if(!estado) {
            estado = true
            val numero_aleatorio = Random.nextInt(0, 9)
            lista_modales.add(Modal(titulos[numero_aleatorio], descripciones[numero_aleatorio], imagenes[numero_aleatorio]))
            renderGrid(lista_modales)
        }
    }

    private fun renderGrid(listado: ArrayList<Modal>) {
        var adaptador_personalizado = AdaptadorPersonalizado(listado, this)

        var grid_view = findViewById<GridView>(R.id.gridView)
        grid_view.adapter = adaptador_personalizado


        grid_view.setOnItemLongClickListener { arg0, arg1, position, arg3 ->
            if(!estado) {
                estado = true
                val numero_aleatorio = Random.nextInt(0, 9)
                lista_modales.set(position, Modal(titulos[numero_aleatorio], descripciones[numero_aleatorio], imagenes[numero_aleatorio]))
                renderGrid(lista_modales)
            }
            true
        }

        grid_view.setOnItemClickListener { arg0, arg1, position, arg3 ->
            if(!estado) {
                estado = true
                lista_modales.removeAt(position)
                renderGrid(lista_modales)
            }
            true
        }

        estado = false

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
            //tv_vista_imagen?.setImageResource(elementoModelo[position].imagen!!)
            Picasso.get().load(elementoModelo[position].imagen!!).into(tv_vista_imagen)

            return vista!!

        }

    }

}