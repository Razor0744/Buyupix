package team.four.mys.domain.usecases

class CategoryOfSubscriptionUseCase {

    fun execute(name: String): String {
        when (name) {
            "Apple One" -> return "Other"
            "Discord Nitro" -> return "Gaming"
            "Dr.Web" -> return "Defence"
            "Dropbox" -> return "Cloud"
            "Flo" -> return "Other"
            "Google Drive" -> return "Cloud"
            "Kaspersky" -> return "Defence"
            "Kion" -> return "Movies"
            "MTC Premium" -> return "Other"
            "IVI" -> return "Movies"
            "Megogo" -> return "Movies"
            "Microsoft 365" -> return "Other"
            "MyBook" -> return "Books"
            "Netflix" -> return "Movies"
            "NordVPN" -> return "Other"
            "Ozon Premium" -> return "Other"
            "PornHub" -> return "Other"
            "Premier One" -> return "Movies"
            "Pure" -> return "Other"
            "Spotify" -> return "Music"
            "Start" -> return "Movies"
            "Tinkoff Pro" -> return "Other"
            "VK Combo" -> return "Other"
            "YouTube" -> return "Movies"
            "Амедиатека" -> return "Movies"
            "ЛитРес" -> return "Books"
            "СберПрайм" -> return "Other"
            else -> return "Other"
        }
    }
}