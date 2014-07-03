package us.platyp.rescalc;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


public class ResistorFrag extends Fragment implements AdapterView.OnItemSelectedListener{

	int numBands;
	TextView finalView, subtotalView;
	Spinner sig_one, sig_two, sig_three, multiplier, tolerance, temperature;

    public ResistorFrag() {
        // Required empty public constructor
    	
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        numBands = getArguments().getInt("NUM_BANDS");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
    	//Inflate proper layout
    	if (numBands == 5) {
    		return inflater.inflate(R.layout.fragment_five, container, false);
    	} else if (numBands == 6) {
    		return inflater.inflate(R.layout.fragment_six, container, false);
    	} else {
    		return inflater.inflate(R.layout.fragment_four, container, false);
    	}

    }


    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onCreate(bundle);
        
        AllColorAdapter allColorsAdapter = 
        		new AllColorAdapter(getActivity(),R.layout.layout_spinner,getActivity().getResources().getStringArray(R.array.base_color_array));
        
        //Set up significant digit spinners
        sig_one = (Spinner) getActivity().findViewById(R.id.sig1_band);
        sig_one.setAdapter(allColorsAdapter); 
        sig_one.setOnItemSelectedListener(this);

        sig_two = (Spinner) getActivity().findViewById(R.id.sig2_band);
        sig_two.setAdapter(allColorsAdapter); 
        sig_two.setOnItemSelectedListener(this);
        
        //Five and six bands have an extra significant digit
        if (numBands == 5 || numBands == 6) {
            sig_three = (Spinner) getActivity().findViewById(R.id.sig3_band);
            sig_three.setAdapter(allColorsAdapter);
            sig_three.setOnItemSelectedListener(this);
        }
        

        //Multiplier spinner
        AllColorAdapter multiAdapter = 
        		new AllColorAdapter(getActivity(),R.layout.layout_spinner,getActivity().getResources().getStringArray(R.array.multi_color_array));
        
        multiplier = (Spinner) getActivity().findViewById(R.id.multiplier_band);
        multiplier.setAdapter(multiAdapter);
        multiplier.setOnItemSelectedListener(this);
        
        //Tolerance spinner
        AllColorAdapter tolAdapter = 
        		new AllColorAdapter(getActivity(),R.layout.layout_spinner,getActivity().getResources().getStringArray(R.array.tolerance_color_array));
        
        tolerance = (Spinner) getActivity().findViewById(R.id.tolerance_band);
        tolerance.setAdapter(tolAdapter);
        tolerance.setOnItemSelectedListener(this);
        
        //Temperature spinner for six bands
        if (numBands == 6) {
        	AllColorAdapter tempAdapter = 
        			new AllColorAdapter(getActivity(),R.layout.layout_spinner,getActivity().getResources().getStringArray(R.array.temperature_color_array));
            
            temperature = (Spinner) getActivity().findViewById(R.id.tempco_band);
            temperature.setAdapter(tempAdapter);
            temperature.setOnItemSelectedListener(this);
        }
        
        //Initialize text views
        finalView = (TextView) getActivity().findViewById(R.id.final_display);
        subtotalView = (TextView) getActivity().findViewById(R.id.subtotal_display);
    }
    
    void CalculateResistor() {
    	
    	double subtotal = 0;
    	String result, subresult;
    	
    	
    	//Calculate value and tolerance if applicable
    	if (numBands == 4) {
    		subtotal = (sig_one.getSelectedItemPosition() * Math.pow(10.0, (double) multiplier.getSelectedItemPosition()+1 )) +
    				(sig_two.getSelectedItemPosition() * Math.pow(10.0, (double) multiplier.getSelectedItemPosition() )); 
    	} else {
    		subtotal = (sig_one.getSelectedItemPosition() * Math.pow(10.0, (double) multiplier.getSelectedItemPosition()+2 )) +
    				(sig_two.getSelectedItemPosition() * Math.pow(10.0, (double) multiplier.getSelectedItemPosition()+1 )) +
    				(sig_three.getSelectedItemPosition() * Math.pow(10.0, (double) multiplier.getSelectedItemPosition() )); 
    	}
    	
    	double[] tolArray = {0.20, 0.01, 0.02, 0.05, 0.005, 0.0025, 0.001, 0.0005, 0.05, 0.1};
    	
    	subresult = String.valueOf((int)subtotal) + " \u03A9" + "\t" + String.valueOf( (int)(100*tolArray[tolerance.getSelectedItemPosition()]) ) + "%";
    	
    	result = String.valueOf( (int) (subtotal - (tolArray[tolerance.getSelectedItemPosition()] * subtotal)) ) + " - " +
    			String.valueOf( (int) (subtotal * (1+tolArray[tolerance.getSelectedItemPosition()])) ) + " \u03A9";
    	
    	//Set temperature coefficient
    	if (numBands == 6) {
    		int[] tempCo = {250,100,50,15,25,20,10,5,1};
    		result += " " + tempCo[temperature.getSelectedItemPosition()] + " ppm";
    	}
    	
    	subtotalView.setText(subresult);
    	finalView.setText(result);
    }
    
    public void UpdateColors() {
    	View sig1_block, sig2_block, multi_block, tol_block;
    	
    }
    
    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int pos, long id) {
    	CalculateResistor();
    	UpdateColors();
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
 
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}