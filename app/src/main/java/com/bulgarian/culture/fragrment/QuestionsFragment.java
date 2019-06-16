package com.bulgarian.culture.fragrment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bulgarian.culture.R;
import com.bulgarian.culture.activity.HistoryActivity;

public class QuestionsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment_layout, container, false);
        ((HistoryActivity) getActivity()).setViewPager(0);
        return view;
    }
}
