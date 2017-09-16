package noh.seung.hwa.weather.retrofits;

import noh.seung.hwa.weather.models.current.CurrentWeather;
import noh.seung.hwa.weather.models.forecast.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    String APP_ID = "7a7cf1a5fb00c83322d4bf4ca43d5b83";

    @GET("weather?lang=kr&units=metric&appid=" + APP_ID)
    Call<CurrentWeather> getCurrentWeather(@Query("q") String cityName);

    @GET("forecast?lang=kr&units=metric&appid=" + APP_ID)
    Call<Forecast> getForecast(@Query("q") String cityName);
}
