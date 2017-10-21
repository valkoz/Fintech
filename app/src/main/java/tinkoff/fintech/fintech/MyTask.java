package tinkoff.fintech.fintech;

import android.os.AsyncTask;
import android.util.Log;

public class MyTask extends AsyncTask<Void, Void, String[]> {

    @Override
    protected String[] doInBackground(Void... voids) {
        Log.i(getClass().getCanonicalName(), "doInBackground");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new String[] {
                "Лол", "Кек", "Чебурек"
        };
    }

}
