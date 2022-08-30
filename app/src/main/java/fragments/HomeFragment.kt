package fragments

import adapters.CustomRecyclerAdapterLanguage
import adapters.CustomRecyclerAdapterSubscriptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ather.LocaleHelper
import team.four.mys.DataLanguage
import team.four.mys.MainActivity
import team.four.mys.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var adapterSubscriptions: CustomRecyclerAdapterSubscriptions? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        adapterSubscriptions = CustomRecyclerAdapterSubscriptions(requireContext(), )
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = adapterSubscriptions

        date()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun date(){
        val month: String = SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
        binding?.month?.text = month
    }
}