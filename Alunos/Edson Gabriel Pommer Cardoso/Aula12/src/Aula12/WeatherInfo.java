package Aula12;

public class WeatherInfo {
    private final double temp, tempMax, tempMin, humidity, precipitation, windSpeed;
    private final String condition, windDirection;

    public WeatherInfo(double temp, double tempMax, double tempMin,
                       double humidity, String condition,
                       double precipitation, double windSpeed,
                       String windDirection) {
        this.temp = temp;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.condition = condition;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "Clima Atual:\n" +
               "Temperatura: " + temp + "°C\n" +
               "Máxima:      " + tempMax + "°C\n" +
               "Mínima:      " + tempMin + "°C\n" +
               "Umidade:     " + humidity + "%\n" +
               "Condição:    " + condition + "\n" +
               "Precipitação:" + precipitation + " mm\n" +
               "Vento:       " + windSpeed + " km/h (" + windDirection + ")";
    }
}
