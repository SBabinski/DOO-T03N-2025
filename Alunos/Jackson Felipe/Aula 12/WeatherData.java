public class WeatherData {

    public Day[] days;
    public CurrentConditions currentConditions;


    public static class CurrentConditions {
        public double temp;
        public double humidity;
        public String conditions;
        public double precip;
        public double windspeed;
        public double winddir;
    }


    public static class Day {
        public double tempmax;
        public double tempmin;
    }
}