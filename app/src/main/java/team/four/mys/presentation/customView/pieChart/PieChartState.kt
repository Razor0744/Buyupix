package team.four.mys.presentation.customView.pieChart

import android.os.Parcelable
import android.view.View

class PieChartState(
    superSavedState: Parcelable?,
    val dataList: List<Float>
) : View.BaseSavedState(superSavedState), Parcelable {
}