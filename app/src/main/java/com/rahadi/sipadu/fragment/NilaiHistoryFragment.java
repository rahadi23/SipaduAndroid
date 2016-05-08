package com.rahadi.sipadu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rahadi.sipadu.R;
import com.rahadi.sipadu.adapter.Nilai;
import com.rahadi.sipadu.adapter.StringContainer;
import com.rahadi.sipadu.gettersetter.NilaiGetsetter;

import java.util.ArrayList;


public class NilaiHistoryFragment extends Fragment {

    ArrayList<NilaiGetsetter> listItem;

    public NilaiHistoryFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nilai_history, container, false);

        setHasOptionsMenu(true);

        Bundle bundle = this.getArguments();
        String nim = bundle.getString("NIM");

        listItem = new ArrayList<>();
        NilaiGetsetter nilai;
        for (int i = 0; i < StringContainer.konten_nilai_history.length; i++){
            nilai = new NilaiGetsetter();
            nilai.setInisial(StringContainer.konten_nilai_history[i][0].substring(0, 1).toUpperCase());
            nilai.setNamamatkul(StringContainer.konten_nilai_history[i][0]);
            nilai.setNamadosen(StringContainer.konten_nilai_history[i][1]);
            nilai.setNilai(StringContainer.konten_nilai_history[i][2]);

            listItem.add(nilai);
        }

        Nilai adapter = new Nilai(getActivity(), listItem);
        // Attach the adapter to a ListView
        View v2 = v.findViewById(R.id.v_emptyHistoryNilai);
        ListView listHistoryNilai = (ListView)v.findViewById(R.id.lv_historynilai);
        TextView emptyfiller2 = (TextView) v.findViewById(R.id.tv_empty);
        emptyfiller2.setText("History Nilai Tidak Tersedia Untuk " + nim);
        listHistoryNilai.setAdapter(adapter);
        listHistoryNilai.setEmptyView(v2);

        return v;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
