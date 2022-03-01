package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentMostrarBinding

class MostrarFragment : Fragment() {

    private var _binding: FragmentMostrarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MostrarViewModel::class.java)

        _binding = FragmentMostrarBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter()
        //cargarEtiquetas(recyclerView,adapter)
        binding.btnActualizar.setOnClickListener {
            cargarEtiquetas(recyclerView,adapter)
            Toast.makeText(requireContext(),"Actualizado",Toast.LENGTH_LONG).show()
        }

        adapter.setOnItemClickListener(object :CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                var nombre = adapter.nombre[position]
                Toast.makeText(requireContext(),"Seleccionaste: ${nombre}",Toast.LENGTH_LONG).show()
            }

        })

        return root
    }
    fun cargarEtiquetas(recyclerView:RecyclerView,adapter: CustomAdapter){
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}