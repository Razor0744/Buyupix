package team.four.mys.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentStatisticsBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.other.SetNavigationColor
import team.four.mys.presentation.viewmodelsfragment.StatisticsViewModel

class StatisticsFragment : Fragment() {

    private var binding: FragmentStatisticsBinding? = null

    private val viewModel by viewModel<StatisticsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        binding?.month?.text = viewModel.date()

        binding?.gamingCategory?.text = getString(
            R.string.gamingCategory,
            String.format("%.2f", (activity as MainActivity).gamingPrice)
        )
        binding?.defenceCategory?.text = getString(
            R.string.defenceCategory,
            String.format("%.2f", (activity as MainActivity).defencePrice)
        )
        binding?.cloudCategory?.text = getString(
            R.string.cloudCategory,
            String.format("%.2f", (activity as MainActivity).cloudPrice)
        )
        binding?.moviesCategory?.text = getString(
            R.string.moviesCategory,
            String.format("%.2f", (activity as MainActivity).moviesPrice)
        )
        binding?.booksCategory?.text = getString(
            R.string.booksCategory,
            String.format("%.2f", (activity as MainActivity).booksPrice)
        )
        binding?.musicCategory?.text = getString(
            R.string.musicCategory,
            String.format("%.2f", (activity as MainActivity).musicPrice)
        )
        binding?.otherCategory?.text = getString(
            R.string.otherCategory,
            String.format("%.2f", (activity as MainActivity).otherPrice)
        )

        viewModel.fullPrice.observe(viewLifecycleOwner) { fullPrice ->
            binding?.price?.text = getString(R.string.fullPrice, String.format("%.2f", fullPrice))
        }

        viewModel.numberOfSubscriptions.observe(viewLifecycleOwner) { number ->
            binding?.textSubscriptions2?.text = number.toString()
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fullPrice()
            viewModel.getNumberOfSubscriptions()
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationColor().execute(
            SetNavigationBarParam(
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        pieChart()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun pieChart() {
        //процентное значение
        binding?.pieChart?.setUsePercentValues(false)
        //надпись внизу справа
        binding?.pieChart?.description?.isEnabled = false
        //отступы и соответственно уменьшение круга
        binding?.pieChart?.setExtraOffsets(0f, 0f, 0f, 0f)

        //будет ли отверстиe в центре
        binding?.pieChart?.isDrawHoleEnabled = true
        //цвет отверстия
        binding?.pieChart?.setHoleColor(
            ResourcesCompat.getColor(
                resources,
                R.color.backgroundMain,
                null
            )
        )

        //радиус отверстия
        binding?.pieChart?.holeRadius = 92f
        //радиус покраски около отверстия
        binding?.pieChart?.transparentCircleRadius = 0f

        //отключить текст слева внизу
        binding?.pieChart?.legend?.isEnabled = false
        binding?.pieChart?.setEntryLabelColor(Color.BLACK)
        binding?.pieChart?.setEntryLabelTextSize(12f)

        val gamingPrice = (activity as MainActivity).gamingPrice
        val defencePrice = (activity as MainActivity).defencePrice
        val cloudPrice = (activity as MainActivity).cloudPrice
        val moviesPrice = (activity as MainActivity).moviesPrice
        val booksPrice = (activity as MainActivity).booksPrice
        val musicPrice = (activity as MainActivity).musicPrice
        val otherPrice = (activity as MainActivity).otherPrice

        //список массивов
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(gamingPrice))
        entries.add(PieEntry(defencePrice))
        entries.add(PieEntry(cloudPrice))
        entries.add(PieEntry(moviesPrice))
        entries.add(PieEntry(booksPrice))
        entries.add(PieEntry(musicPrice))
        entries.add(PieEntry(otherPrice))
        //данные
        val dataSet = PieDataSet(entries, "Mobile OS")
        //иконки
        dataSet.setDrawIcons(false)

        //расстояние между
        dataSet.sliceSpace = 0f//12f
        //уменьшение всей диограммы
        dataSet.selectionShift = 30f

        binding?.pieChart?.setDrawRoundedSlices(true)
        binding?.pieChart?.setTouchEnabled(false)

        //цвета
        val colors: ArrayList<Int> = ArrayList()
        colors.add(ResourcesCompat.getColor(resources, R.color.blue, null))
        colors.add(ResourcesCompat.getColor(resources, R.color.yellow, null))
        colors.add(ResourcesCompat.getColor(resources, R.color.blueMain, null))
        colors.add(ResourcesCompat.getColor(resources, R.color.red, null))
        colors.add(ResourcesCompat.getColor(resources, R.color.blueLight, null))
        colors.add(ResourcesCompat.getColor(resources, R.color.violet, null))
        colors.add(ResourcesCompat.getColor(resources, R.color.turquoise, null))
        dataSet.colors = colors

        val data = PieData(dataSet)
        //размер текста
        data.setValueTextSize(0f)
        binding?.pieChart?.data = data

        // undo all highlights
        binding?.pieChart?.highlightValues(null)

        // loading chart
        binding?.pieChart?.invalidate()
    }
}