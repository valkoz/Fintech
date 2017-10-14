package tinkoff.fintech.fintech;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static tinkoff.fintech.fintech.SignEnum.*;

public class ResultFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void  onViewCreated(final View view, Bundle savedInstanceState){

        TextView textView = view.findViewById(R.id.result_id);
        Double first = getArguments().getDouble("first");
        Double second = getArguments().getDouble("second");
        SignEnum sign = values()[getArguments().getInt("sign")];

        Double result;
        switch (sign) {
            case ADD:
                result = first + second;
                break;
            case SUB:
                result = first - second;
                break;
            case MUL:
                result = first * second;
                break;
            case DIV:
                result = first / second;
                break;
            default:
                result = 0d;
                break;
        }

        String output = first.toString() + " " + sign.toString() + " " + second.toString() + " = " + result.toString();
        textView.setText(output);

    }

}