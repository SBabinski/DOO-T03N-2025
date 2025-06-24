package weather;

public class WeatherInfo {
    private String address;
    private double temp;
    private double tempMax;
    private int humidity;
    private String conditionsString;
    private double windSpeed;

    public WeatherInfo(String addressString, double temp, double tempMax, int humidity, String conditionsString, double windSpeed, String address){
        this.address = address;
        this.temp = temp;
        this.tempMax = tempMax;
        this.humidity = humidity;
        this.conditionsString = conditionsString;
        this.windSpeed = windSpeed;
    }

    public WeatherInfo(String asText, double asDouble, double asDouble2, double asDouble3, int asInt, String asText2,
            double asDouble4, double asDouble5, double asDouble6) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString(){
        return "Cidade: " + address +
               "\nTemperatura: " + temp + "°C" +
               "\nMáxima: " + tempMax + "°C" +
               "\nUmidade: " + humidity + "%" +
               "\nCondições: " + conditionsString +
               "\nVento: " + windSpeed + "Km/h";
    }
}

   


    


