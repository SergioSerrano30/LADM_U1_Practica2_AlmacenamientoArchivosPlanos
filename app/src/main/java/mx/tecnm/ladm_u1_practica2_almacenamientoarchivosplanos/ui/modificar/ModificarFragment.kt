package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.modificar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentModificarBinding

class ModificarFragment : Fragment() {

    private var _binding: FragmentModificarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ModificarViewModel::class.java)

        _binding = FragmentModificarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}