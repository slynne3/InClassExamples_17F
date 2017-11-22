package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncActivity extends Activity {
    Button aButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
         aButton = (Button)findViewById(R.id.asyncbutton);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask aTask = new MyAsyncTask();
              /*  try {
                    InputStream iStream;
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    iStream = urlConnection.getInputStream();*/
                     aTask.execute("http://torunski.ca/CST2335_XML.xml");
          /*      }
                catch (Throwable t)
                {
Log.i("Error", t.getMessage());
                }*/
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<String, Float, String>
    {
        //do work here
        public String doInBackground(String ...args)
        {
            InputStream iStream;
            float i = 0;
            while(i < args.length) {
                try {
                    URL url = new URL(args[(int)i]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    iStream = urlConnection.getInputStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput( iStream  , "UTF-8");

                    while(xpp.getEventType() != XmlPullParser.END_DOCUMENT)
                    {
                        switch(xpp.getEventType())
                        {
                            case XmlPullParser.START_TAG:
                                String  parameter = xpp.getAttributeValue(null, "message");
                                Log.i("Opening tag", xpp.getName() + " message:" + parameter);
                                break;
                            case XmlPullParser.END_TAG:
                                Log.i("Closing tag", xpp.getName());
                                break;
                            case XmlPullParser.TEXT:

                                Log.i("text tag",xpp.getText());
                                break;

                        }
                        xpp.next();
                    }

                }
                catch (XmlPullParserException xppe)
                {
                    Log.e("ERROR", "ParserException");

                }
                catch (MalformedURLException mue)
                {
                    Log.e("ERROR", "MalformedURLException");
                }
                catch(IOException ioe){
                    Log.e("ERROR", "IOException");
                }
                publishProgress(new Float(i++ / args.length));
            }
            return "Go to OnPostExecute";
        }

        //called from GUI
        public void onProgressUpdate(Float ...f )
        {
            aButton.setText("Progress:" + f.toString());
        }

        //last chance to put your GUI back to normal, the work is done
        public void onPostExecute(String s )
        {
            aButton.setText(AsyncActivity.this.getString(R.string.asyncbutton));
        }
    }
}
