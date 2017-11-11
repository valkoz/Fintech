package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import tinkoff.fintech.fintech.AppDatabase;


public class DeleteAllTask extends AsyncTask<Void, Void, Void> {

    private AppDatabase db;

    public DeleteAllTask(AppDatabase appDatabase) {
        db = appDatabase;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        db.nodeDao().deleteAll();
        return null;
    }
}