public class WeatherInfo {
    private double temperature;
    private double tempMax;
    private double tempMin;
    private double humidity;
    private String condition;
    private double precipitation;
    private double windSpeed;
    private String windDirection;

    public WeatherInfo(double temperature, double tempMax, double tempMin, double humidity, String condition,
                       double precipitation, double windSpeed, String windDirection) {
        this.temperature = temperature;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.condition = condition;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public double getTemperature() { return temperature; }
    public double getTempMax() { return tempMax; }
    public double getTempMin() { return tempMin; }
    public double getHumidity() { return humidity; }
    public String getCondition() { return condition; }
    public double getPrecipitation() { return precipitation; }
    public double getWindSpeed() { return windSpeed; }
    public String getWindDirection() { return windDirection; }

    @Override
    public String toString() {
        return "Clima Atual:\n" +
                "Temperatura: " + temperature + "°C\n" +
                "Máxima: " + tempMax + "°C\n" +
                "Mínima: " + tempMin + "°C\n" +
                "Umidade: " + humidity + "%\n" +
                "Condição: " + condition + "\n" +
                "Precipitação: " + precipitation + " mm\n" +
                "Vento: " + windSpeed + " km/h (" + windDirection + ")";
    }
}