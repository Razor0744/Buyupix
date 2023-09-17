package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import team.four.mys.databinding.FragmentChangeSubscriptionBinding

class ChangeSubscriptionFragment : Fragment() {

    private var _binding: FragmentChangeSubscriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeSubscriptionBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}