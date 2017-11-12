package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import java.util.List;

import tinkoff.fintech.fintech.AppDatabase;

public class GetParentsTask extends AsyncTask<Void, Void, List<Integer>> {

    private AppDatabase db;
    private int id;

    public GetParentsTask(AppDatabase appDatabase, int id) {
        db = appDatabase;
        this.id = id;
    }

    @Override
    protected List<Integer> doInBackground(Void... voids) {
        List<Integer> node = db.nodeWithClildrenDao().getParents(id);
        return node;
    }

}