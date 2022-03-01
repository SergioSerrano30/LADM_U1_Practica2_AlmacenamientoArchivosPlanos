package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MostrarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el fragmento mostrar"
    }
    val text: LiveData<String> = _text
}