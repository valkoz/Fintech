package tinkoff.fintech.fintech;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DigitFragment extends Fragment {

    private FragmentListener fragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_digit, container, false);
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
    public void  onViewCreated(final View view, Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.digit_id);

                if (editText != null) {
                    Double message = Double.valueOf(editText.getText().toString());
                    if (getTag().equals("first_digit")) {
                        fragmentListener.setFirstDigit(message);
                        fragmentListener.startSecondDigitFragment();
                    }
                    else {
                        fragmentListener.setSecondDigit(message);
                        fragmentListener.startSignFragment();
                    }

                }
            }
        });
    }

}
