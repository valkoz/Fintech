package tinkoff.fintech.fintech;

public enum SignEnum {
    ADD,
    SUB,
    MUL,
    DIV;

    @Override
    public String toString() {
        switch(this) {
            case ADD: return "+";
            case SUB: return "-";
            case MUL: return "*";
            case DIV: return ":";
            default: throw new IllegalArgumentException();
        }
    }
}
