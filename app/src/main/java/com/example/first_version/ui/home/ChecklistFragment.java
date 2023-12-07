package com.example.first_version.ui.home;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.first_version.R; // Import your R class

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChecklistFragment extends Fragment {
    private EditText editTextTravelDate;
    private Spinner spinnerTravelLocation;
    private Spinner spinnerTravelType;
    private ListView listViewRecommendations;
    private Button buttonAddItem;
    private List<String> travelTypes;
    private boolean[] checkedTravelTypes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false); // Replace with your fragment layout file

//        editTextTravelDate = view.findViewById(R.id.editTextTravelDate);
//        spinnerTravelLocation = view.findViewById(R.id.spinnerTravelLocation);
//        spinnerTravelType = view.findViewById(R.id.spinnerTravelType);
        listViewRecommendations = view.findViewById(R.id.listViewRecommendations);
        buttonAddItem = view.findViewById(R.id.buttonAddItem);

        travelTypes = Arrays.asList("국내여행", "해외여행", "액티비티");
        checkedTravelTypes = new boolean[travelTypes.size()];

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTravelTypesDialog();
            }
        });

        return view;
    }

    private void showTravelTypesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext()); // Use requireContext() to get the context
        builder.setTitle("Choose travel types");

        builder.setMultiChoiceItems(travelTypes.toArray(new String[0]), checkedTravelTypes,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedTravelTypes[which] = isChecked;
                    }
                });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showRecommendations();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showRecommendations() {
        List<String> recommendations = new ArrayList<>();

        for (int i = 0; i < travelTypes.size(); i++) {
            if (checkedTravelTypes[i]) {
                if (travelTypes.get(i).equals("국내여행")) {
                    recommendations.addAll(Arrays.asList("여권", "지도", "현금", "핸드폰 충전기"));
                } else if (travelTypes.get(i).equals("해외여행")) {
                    recommendations.addAll(Arrays.asList("여권", "지도", "외국 화폐", "어댑터"));
                } else if (travelTypes.get(i).equals("액티비티")) {
                    recommendations.addAll(Arrays.asList("여권", "운동화", "수영복", "카메라"));
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), R.layout.list_item, recommendations) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
                CheckBox checkBox = convertView.findViewById(R.id.checkBox);
                checkBox.setText(getItem(position));

                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("your_preferences_name", requireContext().MODE_PRIVATE);
                boolean checkState = sharedPreferences.getBoolean(getItem(position), false);
                checkBox.setChecked(checkState);

                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    SharedPreferences sharedPreferences1 = requireContext().getSharedPreferences("your_preferences_name", requireContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean(getItem(position), isChecked);
                    editor.apply();

                    if (isChecked) {
                        checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        checkBox.setPaintFlags(checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                });

                return convertView;
            }
        };

        listViewRecommendations.setAdapter(adapter);
    }
}
