package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import java.util.List;

import tinkoff.fintech.fintech.AppDatabase;
import tinkoff.fintech.fintech.Entity.Node;


public class GetNodesTask extends AsyncTask<Void, Void, List<Node>> {

    private AppDatabase db;

    public GetNodesTask(AppDatabase appDatabase) {
        db = appDatabase;
    }

    @Override
    protected List<Node> doInBackground(Void... voids) {
        List<Node> nodes = db.nodeDao().getAll();
        return nodes;
    }

    @Override
    protected void onPostExecute(List<Node> nodes) {
        super.onPostExecute(nodes);
    }
}
