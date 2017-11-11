package tinkoff.fintech.fintech;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import tinkoff.fintech.fintech.Async.DeleteAllTask;
import tinkoff.fintech.fintech.Async.GetNodeById;
import tinkoff.fintech.fintech.Entity.Node;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private Button button;
    private EditText ed;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().build();

        model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.setDatabase(db);

        lv = findViewById(R.id.list_view);
        button = findViewById(R.id.add_button);
        ed = findViewById(R.id.edit_node);

        button.setOnClickListener(view -> {
            Node node = new Node(Integer.valueOf(String.valueOf(ed.getText())));
            model.addNodes(node);
        });

        model.getNodes().observe(this, nodes -> {
            Log.i("Observer", nodes.toString());
            ArrayAdapter<Node> adapter = new CustomListAdapter(this, nodes, model);
            lv.setAdapter(adapter);
        });

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(view.getContext(), ChildParentActivity.class);
            String s = lv.getItemAtPosition(i).toString();
            intent.putExtra("ID", s);
            startActivity(intent);
            Log.i(String.valueOf(i), lv.getItemAtPosition(i).toString());
        });

    }

    private void deleteAll(AppDatabase db) {
        new DeleteAllTask(db).execute();
    }

    private void addValues() {
        model.addNodes(new Node(2));
        model.addNodes(new Node(36), new Node(51));
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
