package tinkoff.fintech.fintech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getName().toLowerCase().contains("name");
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .registerTypeAdapter(BigDecimal.class, new BigDecimalDeserializer())
                .setDateFormat("yyyy-MM-dd")
                .create();


        DummyClass c = gson.fromJson("{\"name\":\"name\",\"value\":\"111\"}", DummyClass.class);
        Log.i("Task1", String.valueOf(c.getValue()) + String.valueOf(c.getName()));

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse("{\"name\":\"name\",\"any_map\":{\"a\":\"55\",\"b\":\"85\",\"c\":\"56\"}}")
                .getAsJsonObject()
                .get("any_map")
                .getAsJsonObject();
        HashMap h = gson.fromJson(obj, HashMap.class);
        Log.i("Task2", h.toString());
        Log.i("Task2", h.getClass().getCanonicalName());

        BigDecimal decimal = gson.fromJson("{\"money_amount\":\"2444,88\"}", BigDecimal.class);
        Log.i("Task3", String.valueOf(decimal));

        DateExample dateExample = new DateExample(new Date());
        Log.i("Task4", gson.toJson(dateExample));

    }
}
