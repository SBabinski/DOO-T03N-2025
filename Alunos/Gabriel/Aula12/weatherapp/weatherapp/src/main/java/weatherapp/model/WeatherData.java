package Aula12.weatherapp.weatherapp.src.main.java.weatherapp.model;

public class WeatherData {
    private double currentTemp;
    private double maxTemp;
    private double minTemp;
    private int humidity;
    private String conditions;
    private double precipitation;
    private double windSpeed;
    private double windDirection; // Agora como número para conversão

    public WeatherData(double currentTemp, double maxTemp, double minTemp, int humidity,
                       String conditions, double precipitation, double windSpeed, double windDirection) {
        this.currentTemp = currentTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.conditions = conditions;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public double getCurrentTemp() { return currentTemp; }
    public double getMaxTemp() { return maxTemp; }
    public double getMinTemp() { return minTemp; }
    public int getHumidity() { return humidity; }
    public String getConditions() { return conditions; }
    public double getPrecipitation() { return precipitation; }
    public double getWindSpeed() { return windSpeed; }
    public double getWindDirectionValue() { return windDirection; }

    public String getWindDirectionCardinal() {
        String[] directions = { "Norte", "Nordeste", "Leste", "Sudeste", "Sul", "Sudoeste", "Oeste", "Noroeste" };
        int index = (int) Math.round(((windDirection % 360) / 45)) % 8;
        return directions[index];
    }
}
