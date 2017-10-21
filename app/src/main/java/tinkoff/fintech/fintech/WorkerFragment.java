package tinkoff.fintech.fintech;

import android.app.Fragment;
import android.os.Bundle;

public class WorkerFragment extends Fragment {

    private String[] data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }
}
