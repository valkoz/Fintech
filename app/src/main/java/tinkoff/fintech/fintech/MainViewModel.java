package tinkoff.fintech.fintech;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import tinkoff.fintech.fintech.Async.AddNodeTask;
import tinkoff.fintech.fintech.Async.GetNodesTask;
import tinkoff.fintech.fintech.Entity.Node;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;
    private MutableLiveData<List<Node>> nodes;

    public MainViewModel(Application application) {
        super(application);
    }

    public void setDatabase(AppDatabase database) {
        db = database;
    }

    public LiveData<List<Node>> getNodes() {
        if (nodes == null) {
            nodes = new MutableLiveData<List<Node>>();
            try {
                loadNodes();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    public void addNodes(Node... node) {
        addNode(node);
    }

    private void loadNodes() throws ExecutionException, InterruptedException {
        GetNodesTask getNodesTask = new GetNodesTask(db);
        nodes.setValue(getNodesTask.execute().get());
    }

    private void addNode(Node... node) {
        AddNodeTask addNodeTask = new AddNodeTask(db);
        addNodeTask.execute(node);
    }


}
