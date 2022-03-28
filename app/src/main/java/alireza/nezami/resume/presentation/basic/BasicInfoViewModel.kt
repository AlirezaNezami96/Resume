package alireza.nezami.resume.presentation.basic

import alireza.nezami.resume.domain.usecase.basic.GetBasicInformationUseCase
import alireza.nezami.resume.domain.usecase.basic.GetBasicInformationUseCaseImpl
import alireza.nezami.resume.domain.usecase.basic.SaveBasicInformationUseCase
import alireza.nezami.resume.utils.observablevalidator.ObservableValidator
import alireza.nezami.resume.utils.observablevalidator.ValidationFlags
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
@HiltViewModel
class BasicInfoViewModel
@Inject constructor(
    private val getUseCase: GetBasicInformationUseCase,
    private val saveUseCase: SaveBasicInformationUseCase,
) : ViewModel() {

    var basic: MutableLiveData<BasicInfoValidator> = MutableLiveData<BasicInfoValidator>().also {
        it.value = BasicInfoValidator()
    }

    var validator = createObservableValidator(basic.value)

    private val _isAllValid = MutableLiveData(false)
    val isAllValid: LiveData<Boolean> = Transformations.map(_isAllValid) {
        if (it) {
            saveData()
        }
        _isAllValid.value == true
    }

    private fun saveData() {
        GlobalScope.launch {
            basic.value?.apply {
                saveUseCase.invoke(
                    name!!,
                    email!!,
                    phone!!,
                    address!!,
                    ByteArray(1)
                )
            }
        }
    }

    private fun createObservableValidator(basic: BasicInfoValidator?): ObservableValidator<BasicInfoValidator> {
        return ObservableValidator(basic!!, BR::class.java).apply {
            addRule("name", ValidationFlags.FIELD_REQUIRED, "Enter your full name")

            addRule("email", ValidationFlags.FIELD_REQUIRED, "Enter your email")
            addRule("email", ValidationFlags.FIELD_EMAIL, "Enter a valid email")

            addRule("phone", ValidationFlags.FIELD_REQUIRED, "Enter your phone number")
            addRule("phone", ValidationFlags.FIELD_MIN, "Phone number is too short", limit = 11)
            addRule("phone", ValidationFlags.FIELD_MAX, "Phone number is too long", limit = 11)

            addRule("address", ValidationFlags.FIELD_REQUIRED, "Enter your address")
        }
    }

    fun getInformation() {
        viewModelScope.launch {
            getUseCase().getOrNull()?.let { result ->
                val newValue = BasicInfoValidator().apply {
                    name = result.fullName
                    address = result.residenceAddress
                    phone = result.phoneNumber
                    email = result.email
                }
                basic.value = newValue
                validator = createObservableValidator(newValue)
            }
        }
    }

    fun validateAll() {
        _isAllValid.value = validator.validateAll()
    }

}