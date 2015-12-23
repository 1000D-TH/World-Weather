package com.haringeymobile.ukweather.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.haringeymobile.ukweather.R;
import com.haringeymobile.ukweather.settings.SettingsActivity;
import com.haringeymobile.ukweather.weather.ThreeHourlyForecastDisplayMode;
import com.haringeymobile.ukweather.weather.WeatherInfoType;
import com.haringeymobile.ukweather.database.CityTable;

public class SharedPrefsHelper {

    public static final String SHARED_PREFS_KEY =
            "com.haringeymobile.ukweather.PREFERENCE_FILE_KEY";

    private static final String LAST_SELECTED_CITY_ID = "city id";
    private static final String LAST_SELECTED_WEATHER_INFO_TYPE = "weather info type";

    /**
     * Obtains the ID of the city that was last queried by the user.
     */
    public static int getCityIdFromSharedPrefs(Context context) {
        return getSharedPreferences(context).getInt(LAST_SELECTED_CITY_ID,
                CityTable.CITY_ID_DOES_NOT_EXIST);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE);
    }

    /**
     * Saves the specified city ID.
     *
     * @param cityId OpenWeatherMap city ID
     */
    public static void putCityIdIntoSharedPrefs(Context context, int cityId) {
        getEditor(context).putInt(LAST_SELECTED_CITY_ID, cityId).apply();
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    /**
     * Obtains the {@link WeatherInfoType} for the last user's query.
     */
    public static WeatherInfoType getLastWeatherInfoTypeFromSharedPrefs(Context context) {
        int lastSelectedWeatherInfoTypeId = getSharedPreferences(context).getInt(
                LAST_SELECTED_WEATHER_INFO_TYPE, WeatherInfoType.CURRENT_WEATHER.getId());
        return WeatherInfoType.getTypeById(lastSelectedWeatherInfoTypeId);
    }

    /**
     * Saves the {@link WeatherInfoType} that was last queried by the user.
     *
     * @param weatherInfoType a kind of weather information
     */
    public static void putLastWeatherInfoTypeIntoSharedPrefs(Context context,
                                                             WeatherInfoType weatherInfoType) {
        getEditor(context).putInt(LAST_SELECTED_WEATHER_INFO_TYPE, weatherInfoType.getId()).apply();
    }

    /**
     * Obtains the three-hourly forecast display mode from the shared preferences.
     *
     * @return weather forecast display mode preferred by the user, such as a list, or a horizontal
     * swipe view
     */
    public static ThreeHourlyForecastDisplayMode getForecastDisplayMode(Context context) {
        String forecastDisplayModeIdString = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(SettingsActivity.PREF_FORECAST_DISPLAY_MODE, context.getResources()
                        .getString(R.string.pref_forecast_display_mode_id_default));
        int forecastDisplayModeId = Integer.parseInt(forecastDisplayModeIdString);
        return ThreeHourlyForecastDisplayMode.getForecastDisplayModeById(forecastDisplayModeId);
    }

}