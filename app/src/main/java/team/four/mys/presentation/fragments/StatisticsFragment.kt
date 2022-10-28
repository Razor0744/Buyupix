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
import com.github.mikephil.charting.renderer.PieChartRenderer
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

        //текст в центре
        binding?.pieChart?.centerText = "Pie chart"
        //размер этого текста
        binding?.pieChart?.setCenterTextSize(24f)

        //отключить текст слева внизу
        binding?.pieChart?.legend?.isEnabled = false
        binding?.pieChart?.setEntryLabelColor(Color.BLACK)
        binding?.pieChart?.setEntryLabelTextSize(12f)

        //список массивов
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(70f))
        entries.add(PieEntry(20f))
        entries.add(PieEntry(100f))
        //данные
        val dataSet = PieDataSet(entries, "Mobile OS")
        //иконки
        dataSet.setDrawIcons(false)

        //расстояние между
        dataSet.sliceSpace = 12f
        //уменьшение всей диограммы
        dataSet.selectionShift = 30f


        //цвета
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.grey_100))
        colors.add(resources.getColor(R.color.yellow))
        colors.add(resources.getColor(R.color.red))
        dataSet.colors = colors

        val data = PieData(dataSet)
        //размер текста
        data.setValueTextSize(0f)
        binding?.pieChart?.data = data

        // undo all highlights
        binding?.pieChart?.highlightValues(null)

        //кстомный рендер
        binding?.pieChart?.setDrawRoundedSlices(true)
        binding?.pieChart?.setDrawSlicesUnderHole(false)

        // loading chart
        binding?.pieChart?.invalidate()
    }
}