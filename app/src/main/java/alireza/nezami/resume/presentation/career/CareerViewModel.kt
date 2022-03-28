package alireza.nezami.resume.presentation.career

import alireza.nezami.resume.domain.model.Skill
import alireza.nezami.resume.domain.usecase.basic.GetBasicInformationUseCase
import alireza.nezami.resume.domain.usecase.basic.SaveBasicInformationUseCase
import alireza.nezami.resume.utils.observablevalidator.ObservableValidator
import alireza.nezami.resume.utils.observablevalidator.ValidationFlags
import android.util.Log
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
@HiltViewModel
class CareerViewModel
@Inject constructor(
    private val getUseCase: GetBasicInformationUseCase,
    private val saveUseCase: SaveBasicInformationUseCase,
) : ViewModel() {

    private val _skillList = ArrayList<Skill>()
    private val _skillListLiveData: MutableLiveData<List<Skill>> =
        MutableLiveData(arrayListOf())
    val newSkill = _skillListLiveData

    var career: MutableLiveData<CareerValidator> = MutableLiveData<CareerValidator>().also {
        it.value = CareerValidator()
    }

    var validator = createObservableValidator(career.value)

    private val _isAllValid = MutableLiveData(false)
    val isAllValid: LiveData<Boolean> = Transformations.map(_isAllValid) {
        if (it) {
            saveData()
        }
        _isAllValid.value == true
    }

    private fun saveData() {
//        GlobalScope.launch {
//            career.value?.apply {
//                saveUseCase.invoke(
//                    name!!,
//                    email!!,
//                    phone!!,
//                    address!!,
//                    ByteArray(1)
//                )
//            }
//        }
    }

    private fun createObservableValidator(career: CareerValidator?): ObservableValidator<CareerValidator> {
        return ObservableValidator(career!!, BR::class.java).apply {
            addRule("objective", ValidationFlags.FIELD_REQUIRED, "Enter career objective")

            addRule("exp", ValidationFlags.FIELD_REQUIRED, "Enter your total years of experience")
            addRule("exp", ValidationFlags.FIELD_MAX, "How old are you??!!!", limit = 2)
        }
    }

//    fun getInformation() {
//        viewModelScope.launch {
//            getUseCase().getOrNull()?.let { result ->
//                val newValue = BasicInfoValidator().apply {
//                    name = result.fullName
//                    address = result.residenceAddress
//                    phone = result.phoneNumber
//                    email = result.email
//                }
//                career.value = newValue
//                validator = createObservableValidator(newValue)
//            }
//        }
//    }

    fun validateAll() {
        _isAllValid.value = validator.validateAll()
    }

    fun addSkill() {
        career.value?.skillName?.let {
            _skillList.add(Skill(it))
            _skillListLiveData.value = _skillList
            career.value!!.skillName = ""

        }
        Log.i("TAG", "addSkill: ${_skillListLiveData.value?.size}")
    }

}