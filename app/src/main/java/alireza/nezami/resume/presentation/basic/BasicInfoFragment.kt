package alireza.nezami.resume.presentation.basic

import alireza.nezami.resume.R
import alireza.nezami.resume.databinding.FragmentBasicInfoBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicInfoFragment : Fragment() {

    private val viewModel: BasicInfoViewModel by viewModels()

    private lateinit var binding: FragmentBasicInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stepIndicator.setCurrentStepPosition(0)

        viewModel.getInformation()

        viewModel.isAllValid.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(R.id.action_basicInfoFragment_to_careerFragment)
        }
    }


}