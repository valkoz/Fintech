package tinkoff.fintech.fintech;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements FragmentListener{

    private Double firstDigit;
    private Double secondDigit;
    private SignEnum sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button firstDigitButton = findViewById(R.id.first_digit_button);
        final Button secondDigitButton = findViewById(R.id.second_digit_button);
        final Button signButton = findViewById(R.id.sign_button);
        final Button resultButton = findViewById(R.id.result_button);

        startFirstDigitFragment();

        firstDigitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("onClick", "first_digit_button");
                startFirstDigitFragment();
            }
        });

        secondDigitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("onClick", "second_digit_button");
                startSecondDigitFragment();
            }
        });


        signButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("onClick", "sign_button");
                startSignFragment();
            }
        });


        resultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startResultFragment();
            }
        });
    }

    @Override
    public void setFirstDigit(Double message) {
        firstDigit = message;
    }

    @Override
    public void setSecondDigit(Double message) {
        secondDigit = message;
    }

    @Override
    public void setSign(SignEnum message) {
        sign = message;
    }

    @Override
    public void startFirstDigitFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, new DigitFragment(), "first_digit")
                .commit();
    }

    @Override
    public void startSecondDigitFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, new DigitFragment(), "second_digit")
                .commit();
    }

    @Override
    public void startSignFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, new SignFragment(), "sign")
                .commit();
    }

    @Override
    public void startResultFragment() {
        if (firstDigit != null && secondDigit != null && sign != null) {
            Bundle bundle = new Bundle();
            bundle.putDouble("first", firstDigit);
            bundle.putDouble("second", secondDigit);
            bundle.putInt("sign", sign.ordinal());

            Fragment fragment = new ResultFragment();
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_content, fragment, "result")
                    .commit();
        } else {
            if (firstDigit == null) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Введите первое число!", Toast.LENGTH_SHORT);
                toast.show();
            }
            if (secondDigit == null) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Введите второе число!", Toast.LENGTH_SHORT);
                toast.show();
            }
            if (sign == null) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Введите знак!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
