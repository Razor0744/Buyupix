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
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.presentation.viewmodelsfragment.StatisticsViewModel

class StatisticsFragment : Fragment() {

    private var binding: FragmentStatisticsBinding? = null

    private val viewModel by viewModel<StatisticsViewModel>()

    private var gamingPrice = 20f
    private var musicPrice = 199f
    private var cloudPrice = 230f
    private var otherPrice = 12.99f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        binding?.month?.text = viewModel.date()

        viewModel.fullPrice.observe(viewLifecycleOwner) { fullPrice ->
            binding?.price?.text = getString(R.string.fullPrice, String.format("%.2f", fullPrice))
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fullPrice()
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().execute(
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

        //список массивов
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(gamingPrice))
        entries.add(PieEntry(musicPrice))
        entries.add(PieEntry(cloudPrice))
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