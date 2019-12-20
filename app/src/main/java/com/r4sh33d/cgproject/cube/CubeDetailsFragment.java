package com.r4sh33d.cgproject.cube;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.r4sh33d.cgproject.R;
import com.r4sh33d.cgproject.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class CubeDetailsFragment extends BaseFragment {

    @BindView(R.id.cube_type_group)
    RadioGroup cubeTypeRadioGroup;
    @BindView(R.id.cube_movement_radio_group)
    RadioGroup movementRadioGroup;

    public static CubeDetailsFragment newInstance() {
        Bundle args = new Bundle();
        CubeDetailsFragment fragment = new CubeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CubeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cube_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarTitle("GL Cubes");
    }

    @OnClick(R.id.cube_draw)
    public void onClickDraw() {
        RadioButton cubeTypeButton = getView().findViewById(cubeTypeRadioGroup.getCheckedRadioButtonId());
        String cubeType = cubeTypeButton.getText().toString();
        RadioButton cubeMovementButton = getView().findViewById(movementRadioGroup.getCheckedRadioButtonId());
        String movementType = cubeMovementButton.getText().toString();

        Intent intent = new Intent(getContext(), CubeActivity.class);
        intent.putExtra(CubeActivity.CUBE_TYPE, cubeType);
        intent.putExtra(CubeActivity.MOVEMENT_TYPE, movementType);
        Timber.d(cubeType);
        Timber.d(movementType);
        startActivity(intent);
    }
}
