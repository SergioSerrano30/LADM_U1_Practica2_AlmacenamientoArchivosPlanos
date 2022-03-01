package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //Arreglos
    val titles = arrayOf("Sergio","Diana","Rosy","Miranda","Rosangela")
    val details = arrayOf("Flash delivery","Chapys Sport", "El cevichin","Cenaduria Tovar","Pollo bronco")
    val images =  intArrayOf(R.drawable.icono1,R.drawable.icono2,R.drawable.icono3,R.drawable.icono4,R.drawable.icono5)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetails.text = details[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetails: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.lblTitulo)
            itemDetails = itemView.findViewById(R.id.lblDescripcion)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}