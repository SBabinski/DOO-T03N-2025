import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class UtilCurrency {

    public static DecimalFormat getBrlFormat(){
        Locale localeBR = new Locale.Builder().setLanguage("pt").setRegion("BR").build();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(localeBR);
        symbols.setCurrencySymbol("R$");

        return new DecimalFormat("¤#,##0.00", symbols);
    }
}
