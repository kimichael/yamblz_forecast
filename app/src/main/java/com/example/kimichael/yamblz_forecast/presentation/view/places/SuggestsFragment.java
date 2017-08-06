package com.example.kimichael.yamblz_forecast.presentation.view.places;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.network.places.response.Prediction;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SuggestsPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Sinjvf on 05.08.2017.
 * fragment for city choosing
 */

public class SuggestsFragment extends DialogFragment implements SuggestsView{
    @BindView(R.id.city_input)
    SearchView searchView;
    @BindView(R.id.list_view)
    ListView listView;
    private Unbinder unbinder;
    @Inject
    SuggestsPresenter presenter;

    public static SuggestsFragment getInstance(){
        return new SuggestsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_suggests, container, false);
        unbinder = ButterKnife.bind(this, v);

        App.getInstance().getSettingsScreenComponent().inject(this);

        presenter.onAttach(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addList(null);
        // RxAutoCompleteTextView.itemClickEvents(searchView).subscribeWith();
        SearchView.SearchAutoComplete searchSrcTextView =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchSrcTextView.setTextColor(Color.BLACK);
        RxTextView.textChangeEvents(searchSrcTextView)
                .skip(1)
                .debounce(400, TimeUnit.MILLISECONDS)
                .map(event-> event.text().toString())
                .filter(s -> s.length()>2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        presenter.getCitiesIds(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDetach();
    }

    @Override
    public void addList(List<Prediction> list) {
        if (list==null) {
            listView.setVisibility(View.GONE);
        }else {
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new ArrayAdapter<>(getActivity(),
                    R.layout.item_suggest, list));
            listView.setOnItemClickListener((adapterView, view, i, l) -> presenter.saveCity(list.get(i)));
        }
    }
}
