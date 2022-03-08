package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InicioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el fragmento inicio"
    }
    val text: LiveData<String> = _text
}