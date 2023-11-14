package team.four.mys.domain.usecases

class CheckTimeSyncUseCase {

    fun execute(timeFirebase: String, timeSettings: String): Boolean {
        return timeFirebase.replace(".", "").toInt() > timeSettings.replace(".", "").toInt()
    }
}