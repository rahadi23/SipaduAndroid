package com.rahadi.sipadu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.rahadi.sipadu.R;
import com.rahadi.sipadu.adapter.Nilai;
import com.rahadi.sipadu.adapter.StringContainer;
import com.rahadi.sipadu.gettersetter.BeritaGetsetter;
import com.rahadi.sipadu.gettersetter.NilaiGetsetter;

import java.util.ArrayList;


public class NilaiAktifFragment extends Fragment {

    private String nim;
    private ArrayList<NilaiGetsetter> listItem;

    public NilaiAktifFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nilai_aktif, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        String nim = bundle.getString("NIM");

        listItem = new ArrayList<>();
        NilaiGetsetter nilai;

        for (int i = 0; i < StringContainer.konten_nilai_aktif.length; i++){
            nilai = new NilaiGetsetter();
            nilai.setInisial(StringContainer.konten_nilai_aktif[i][0].substring(0, 1).toUpperCase());
            nilai.setNamamatkul(StringContainer.konten_nilai_aktif[i][0]);
            nilai.setNamadosen(StringContainer.konten_nilai_aktif[i][1]);
            nilai.setNilai(StringContainer.konten_nilai_aktif[i][2]);

            listItem.add(nilai);
        }

        Nilai adapter = new Nilai(this.getActivity(), listItem);
        // Attach the adapter to a ListView
        ListView listNilaiAktif = (ListView) getActivity().findViewById(R.id.lv_nilaiaktif);
        TextView emptyfiller = (TextView) getActivity().findViewById(R.id.tv_empty);
        View view = getActivity().findViewById(R.id.v_emptySemesterAktif);
        emptyfiller.setText("Daftar Nilai Belum Tersedia Untuk " + nim);

        listNilaiAktif.setAdapter(adapter);
        listNilaiAktif.setEmptyView(view);
    }
}
