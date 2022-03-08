package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar.MostrarFragment
import java.io.InputStreamReader
import java.util.ArrayList

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener
    //var tam=mostrar.lCliente.size-1

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    //Arreglos
    val lClienteNom: ArrayList<String> = ArrayList()
    val lClienteEmp: ArrayList<String> = ArrayList()
    val lClienteCel: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v,mListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemNombre.text =  lClienteNom[i]
        viewHolder.itemEmpresa.text = lClienteEmp[i]
        viewHolder.itemCelular.text = lClienteCel[i]
        //viewHolder.itemImage.setImageResource(R.drawable.ic_baseline_person_24)
        //lCliente.add(nombre[i])
    }

    inner class ViewHolder(itemView: View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        //var itemImage: ImageView= itemView.findViewById(R.id.item_image)
        var itemNombre: TextView= itemView.findViewById(R.id.lblNombre)
        var itemEmpresa: TextView= itemView.findViewById(R.id.lblEmpresa)
        var itemCelular: TextView= itemView.findViewById(R.id.lblCelular)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return lClienteNom.size//nombre.size
    }
}