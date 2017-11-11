package tinkoff.fintech.fintech.Async;

import android.os.AsyncTask;

import tinkoff.fintech.fintech.AppDatabase;
import tinkoff.fintech.fintech.Entity.NodeWithChildren;


public class AddChildrenTask extends AsyncTask<NodeWithChildren, Void, Void> {

    private AppDatabase db;

    public AddChildrenTask(AppDatabase appDatabase) {
        db = appDatabase;
    }

    @Override
    protected Void doInBackground(NodeWithChildren... nodeWithChildren) {
        db.nodeWithClildrenDao().addRelation(nodeWithChildren);
        return null;
    }
}
