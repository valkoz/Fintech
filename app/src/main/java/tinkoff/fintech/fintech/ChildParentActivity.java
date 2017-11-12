package tinkoff.fintech.fintech;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tinkoff.fintech.fintech.Entity.Node;
import tinkoff.fintech.fintech.Entity.NodeWithChildren;

public class ChildParentActivity extends AppCompatActivity {

    private ChildrenViewModel model;

    private int id;
    ListView parentsList;
    ListView childrenList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_parent);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        initViews();

        model = ViewModelProviders.of(this).get(ChildrenViewModel.class);
        model.setDatabase(db);

        addChildren(2, 5);
        addChildren(2, 4);
        addChildren(4, 5);

        model.getChildren(id).observe(this, nodes -> {
            ArrayAdapter<Integer> adapter = new ChildListAdapter(this, nodes, model);
            childrenList.setAdapter(adapter);
        });

        model.getParents(id).observe(this, nodes -> {
            ArrayAdapter<Integer> adapter = new ChildListAdapter(this, nodes, model);
            parentsList.setAdapter(adapter);
        });

    }

    private void addChildren(int node, int child) {
        model.addRelation(new NodeWithChildren(node, child));
    }

    private void initViews() {
        Intent intent = getIntent();

        final String id = intent.getStringExtra("ID");
        TextView textView = findViewById(R.id.node_value);
        textView.setText(id);
        this.id = Integer.valueOf(id);
        parentsList = findViewById(R.id.parents_list);
        childrenList = findViewById(R.id.children_list);
    }


}
