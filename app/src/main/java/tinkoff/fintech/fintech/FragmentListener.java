package tinkoff.fintech.fintech;

public interface FragmentListener {
    void setFirstDigit(Double msg);
    void setSecondDigit(Double msg);
    void setSign(SignEnum msg);

    void startFirstDigitFragment();

    void startSecondDigitFragment();

    void startSignFragment();

    void startResultFragment();
}
