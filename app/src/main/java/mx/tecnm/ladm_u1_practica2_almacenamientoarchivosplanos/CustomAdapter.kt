package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    //Arreglos
    val images =  intArrayOf(R.drawable.icono1,R.drawable.icono2,R.drawable.icono3,R.drawable.icono4,R.drawable.icono5,R.drawable.ic_baseline_person_24,R.drawable.ic_baseline_person_24)
    val nombre = arrayOf("Sergio","Diana","Rosy","Jason","Rosangela","","")
    val empresa = arrayOf("Flash delivery","Chapys Sport", "El cevichin","Pato Industries","Pollo bronco","","")
    val celular = arrayOf("311 122 7276","311 100 4014","311 143 5730","311 256 7810","323 893 4125","","")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v,mListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemNombre.text = nombre[i]
        viewHolder.itemEmpresa.text = empresa[i]
        viewHolder.itemCelular.text = celular[i]
        viewHolder.itemImage.setImageResource(images[i])


    }

    inner class ViewHolder(itemView: View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView= itemView.findViewById(R.id.item_image)
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
        return nombre.size
    }
}