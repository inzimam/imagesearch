package com.android.hikedomo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.hikedomo.MyApp;
import com.android.hikedomo.R;
import com.android.hikedomo.adapter.SearchAdapter;
import com.android.hikedomo.entity.Photo;
import com.android.hikedomo.utils.Constants;
import com.android.hikedomo.utils.Utils;
import com.android.hikedomo.viewmodel.SearchViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;


public class SearchFragment extends Fragment {

    @Inject
    public Retrofit retrofit;
    @Inject
    public Picasso picasso;
    private Unbinder unbinder;
    private SearchViewModel mViewModel;
    @BindView(R.id.recyclerview)
    public RecyclerView recyclerView;
    @BindView(R.id.searchBox)
    public EditText searchBox;
    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    private ArrayList<Photo> photoList = new ArrayList<>();
    private SearchAdapter searchAdapter;

    private String searchText = "";
    private String lastQueriedText = "";

    public SearchFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MyApp) context.getApplicationContext()).getApiComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeUIComponent();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
    }

    private void initializeUIComponent() {
        searchAdapter = new SearchAdapter(photoList, picasso);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        searchBox.addTextChangedListener(textWatcher);
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence query, int start, int before, int count) {
            searchText = searchBox.getText().toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (!TextUtils.isEmpty(searchText)) {
                if (!lastQueriedText.contentEquals(searchText)) {
                    photoList.clear();
                    searchAdapter.notifyDataSetChanged();
                    fetchImages(searchText);
                }
                lastQueriedText = searchText;
            }
        }
    };

    Map<String, String> getMap(String searchText) {
        Map<String, String> data = new HashMap<>();
        data.put("method", Constants.METHOD);
        data.put("api_key", Constants.API_KEY);
        data.put("format", Constants.FORMAT);
        data.put("nojsoncallback", Constants.NOJSONCALLBACK);
        data.put("text", searchText);
        return data;
    }

    private void fetchImages(String searchText) {
        loaderVisibility(View.VISIBLE);
        Map<String, String> data = getMap(searchText);
        mViewModel.getImageList(retrofit, data).observe(this, mainResponse -> {
            loaderVisibility(View.GONE);
            mViewModel.setNull();
            if (mainResponse != null && mainResponse.photos != null) {
                Utils.hideKeyboard(getActivity());
                photoList.addAll(mainResponse.photos.getImageList());
                searchAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Something went wrong, Please try again later", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loaderVisibility(int visibility) {
        progressbar.setVisibility(visibility);
    }

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
