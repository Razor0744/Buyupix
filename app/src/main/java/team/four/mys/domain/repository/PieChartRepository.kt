package team.four.mys.domain.repository

interface PieChartRepository {

    fun setDataChart(list: List<Float>)

    fun startAnimation()
}