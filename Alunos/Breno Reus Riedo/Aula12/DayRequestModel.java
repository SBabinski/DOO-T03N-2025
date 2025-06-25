import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class DayRequestModel {

    private BigDecimal tempmax;
    private BigDecimal tempmin;
    private BigDecimal temp;
    private BigDecimal humidity;
    private BigDecimal precip;
    private BigDecimal windspeed;
    private BigDecimal winddir;
    private String conditions;

    public DayRequestModel() {

    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public BigDecimal getWinddir() {
        return winddir;
    }

    public void setWinddir(BigDecimal winddir) {
        this.winddir = winddir;
    }

    public BigDecimal getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(BigDecimal windspeed) {
        this.windspeed = windspeed;
    }

    public BigDecimal getPrecip() {
        return precip;
    }

    public void setPrecip(BigDecimal precip) {
        this.precip = precip;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public BigDecimal getTempmin() {
        return tempmin;
    }

    public void setTempmin(BigDecimal tempmin) {
        this.tempmin = tempmin;
    }

    public BigDecimal getTempmax() {
        return tempmax;
    }

    public void setTempmax(BigDecimal tempmax) {
        this.tempmax = tempmax;
    }
}
