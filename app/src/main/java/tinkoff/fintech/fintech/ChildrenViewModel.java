package tinkoff.fintech.fintech;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tinkoff.fintech.fintech.Async.AddChildrenTask;
import tinkoff.fintech.fintech.Async.GetChildrenTask;
import tinkoff.fintech.fintech.Async.GetParentsTask;
import tinkoff.fintech.fintech.Entity.Node;
import tinkoff.fintech.fintech.Entity.NodeWithChildren;


public class ChildrenViewModel extends ViewModel {
    private AppDatabase db;
    private MutableLiveData<Integer[]> children;
    private MutableLiveData<Integer[]> parents;

    public void setDatabase(AppDatabase database) {
        db = database;
    }

    public List<NodeWithChildren> getNodesWithChildren() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<NodeWithChildren>>() {
            @Override
            protected List<NodeWithChildren> doInBackground(Void... voids) {
                return db.nodeWithClildrenDao().getNodesWithChildren();
            }
        }.execute().get();
    }

    public void addRelation(NodeWithChildren... withChildren) {
        AddChildrenTask addNodeTask = new AddChildrenTask(db);
        addNodeTask.execute(withChildren);
    }

    public LiveData<Integer[]> getChildren(int id) {
        GetChildrenTask getChildrenTask = new GetChildrenTask(db, id);

        if (children == null)
            children = new MutableLiveData<Integer[]>();
        try {
            children.postValue(getChildrenTask.execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return children;
    }

    public LiveData<Integer[]> getParents(int id) {
        GetParentsTask getParentsTask = new GetParentsTask(db, id);

        if (parents == null)
            parents = new MutableLiveData<Integer[]>();
        try {
            parents.postValue(getParentsTask.execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return parents;
    }
}
