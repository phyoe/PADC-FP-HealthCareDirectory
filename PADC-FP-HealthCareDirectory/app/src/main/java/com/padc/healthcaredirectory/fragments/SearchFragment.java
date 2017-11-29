package com.padc.healthcaredirectory.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.activities.SearchResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    @BindView(R.id.txtSearch)
    EditText txtSearch;

    private static String mSearchText;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.btnSearch)
    public void onTapSearch() {

        mSearchText = txtSearch.getText().toString();

        Intent intent = SearchResultActivity.newInent(mSearchText);
        startActivity(intent);
    }
}
