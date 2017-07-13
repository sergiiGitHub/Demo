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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.sergii.geofirebase.R;

import java.util.List;

/**
 * Created by sergii on 09.07.17.
 */

public class TypeSetupFragment extends Fragment {

    private View.OnClickListener onItemClickListener;
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener;
    private TypeSetupController.Type currentType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.type_setup, container, false);
        populateView(view);
        return view;
    }

    private void populateView(View view) {
        populateRadio(view);
        populateButton(view);
    }

    private void populateRadio(View view) {
        RadioGroup radioGroup = view.findViewById(R.id.setup_group);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        if(currentType == TypeSetupController.Type.NON || currentType == TypeSetupController.Type.WATCHER){
            RadioButton radioButton = view.findViewById(R.id.setup_watcher);
            radioButton.setChecked(true);
        } else {
            RadioButton radioButton = view.findViewById(R.id.setup_track);
            radioButton.setChecked(true);
        }
    }

    private void populateButton(View view) {
        Button button = view.findViewById(R.id.type_setup_done);
        button.setOnClickListener(onItemClickListener);
    }

    public RadioGroup.OnCheckedChangeListener getOnItemSelectedListener() {
        return onCheckedChangeListener;
    }

    public void setOnItemSelectedListener(RadioGroup.OnCheckedChangeListener onItemSelectedListener) {
        this.onCheckedChangeListener = onItemSelectedListener;
    }

    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setType(TypeSetupController.Type currentType) {
        this.currentType = currentType;
    }
}
