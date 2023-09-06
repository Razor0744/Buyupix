package team.four.mys.domain.usecases

class CategoryOfSubscriptionUseCase {

    fun execute(name: String): String {
        return when (name) {
            "Apple One" -> "Other"
            "Discord Nitro" -> "Gaming"
            "Dr.Web" -> "Defence"
            "Dropbox" -> "Cloud"
            "Flo" -> "Other"
            "Google Drive" -> "Cloud"
            "Kaspersky" -> "Defence"
            "Kion" -> "Movies"
            "MTC Premium" -> "Other"
            "IVI" -> "Movies"
            "Megogo" -> "Movies"
            "Microsoft 365" -> "Other"
            "MyBook" -> "Books"
            "Netflix" -> "Movies"
            "NordVPN" -> "Other"
            "Ozon Premium" -> "Other"
            "PornHub" -> "Other"
            "Premier One" -> "Movies"
            "Pure" -> "Other"
            "Spotify" -> "Music"
            "Start" -> "Movies"
            "Tinkoff Pro" -> "Other"
            "VK Combo" -> "Other"
            "YouTube" -> "Movies"
            "Амедиатека" -> "Movies"
            "ЛитРес" -> "Books"
            "СберПрайм" -> "Other"
            else -> "Other"
        }
    }
}