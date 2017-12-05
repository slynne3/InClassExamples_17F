package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends Activity {
    boolean isTablet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //test if this is a phone or tablet
         isTablet = findViewById(R.id.frame) != null;
        String s = "Phone";
        final DetailFragment df = new DetailFragment();

        final Bundle info = new Bundle();
        info.putString("Key", "Value");
        Button fButton = (Button)findViewById(R.id.fragmentbutton);


        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTablet)
                {

                    df.setArguments(info);
                    //start Transaction to insert fragment in screen:
                 FragmentTransaction ft =  getFragmentManager().beginTransaction();
                 ft.add(R.id.frame, df);
                ft.addToBackStack("A string name");
                 ft.commit();
                }
                else
                {
                    Intent phoneIntent = new Intent(FragmentActivity.this, EmptyFragmentActivity.class);
                    phoneIntent.putExtras(info);
                    startActivity(phoneIntent);
                }
            }
        });


        Button removeButton = (Button)findViewById(R.id.removefragment);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTablet)
                {
                    //start Transaction to insert fragment in screen:
                    FragmentTransaction ft =  getFragmentManager().beginTransaction();
                    ft.remove(df);
                    ft.commit();
                }
                else
                {

                }
            }
        });
        if(isTablet)
            s = "Tablet";

        Log.i("IsTablet?" , s);
    }
}
