package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;


import tinkoff.fintech.fintech.AppDatabase;
import tinkoff.fintech.fintech.Entity.Node;

public class GetNodeById extends AsyncTask<Void, Void, Node> {

    private AppDatabase db;
    private int id;

    public GetNodeById(AppDatabase appDatabase, int id) {
        db = appDatabase;
        this.id = id;
    }

    @Override
    protected Node doInBackground(Void... voids) {
        Node node = db.nodeDao().getByKey(id);
        return node;
    }

    @Override
    protected void onPostExecute(Node node) {
        super.onPostExecute(node);
    }

}
