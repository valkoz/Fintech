package tinkoff.fintech.fintech;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SignFragment extends Fragment {

    private FragmentListener fragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof FragmentListener)
            fragmentListener = (FragmentListener) getActivity();
        else
            throw new RuntimeException("Activity should implement FragmentListener");
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        Button addButton = view.findViewById(R.id.add_id);
        addButton.setOnClickListener(addOnClickListener(SignEnum.ADD));

        Button subButton = view.findViewById(R.id.sub_id);
        subButton.setOnClickListener(addOnClickListener(SignEnum.SUB));

        Button divButton = view.findViewById(R.id.div_id);
        divButton.setOnClickListener(addOnClickListener(SignEnum.DIV));

        Button mulButton = view.findViewById(R.id.mul_id);
        mulButton.setOnClickListener(addOnClickListener(SignEnum.MUL));
    }

    private View.OnClickListener addOnClickListener(final SignEnum sign) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClick", sign.name());
                fragmentListener.setSign(sign);
                fragmentListener.startResultFragment();
            }
        };
    }

}
