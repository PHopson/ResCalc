package us.platyp.rescalc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ResistorActivity extends Activity implements AdapterView.OnItemSelectedListener {

    //look in the sky, it's a bird, it's a plane
    FragmentManager itsFragMan = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistor);

        //Populate spinner for choosing the number of bands
        Spinner bandSpinner = (Spinner) findViewById(R.id.num_bands);
        ArrayAdapter<CharSequence> bandAdapter =
                ArrayAdapter.createFromResource(this,R.array.numbands_array, R.layout.layout_spinner);
        

        bandAdapter.setDropDownViewResource(R.layout.layout_spinner);
        bandSpinner.setAdapter(bandAdapter);
        bandSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int pos, long id) {

    	//Pass the number of bands to the fragment
        ResistorFrag resistFrag = new ResistorFrag();
        Bundle bunDull = new Bundle();
        bunDull.putInt("NUM_BANDS", pos+4);
        resistFrag.setArguments(bunDull);
        
        FragmentTransaction numBandTrans = itsFragMan.beginTransaction();
        numBandTrans.replace(R.id.frag_container, resistFrag);
        numBandTrans.commit();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
