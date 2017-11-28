package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by torunse on 11/28/2017.
 */

public class DetailFragment extends Fragment {
       private Activity parent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle passedInfo = getArguments();

    String s = "none";
    if(passedInfo != null)
        s= passedInfo.getString("Key");
    Log.i("Passed key", s);

        parent = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, null);
Button b = (Button)v.findViewById(R.id.fragmentbutton);
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        parent.getFragmentManager().beginTransaction().remove(DetailFragment.this).commit();
    }
});
        return v;
    }
}
