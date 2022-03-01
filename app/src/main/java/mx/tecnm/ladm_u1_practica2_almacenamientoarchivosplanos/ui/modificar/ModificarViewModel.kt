package mx.tecnm.ladm_u1_practica2_almacenamientoarchivosplanos.ui.modificar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModificarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el fragmento modificar"
    }
    val text: LiveData<String> = _text
}