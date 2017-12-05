package com.example.etorunski.inclassexamples_17f;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NavigationExample extends AppCompatActivity {
String responseText = "You pressed yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.my_navigation_menu, menu);
	    return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_one:
                //show a Toast
                Toast t = Toast.makeText(this, "Action one", Toast.LENGTH_LONG);
                t.show();
                break;
            case R.id.action_two:
                //launch another Activity

                LayoutInflater li= getLayoutInflater();
                LinearLayout rootTag = (LinearLayout)li.inflate(R.layout.dialog_middle_layout, null);
                final EditText et = (EditText)rootTag.findViewById(R.id.enter_field);
                Button clickButton = (Button)rootTag.findViewById(R.id.enter_button);
                clickButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        responseText = et.getText().toString();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Alert pressed")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Accept
                                Toast t = Toast.makeText(NavigationExample.this, responseText,  Toast.LENGTH_LONG);
                                t.show();
                            }
                        })
                        .setView(rootTag)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                        // What to do on Cancel
                                Toast t = Toast.makeText(NavigationExample.this, "Pressed Cancel", Toast.LENGTH_LONG);
                                t.show();
                            }
                        });
                builder.create().show();

                break;
            case R.id.action_three:
                break;
            case R.id.action_four:
                break;
        }
        return true;
    }


}
