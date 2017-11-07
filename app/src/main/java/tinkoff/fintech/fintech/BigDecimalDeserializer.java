package tinkoff.fintech.fintech;

import android.support.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.math.BigDecimal;


public class BigDecimalDeserializer implements JsonDeserializer<BigDecimal> {
    @Nullable
    public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BigDecimal decimal = null;
        if (json.isJsonObject()) {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(" {\"money_amount\":\"2444,88\"}").getAsJsonObject();
            String s = obj.get("money_amount").getAsString().replace(",", ".");
            decimal = BigDecimal.valueOf(Double.valueOf(s));
        }
        return decimal;
    }
}
