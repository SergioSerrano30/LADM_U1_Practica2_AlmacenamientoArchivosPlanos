package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.modificar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentModificarBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class ModificarFragment : Fragment() {

    private var _binding: FragmentModificarBinding? = null
    var pos = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val adapter = CustomAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ModificarViewModel::class.java)

        _binding = FragmentModificarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        leerTodo()
        cargarEtiquetas(recyclerView,adapter)

        binding.btnModificar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Aviso")
                .setMessage("¿Estás seguro que deseas modificar?")
                .setPositiveButton("Aceptar"){view,_->
                    if (!camposVacios()) {
                        if (celCorrecto()){
                            adapter.lClienteNom.set(pos, binding.txtNombre.text.toString())
                            adapter.lClienteEmp.set(pos, binding.txtEmpresa.text.toString())
                            adapter.lClienteCel.set(pos, binding.txtCelular.text.toString())
                            limpiar()
                            guardarTodo()

                            limpiarLista()
                            cargarEtiquetas(recyclerView, adapter)
                            leerTodo()
                            cargarEtiquetas(recyclerView, adapter)
                            Toast.makeText(requireContext(), "Modificado", Toast.LENGTH_LONG).show()
                            binding.lblClientes.setText("Modificar cliente")
                        }else{
                            Toast.makeText(requireContext(),"Formato de celular incorrecto",Toast.LENGTH_LONG).show()
                            binding.txtCelular.requestFocus()
                        }
                    }
                    else{
                        Toast.makeText(requireContext(),"Hay campos hacios",Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("Cancelar"){view,_->
                    limpiar()
                    Toast.makeText(requireContext(), "Operación cancelada", Toast.LENGTH_SHORT).show()
                    view.dismiss()}
                .show()


        }
        binding.btnActualizar.setOnClickListener {
            limpiarLista()
            cargarEtiquetas(recyclerView,adapter)
            leerTodo()
            cargarEtiquetas(recyclerView,adapter)
            Toast.makeText(requireContext(),"Actualizado",Toast.LENGTH_LONG).show()
        }

        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val nombre = adapter.lClienteNom[position]
                val empresa = adapter.lClienteEmp[position]
                val celular = adapter.lClienteCel[position]
                pos = position
                binding.txtNombre.setText(nombre)
                binding.txtEmpresa.setText(empresa)
                binding.txtCelular.setText(celular)
                binding.lblClientes.setText("Modificando a: \n${nombre}")
            }
        })

        return root
    }
    private fun celCorrecto():Boolean{
        if (binding.txtCelular.text.length==10)return true
        return false
    }
    private fun camposVacios():Boolean{
        if (binding.txtNombre.text.toString().equals("") ||
            binding.txtEmpresa.text.toString().equals("") ||
            binding.txtCelular.text.toString().equals("")) return true
        return false
    }
    private fun limpiarLista(){
        adapter.lClienteNom.clear()
        adapter.lClienteEmp.clear()
        adapter.lClienteCel.clear()
    }
    private fun leerTodo(){
        val listaClientes = leerArchivo()
        val cadena = ajustarClientes(listaClientes)
        separarDatos(cadena)
    }
    private fun leerArchivo(): ArrayList<String> {
        val arCadena:ArrayList<String> = ArrayList()
        try {
            val archivo = InputStreamReader(requireActivity().openFileInput("mxcliente.txt"))
            val listaCadena = archivo.readLines()
            val tam = listaCadena.size-1
            (0..tam).forEach(){
                arCadena.add(listaCadena.get(it))
            }
        }catch (e:Exception){
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(e.message).show()
        }
        return arCadena
    }
    private fun separarDatos(cadena: String) {
        val listaGeneral = cadena.split("\n")
        val tam = listaGeneral.size-1
        var tamLS = 0
        val lSeparada:ArrayList<String> = ArrayList()
        (0..tam).forEach {
            val listaSeparada = listaGeneral.get(it).split("|")
            tamLS = listaSeparada.size-1
            (0..tamLS).forEach(){
                lSeparada.add(listaSeparada.get(it))
            }
        }
        var index = 0
        var test = 1
        while (index < lSeparada.size-1){
            if (test==4) test=1
            if (test==1){adapter.lClienteNom.add(lSeparada.get(index))}
            if (test==2){adapter.lClienteEmp.add(lSeparada.get(index))}
            if (test==3){adapter.lClienteCel.add(lSeparada.get(index))}
            test++
            index++
        }
    }

    private fun limpiar() {
        binding.txtNombre.setText("")
        binding.txtEmpresa.setText("")
        binding.txtCelular.setText("")
        binding.lblClientes.setText("Modificar cliente")
    }
    private fun guardarTodo(){
        val listaClientes = obtenerClientes()
        val cadena = ajustarClientes(listaClientes)
        guardarArchivo(cadena)
    }
    private fun obtenerClientes(): ArrayList<String>{
        val listaClientes: ArrayList<String> = ArrayList()
        try {
            val ultimo = adapter.lClienteNom.size-1
            (0..ultimo).forEach(){
                var cad = adapter.lClienteNom[it]+"|"+
                        adapter.lClienteEmp[it]+"|"+
                        adapter.lClienteCel[it]
                listaClientes.add(cad)
            }
        }catch (e:Exception){
            AlertDialog.Builder(requireContext()).setMessage("Error al obtener clientes \n${e.message}")
        }
        return listaClientes
    }
    private fun ajustarClientes(lista:ArrayList<String>):String {
        var cadena = ""
        val tam = lista.size-1
        (0..tam).forEach() {
            cadena+=lista.get(it)+"\n"
        }
        return cadena
    }
    private fun guardarArchivo(cadena:String) {
        try {
            val archivo = OutputStreamWriter(requireActivity().openFileOutput("mxcliente.txt",0))
            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            binding.txtNombre.setText("")
            binding.txtEmpresa.setText("")
            binding.txtCelular.setText("")
            AlertDialog.Builder(requireContext())
                .setMessage("Se ha guardado correctamente").show()
        }catch (e:Exception){
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(e.message).show()
        }
    }
    fun cargarEtiquetas(recyclerView: RecyclerView, adapter: CustomAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}