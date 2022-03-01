package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.insertar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentInsertarBinding
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar.MostrarFragment

class InsertarFragment : Fragment() {

    private var _binding: FragmentInsertarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(InsertarViewModel::class.java)

        _binding = FragmentInsertarBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = CustomAdapter()
        var disponible = 0
        binding.btnInsertar.setOnClickListener {
            var i = 0
            for (i in adapter.nombre.indices){
                if (adapter.nombre[i]==""){
                    disponible=i
                    AlertDialog.Builder(requireContext())
                        .setMessage("Disponible en: ${disponible}").show()
                }
            }
            if (disponible != 0){
                var nom = binding.txtNombre.text.toString()
                var emp = binding.txtEmpresa.text.toString()
                var cel = binding.txtCelular.text.toString()
                adapter.nombre[disponible] = nom
                adapter.empresa[disponible] = emp
                adapter.celular[disponible] = cel
                Toast.makeText(requireContext(),"Se agrego: ${adapter.nombre[disponible]}",Toast.LENGTH_LONG).show()

            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}