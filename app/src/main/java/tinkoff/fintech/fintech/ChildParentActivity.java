package tinkoff.fintech.fintech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_parent);

        Intent intent = getIntent();

        final String id = intent.getStringExtra("ID");
        TextView textView = findViewById(R.id.node_value);
        textView.setText(id);

    }
}
