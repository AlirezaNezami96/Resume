package alireza.nezami.resume.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
class HomeViewModel(

) : ViewModel() {

    private val _showCreateNew = MutableLiveData(false)
    val showCreateNew: LiveData<Boolean> = _showCreateNew

    fun onCreateNew() {
        _showCreateNew.value = true
    }

    fun onUpdateExciting() {

    }

    fun onSharePDF() {

    }
}