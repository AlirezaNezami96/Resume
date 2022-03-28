package alireza.nezami.resume.presentation.career

import alireza.nezami.resume.R
import alireza.nezami.resume.databinding.FragmentCareerBinding
import alireza.nezami.resume.domain.model.Skill
import alireza.nezami.resume.presentation.utils.SimpleAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CareerFragment : Fragment() {

    private val viewModel: CareerViewModel by viewModels()

    private lateinit var binding: FragmentCareerBinding

    private lateinit var listAdapter: SimpleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCareerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListView(this.requireContext())

        binding.stepIndicator.setCurrentStepPosition(1)

        viewModel.isAllValid.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(R.id.action_basicInfoFragment_to_careerFragment)
        }

        viewModel.newSkill.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                addSkill(it.last())
            }
        }
    }

    private fun addSkill(last: Skill) {
        listAdapter.add(last.name)
    }

    private fun setupListView(context: Context) {
        listAdapter =
            SimpleAdapter(arrayListOf())
        binding.recyclerView.adapter = listAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }


}