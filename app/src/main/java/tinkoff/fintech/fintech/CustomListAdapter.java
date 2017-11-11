package tinkoff.fintech.fintech;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import tinkoff.fintech.fintech.Entity.Node;

public class CustomListAdapter extends ArrayAdapter<Node> {

    private final Activity context;
    private List<Node> nodes;
    private MainViewModel model;

    public CustomListAdapter(Activity context, List<Node> list, MainViewModel model) {
        super(context, R.layout.list_item, list);
        this.context = context;
        this.nodes = list;
        this.model = model;
    }

    @NonNull
    @Override
    public View getView(int position, final View convertView, @NonNull ViewGroup parent) {

        View viewItem = null;
        if (convertView == null) {

            final LayoutInflater inflator = context.getLayoutInflater();
            viewItem = inflator.inflate(R.layout.list_item, parent, false);
        } else {
            viewItem = convertView;
        }

        Integer id = nodes.get(position).getValue();
        TextView tv = viewItem.findViewById(R.id.label);
        tv.setText(String.valueOf(id));

        if (model.hasChildren(id) && model.hasParents(id))
            viewItem.setBackgroundColor(Color.RED);
        else if (model.hasChildren(id))
            viewItem.setBackgroundColor(Color.YELLOW);
        else if (model.hasParents(id))
            viewItem.setBackgroundColor(Color.BLUE);

        return viewItem;
    }

}

