package alireza.nezami.resume.presentation.basic

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
 * Created by Alireza Nezami on 3/27/2022.
 */
class BasicInfoValidator : BaseObservable() {

    @Bindable
    var name: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var email: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @Bindable
    var phone: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phone)
        }

    @Bindable
    var address: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }
}