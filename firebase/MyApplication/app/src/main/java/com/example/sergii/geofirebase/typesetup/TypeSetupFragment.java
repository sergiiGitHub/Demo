package com.example.sergii.geofirebase.typesetup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sergii.geofirebase.R;

import java.util.List;

/**
 * Created by sergii on 09.07.17.
 */

public class TypeSetupFragment extends Fragment {

    private List<String> spinnerItems;
    private AdapterView.OnItemSelectedListener onItemSelectedListener;
    private View.OnClickListener onItemClickListener;
    private EditText emailAdress;
    private String email;

    public void setSpinnerList(List<String> list){
        this.spinnerItems = list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.type_setup, container, false);
        populateView(view);
        return view;
    }

    private void populateView(View view) {
        populateSpinner(view);
        populateButton(view);
        populateEmailAddres(view);
    }

    private void populateEmailAddres(View view) {
        emailAdress = view.findViewById(R.id.email_address);
        emailAdress.setText(email);
    }

    private void populateButton(View view) {
        Button button = view.findViewById(R.id.type_setup_done);
        button.setOnClickListener(onItemClickListener);
    }

    private void populateSpinner(View view) {
        Spinner spinner = view.findViewById(R.id.type_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.app_type, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerItems);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    public AdapterView.OnItemSelectedListener getOnItemSelectedListener() {
        return onItemSelectedListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public String getEmail() {
        return emailAdress.getText().toString();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
