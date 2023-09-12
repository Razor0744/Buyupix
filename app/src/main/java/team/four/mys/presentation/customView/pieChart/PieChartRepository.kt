package team.four.mys.presentation.customView.pieChart

interface PieChartRepository {

    fun setDataChart(list: List<Float>)

    fun startAnimation()
}