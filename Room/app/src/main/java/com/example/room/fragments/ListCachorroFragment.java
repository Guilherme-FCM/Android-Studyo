package com.example.room.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.room.MainActivity;
import com.example.room.R;
import com.example.room.daos.CachorroDao;
import com.example.room.entities.Cachorro;
import com.example.room.entities.CachorroPessoa;

import java.util.ArrayList;
import java.util.List;

public class ListCachorroFragment extends Fragment {

    private CachorroDao dao;

    public ListCachorroFragment(CachorroDao dao) {
        this.dao = dao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_cachorro, container, false);

        ListView listView = view.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listCachorro());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, v, i, l) -> {
            CachorroDialog dialog = new CachorroDialog(getCachorro(i), dao, adapter);
            dialog.show(getActivity().getSupportFragmentManager(), "CachorroDialog");
        });

        return view;
    }

    private List<String> listCachorroPessoa() {
        List<CachorroPessoa> result = dao.listWithPessoa();
        List<String> strings = new ArrayList<>();

        for (CachorroPessoa cp : result) {
            strings.add(
                "Cachorro: " + cp.cachorro.toString() +
                "Pessoa: " + cp.pessoas.toString()
            );
        }
        return strings;
    }

    private String[] listCachorro() {
        return dao.list()
                .stream()
                .map(Cachorro::toString)
                .toArray(String[]::new);
    }

    private Cachorro getCachorro(int i) {
        return dao.list().get(i);
    }
}