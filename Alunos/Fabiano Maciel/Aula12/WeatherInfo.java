package Aula12;

public class WeatherInfo {
    private double currentTemperature;
    private double maxTemperature;
    private double minTemperature;
    private int humidity;
    private String condition;
    private double precipitation;
    private double windSpeed;
    private String windDirection;

    public WeatherInfo(
            double currentTemperature,
            double maxTemperature,
            double minTemperature,
            int humidity,
            String condition,
            double precipitation,
            double windSpeed,
            String windDirection) {
        this.currentTemperature = currentTemperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.humidity = humidity;
        this.condition = condition;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    @Override
    public String toString() {
        return String.format(
            "Clima atual:\n" +
            "Temperatura: %.1f°C (Mín: %.1f°C, Máx: %.1f°C)\n" +
            "Umidade: %d%%\n" +
            "Condição: %s\n" +
            "Precipitação: %.1f mm\n" +
            "Vento: %.1f km/h, direção: %s",
            currentTemperature, minTemperature, maxTemperature,
            humidity, condition, precipitation, windSpeed, windDirection
        );
    }
}