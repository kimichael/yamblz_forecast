package com.example.kimichael.yamblz_forecast.presentation.view.forecast;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.ForecastPresenter;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.presentation.view.ToolbarOwner;
import com.example.kimichael.yamblz_forecast.presentation.view.settings.SureDialog;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableCombineLatest;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class ForecastFragment extends BaseForecastFragment implements ForecastView {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Inject
    ForecastPresenter forecastPresenter;
    private ForecastAdapter adapter;
    private final static String DATA_KEY = "DataKey";
    private PlaceData data;
    private PublishSubject<Object> subjectViewCreated = PublishSubject.create();
    private PublishSubject<Object> subjectViewOnScreen = PublishSubject.create();

    public static ForecastFragment getInstance(PlaceData data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DATA_KEY, data);
        ForecastFragment fragment = new ForecastFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            data = bundle.getParcelable(DATA_KEY);
        }
    }

    public ForecastFragment() {
        Observable.combineLatest(subjectViewCreated, subjectViewOnScreen,
                (a, b) -> false)
                .subscribe(this::getForecast, this::errorHandling);
    }

    private void errorHandling(Throwable e) {
        Timber.e("errorHandling: " + e.getMessage());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().getForecastScreenComponent().inject(this);
        getData();
        forecastPresenter.setData(data);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        forecastPresenter.onAttach(this);
        initRecycler();
        subjectViewCreated.onNext(new Object());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            forecastPresenter.getForecast(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        forecastPresenter.onDetach();
        App.getInstance().releaseForecastScreenComponent();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);
        setHasOptionsMenu(true);
        return v;
    }

    private void initRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        ((ToolbarOwner) getActivity()).clearMenu();
        inflater.inflate(R.menu.forecast_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showForecast(List<ForecastInfo> forecastsList) {
        if (adapter != null) adapter.setList(forecastsList);
    }

    @Override
    public PlaceData getPlace() {
        return data;
    }

    @Override
    public void showSureDialog() {
        SureDialog dialogFragment = SureDialog.getInstance(data);
        dialogFragment.show(getActivity().getFragmentManager(), null);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.delete)
    public void deleteCity() {
        forecastPresenter.showSureDialog();
    }

    @Override
    public void onScreen() {
        subjectViewOnScreen.onNext(new Object());
    }

    public void getForecast(Boolean bool) {
        forecastPresenter.getForecast(bool);
    }
}
