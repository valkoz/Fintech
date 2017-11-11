package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import tinkoff.fintech.fintech.AppDatabase;

public class GetParentsTask extends AsyncTask<Void, Void, Integer[]> {

    private AppDatabase db;
    private int id;

    public GetParentsTask(AppDatabase appDatabase, int id) {
        db = appDatabase;
        this.id = id;
    }

    @Override
    protected Integer[] doInBackground(Void... voids) {
        Integer[] node = db.nodeWithClildrenDao().getParents(id);
        return node;
    }

}