package Aula12.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Clima {
    public int queryCost;
    public double latitude;
    public double longitude;
    public String resolvedAddress;
    public String address;
    public String timezone;
    public double tzoffset;
    public String description;
    public List<Dia> days;
}