package team.four.mys.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.four.mys.R
import team.four.mys.databinding.FragmentStatisticsBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetStatusBarUseCase

class StatisticsFragment : Fragment() {

    private var binding: FragmentStatisticsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                requireContext(),
                requireActivity(),
                requireContext().getColor(R.color.backgroundMain)
            )
        )

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}