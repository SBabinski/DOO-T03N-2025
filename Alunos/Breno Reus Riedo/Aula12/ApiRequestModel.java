import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class ApiRequestModel {

    private String resolvedAddress;
    private List<DayRequestModel> days;

    public ApiRequestModel() {

    }

    public String getResolvedAddress() {
        return resolvedAddress;
    }

    public void setResolvedAddress(String resolvedAddress) {
        this.resolvedAddress = resolvedAddress;
    }

    public List<DayRequestModel> getDays() {
        return days;
    }

    public void setDays(List<DayRequestModel> days) {
        this.days = days;
    }

}
