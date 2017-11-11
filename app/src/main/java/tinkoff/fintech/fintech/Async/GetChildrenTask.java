package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import java.util.List;

import tinkoff.fintech.fintech.AppDatabase;
import tinkoff.fintech.fintech.Entity.Node;

public class GetChildrenTask extends AsyncTask<Void, Void, Integer[]> {

    private AppDatabase db;
    private int id;

    public GetChildrenTask(AppDatabase appDatabase, int id) {
        db = appDatabase;
        this.id = id;
    }

    @Override
    protected Integer[] doInBackground(Void... voids) {
        Integer[] node = db.nodeWithClildrenDao().getChildren(id);
        return node;
    }

}