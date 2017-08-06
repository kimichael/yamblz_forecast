package com.example.kimichael.yamblz_forecast.presentation.view.settings.dialogs.select;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kimichael.yamblz_forecast.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Sinjvf on 05.08.2017.
 *
 * super class for choosing dialogs
 */

public abstract class SelectorDialogFragment extends DialogFragment {
    @BindView(R.id.list_view)
    ListView listView;
    private Unbinder unbinder;
    private static final String POSITION = "position";

    public static Bundle getBundle(int position){
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        return args;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_selector, container, false);
        unbinder = ButterKnife.bind(this, v);
        try {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }catch (NullPointerException e){
            Timber.e(e.getMessage());
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   SelectAdapter adapter = new SelectAdapter(getList(), getActivity());

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_single_choice, getList()));
    }

    public abstract List<String> getList();
    public abstract void savePosition(int position);

    @OnClick(R.id.ok_button)
    public void okClick() {
        int position = listView.getCheckedItemPosition();
        savePosition(position);
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
