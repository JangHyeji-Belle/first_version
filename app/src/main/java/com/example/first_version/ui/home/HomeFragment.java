package com.example.first_version.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.first_version.R;

public class HomeFragment extends Fragment {

    // Context로 다음 액티비티에서 정보 사용
    public static Context DayContext;

    RadioButton rdoCal, rdoTime;
    DatePicker dPicker, tPicker;
    TextView tvYear1, tvMonth1, tvDay1, tvYear2, tvMonth2, tvDay2;
    Button btnDayOk;
    Button btnToTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.country_names, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        // 컨텍스트 설정
        DayContext = getContext();

        // 라디오버튼 2개
        rdoCal = view.findViewById(R.id.rdoCal);
        rdoTime = view.findViewById(R.id.rdoTime);

        // FrameLayout의 2개 위젯
        dPicker = view.findViewById(R.id.datePicker1);
        tPicker = view.findViewById(R.id.datePicker2);

        // 텍스트뷰 중에서 연,월,일,시,분 숫자
        tvYear1 = view.findViewById(R.id.tvYear1);
        tvMonth1 = view.findViewById(R.id.tvMonth1);
        tvDay1 = view.findViewById(R.id.tvDay1);
        tvYear2 = view.findViewById(R.id.tvYear2);
        tvMonth2 = view.findViewById(R.id.tvMonth2);
        tvDay2 = view.findViewById(R.id.tvDay2);

        tPicker.setVisibility(View.INVISIBLE);
        dPicker.setVisibility(View.INVISIBLE);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                dPicker.setVisibility(View.VISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE);
                dPicker.setVisibility(View.INVISIBLE);
            }
        });

        btnDayOk = view.findViewById(R.id.BtnDayOk);
        btnDayOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYear1.setText(Integer.toString(dPicker.getYear()));
                tvMonth1.setText(Integer.toString(1 + dPicker.getMonth()));
                tvDay1.setText(Integer.toString(dPicker.getDayOfMonth()));

                tvYear2.setText(Integer.toString(tPicker.getYear()));
                tvMonth2.setText(Integer.toString(1 + tPicker.getMonth()));
                tvDay2.setText(Integer.toString(tPicker.getDayOfMonth()));
            }
        });

        btnToTable = view.findViewById(R.id.BtnToPeople);
        btnToTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ChecklistFragment checklistFragment = new ChecklistFragment();
                    if (getFragmentManager() != null) {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_checklist, checklistFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}
