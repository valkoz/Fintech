package tinkoff.fintech.fintech;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tinkoff.fintech.fintech.Async.AddNodeTask;
import tinkoff.fintech.fintech.Async.GetChildrenTask;
import tinkoff.fintech.fintech.Async.GetNodesTask;
import tinkoff.fintech.fintech.Async.GetParentsTask;
import tinkoff.fintech.fintech.Entity.Node;

public class MainViewModel extends ViewModel {
    private AppDatabase db;
    private MutableLiveData<List<Node>> nodes;

    public void setDatabase(AppDatabase database) {
        db = database;
    }

    public LiveData<List<Node>> getNodes() {
        if (nodes == null) {
            nodes = new MutableLiveData<List<Node>>();
        }
        try {
            loadNodes();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    public void addNodes(Node... node) {
        addNode(node);
        getNodes();
    }

    private void loadNodes() throws ExecutionException, InterruptedException {
        GetNodesTask getNodesTask = new GetNodesTask(db);
        nodes.postValue(getNodesTask.execute().get());
    }

    private void addNode(Node... node) {
        AddNodeTask addNodeTask = new AddNodeTask(db);
        addNodeTask.execute(node);
    }

    public Boolean hasChildren(int id) {
        GetChildrenTask getChildrenTask = new GetChildrenTask(db, id);
        try {
            List<Integer> i = getChildrenTask.execute().get();
            if (!i.isEmpty())
                return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean hasParents(int id) {
        GetParentsTask getParentsTask = new GetParentsTask(db, id);
        try {
            List<Integer> i = getParentsTask.execute().get();
            if (!i.isEmpty())
                return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
