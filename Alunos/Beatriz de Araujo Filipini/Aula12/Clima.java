public class Clima {
    private String address; 
    private double currentTemp;
    private double maxTemp;     
    private double minTemp;     
    private double humidity;    
    private String conditions;  
    private double precipitation; 
    private double windSpeed;   
    private double windDir;     

    public Clima() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDir() {
        return windDir;
    }

    public void setWindDir(double windDir) {
        this.windDir = windDir;
    }
    public String getWindDirectionText() {
        if (windDir >= 337.5 || windDir < 22.5) return "Norte";
        if (windDir >= 22.5 && windDir < 67.5) return "Nordeste";
        if (windDir >= 67.5 && windDir < 112.5) return "Leste";
        if (windDir >= 112.5 && windDir < 157.5) return "Sudeste";
        if (windDir >= 157.5 && windDir < 202.5) return "Sul";
        if (windDir >= 202.5 && windDir < 247.5) return "Sudoeste";
        if (windDir >= 247.5 && windDir < 292.5) return "Oeste";
        if (windDir >= 292.5 && windDir < 337.5) return "Noroeste";
        return "N/A";
    }
}