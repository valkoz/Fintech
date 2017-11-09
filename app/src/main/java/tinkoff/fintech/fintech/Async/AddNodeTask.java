package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import tinkoff.fintech.fintech.AppDatabase;
import tinkoff.fintech.fintech.Entity.Node;

public class AddNodeTask extends AsyncTask<Node, Void, Void> {

    private AppDatabase db;

    public AddNodeTask(AppDatabase appDatabase) {
        db = appDatabase;
    }

    @Override
    protected Void doInBackground(Node... nodes) {
        db.nodeDao().insertAll(nodes);
        return null;

    }
}
