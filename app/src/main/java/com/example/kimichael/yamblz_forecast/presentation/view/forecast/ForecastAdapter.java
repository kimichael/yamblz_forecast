package com.example.kimichael.yamblz_forecast.presentation.view.forecast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.ForecastPresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.holders.BaseHolder;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.holders.HeaderHolder;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.holders.ItemHolder;

import java.util.List;

/**
 * Created by Sinjvf on 06.08.2017.
 * adapter for weather+forecast recycler
 */

class ForecastAdapter extends RecyclerView.Adapter<BaseHolder> {
    private ForecastInfo header;
    private List<ForecastInfo> list;
    private ForecastPresenter presenter;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    ForecastAdapter(ForecastPresenter presenter) {
        this.presenter = presenter;
    }

    void setHeader(ForecastInfo header) {
        this.header = header;
        notifyItemChanged(0);
    }

    public void setList(List<ForecastInfo> list) {
        this.list = list;
        if (header == null) notifyDataSetChanged();
        else {
            notifyItemRangeChanged(1, list.size() + 1);
        }
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case TYPE_HEADER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_header, parent, false);
                return new HeaderHolder(v, presenter);
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
                return new ItemHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (header != null && position == 0)
            holder.bind(header);
        else if (header == null)
            holder.bind(list.get(position));
        else
            holder.bind(list.get(position - 1));
    }

    @Override
    public int getItemViewType(int position) {
        if (header != null && position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        int res = 0;
        res += (header == null) ? 0 : 1;
        res += (list == null) ? 0 : list.size();
        return res;
    }
}
