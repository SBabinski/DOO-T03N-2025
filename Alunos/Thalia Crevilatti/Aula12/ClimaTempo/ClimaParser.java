import com.google.gson.*;

public class ClimaParser {
    public static ClimaDia parse(String json) {
        JsonObject raiz = JsonParser.parseString(json).getAsJsonObject();

        JsonObject current = raiz.getAsJsonObject("currentConditions");
        JsonObject hoje = raiz.getAsJsonArray("days").get(0).getAsJsonObject();

        return new ClimaDia(
                hoje.get("datetime").getAsString(),
                current.get("temp").getAsDouble(),
                hoje.get("tempmin").getAsDouble(),
                hoje.get("tempmax").getAsDouble(),
                current.get("humidity").getAsInt(),
                current.get("conditions").getAsString(),
                current.has("precip") ? current.get("precip").getAsDouble() : 0.0,
                current.get("windspeed").getAsDouble(),
                current.get("winddir").getAsDouble()
        );
    }
}