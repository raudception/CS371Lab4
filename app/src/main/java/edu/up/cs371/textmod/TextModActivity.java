package edu.up.cs371.textmod;

/**
 * class TextModActivity
 *
 * Allow text to be modified in simple ways with button-presses.
 */
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Button;
import java.util.ArrayList;


import static edu.up.cs371.textmod.R.array.spinner_names;

public class TextModActivity extends ActionBarActivity {

    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image
    private Button button2;



    private Button clear;
    private EditText clearT ;
    private Button reverseButton;
    private Button lowerbutton;
    private Button kollinButton;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        clear = (Button)findViewById(R.id.ClearButton);
        clearT = (EditText)findViewById(R.id.editText);

        kollinButton = (Button) findViewById(R.id.kollinButton);

        kollinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearT.setText("Why would you leave me?");
            }
        });

        if(clear == null)
            Log.i("eror","null clear button ref");
        else
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearT.setText("");
            }
        });

        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mod);



        // set instance variables for our widgets
        imageView = (ImageView)findViewById(R.id.imageView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);
        button2 = (Button) findViewById(R.id.button6);
        lowerbutton = (Button) findViewById(R.id.button7);

        reverseButton = (Button) findViewById(R.id.button4);
        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storage = "SGOD 2 sah nhoJ";
                clearT.setText(storage);

            }
        });
        lowerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storage = clearT.getText().toString();
                storage = storage.toLowerCase();
                clearT.setText(storage);
            }
        });
        clearT = (EditText) findViewById(R.id.editText);
        button2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String string = clearT.getText().toString();
                clearT.setText(string.toUpperCase());
            }
        });


        // Set up the spinner so that it shows the names in the spinner array resources
        //
        // get spinner object
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        // get array of strings
        final String[] spinnerNames = getResources().getStringArray(spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i, 0);
            if (id == 0) id = imageIds2.getResourceId(0, 0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);
        }



        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                editText.setText(spinner.getSelectedItem().toString());
                imageView.setImageBitmap(images.get(position));
            }
        });



    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_mod, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }
}
