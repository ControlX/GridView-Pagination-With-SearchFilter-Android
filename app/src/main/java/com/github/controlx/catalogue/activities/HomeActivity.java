package com.github.controlx.catalogue.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.github.controlx.catalogue.R;
import com.github.controlx.catalogue.fragments.CatalogueFragment;
import com.github.controlx.catalogue.helper.ImageMapConstant;
import com.github.controlx.catalogue.helper.Utils;

import io.fabric.sdk.android.Fabric;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RelativeLayout mParentLayoutRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home);

        ImageMapConstant.init();
        initToolBar();
        initViews();
    }

    private void initViews(){
        mParentLayoutRL = (RelativeLayout)findViewById(R.id.activity_home_rl);
    }

    private void initToolBar(){
        SpannableString spannableString = new SpannableString(getString(R.string.romantic_comedy));
        spannableString.setSpan(new Utils.TypefaceSpan(this, "TitilliumWeb-Light.ttf"), 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(spannableString);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, searchItem, false);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setItemsVisibility(menu, searchItem, true);
                return false;
            }
        });

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Snackbar snackbar = Snackbar
                        .make(mParentLayoutRL, R.string.navigation_previous_screen, Snackbar.LENGTH_SHORT);
                View view = snackbar.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.YELLOW);
                snackbar.show();
                break;
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        CatalogueFragment catalogueFragment = (CatalogueFragment) getSupportFragmentManager().findFragmentById(R.id.catalogue_fragment);
        int size = catalogueFragment.getCatalogueGridAdapter().getCount();
        String toastText;

        if(size == 0)
            toastText = getString(R.string.no_result);
        else if(size == 1)
            toastText = size+getString(R.string.single_result);
        else
            toastText = size+getString(R.string.multiple_result);

        Snackbar snackbar = Snackbar
                .make(mParentLayoutRL, toastText, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.YELLOW);
        snackbar.show();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        CatalogueFragment catalogueFragment = (CatalogueFragment) getSupportFragmentManager().findFragmentById(R.id.catalogue_fragment);
        catalogueFragment.getCatalogueGridAdapter().getFilter().filter(newText);

        return true;
    }
}
