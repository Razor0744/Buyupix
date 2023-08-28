package team.four.mys.domain.usecases

import android.content.Context
import java.util.Locale

class LocaleHelperUseCase {

    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language).toString()
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String {
        return getPersistedData(context, Locale.getDefault().language).toString()
    }

    fun setLocale(context: Context, language: String): Context {
        persist(context, language)

        return updateResources(context, language)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String? {
        val preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        return preferences.getString("Locale", defaultLanguage)
    }

    private fun persist(context: Context, language: String) {
        val preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString("Locale", language)
        editor.apply()
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }
}