package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}