package com.github.controlx.catalogue.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.github.controlx.catalogue.R;
import com.github.controlx.catalogue.adapters.CatalogueGridAdapter;
import com.github.controlx.catalogue.helper.JsonLoader;
import com.github.controlx.catalogue.model.Content;

import java.util.ArrayList;

/**
 * Created by Abhishek Verma on 11/27/2016.
 */

public class CatalogueFragment extends Fragment implements AbsListView.OnScrollListener{

    private CatalogueGridAdapter mCatalogueGridAdapter;
    private GridView mGridView;
    private ArrayList<Content> mGlobalContentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_catalogue, container, false);

        mGlobalContentList = JsonLoader.getInstance().parseAndLoadClass(getActivity(), JsonLoader.Parse.FIRST_FILE);
        mCatalogueGridAdapter = new CatalogueGridAdapter(getActivity(), mGlobalContentList);

        mGridView = (GridView) rootView.findViewById(R.id.gridview_catalogue);
        mGridView.setOnScrollListener(this);
        mGridView.setAdapter(mCatalogueGridAdapter);

        return rootView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount >= totalItemCount){
            ArrayList<Content> contentList = null;

            //Currently hard coding the numbers as inconsisteny is found between 3rd file and other 2 files related to total items.
            if(totalItemCount == 20){
                contentList = JsonLoader.getInstance().parseAndLoadClass(getActivity(), JsonLoader.Parse.SECOND_FILE);
            }
            else if(totalItemCount == 40){
                contentList = JsonLoader.getInstance().parseAndLoadClass(getActivity(), JsonLoader.Parse.THIRD_FILE);
            }

            if(contentList != null) {
                mGlobalContentList.addAll(contentList);
                mCatalogueGridAdapter.notifyDataSetChanged();
            }
        }
    }

    public CatalogueGridAdapter getCatalogueGridAdapter(){
        return mCatalogueGridAdapter;
    }
}
