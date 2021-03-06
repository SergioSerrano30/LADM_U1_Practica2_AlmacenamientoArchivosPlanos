package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.eliminar

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentEliminarBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class eliminar : Fragment() {

    private var _binding: FragmentEliminarBinding? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEliminarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnTest.setOnClickListener {
            AlertDialog.Builder(requireContext()).setMessage(":)").show()
        }

        return inflater.inflate(R.layout.fragment_eliminar, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            eliminar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}