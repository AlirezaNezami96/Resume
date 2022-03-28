package alireza.nezami.resume.presentation.career

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
 * Created by Alireza Nezami on 3/27/2022.
 */
class CareerValidator : BaseObservable() {

    @Bindable
    var objective: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.objective)
        }

    @Bindable
    var exp: Int? = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.exp)
        }

//    @Bindable
//    var skills: List<String>? = arrayListOf()
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.skills)
//        }

    @Bindable
    var skillName: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.skillName)
        }

}