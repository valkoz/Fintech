package tinkoff.fintech.fintech;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import tinkoff.fintech.fintech.Async.GetNodeById;
import tinkoff.fintech.fintech.Entity.Node;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.setDatabase(db);
        model.addNodes(new Node(2));
        model.addNodes(new Node(3), new Node(5));

        List<Node> nodes = model.getNodes().getValue();

        if (nodes != null) {
            Log.i("getAll:", model.getNodes().getValue().toString());
        }
        getByIdTest(db);

       /* model.getNodes().observe(this, nodes -> {
            // update UI
        });*/


    }

    private void getByIdTest(AppDatabase db) {
        GetNodeById getNodeById = new GetNodeById(db, 2);
        try {
            Log.i("getById", getNodeById.execute().get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        GetNodeById getNodeById2 = new GetNodeById(db, 4);
        try {
            Node value = getNodeById2.execute().get();
            if (value != null) {
                Log.i("getById", value.toString());
            } else {
                Log.i("Element not found", "NULL");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
