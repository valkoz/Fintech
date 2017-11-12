package tinkoff.fintech.fintech;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ChildListAdapter extends ArrayAdapter<Integer> {

    private final Activity context;
    private List<Integer> nodes;
    private ChildrenViewModel model;

    public ChildListAdapter(Activity context, List<Integer> list, ChildrenViewModel model) {
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

        Integer id = nodes.get(position).intValue();
        TextView tv = viewItem.findViewById(R.id.label);
        tv.setText(String.valueOf(id));

        viewItem.setBackgroundColor(Color.GREEN);

        return viewItem;
    }

}