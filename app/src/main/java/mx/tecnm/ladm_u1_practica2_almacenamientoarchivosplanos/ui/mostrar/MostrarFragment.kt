package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}