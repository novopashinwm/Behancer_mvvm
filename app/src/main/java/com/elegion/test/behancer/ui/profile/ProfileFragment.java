package com.elegion.test.behancer.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Vladislav Falzan.
 */

public class ProfileFragment extends Fragment  {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    private String mUsername;

    private ProfileViewModel mProfileViewModel;


    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Storage mStorage = context instanceof Storage.StorageOwner ? ((Storage.StorageOwner) context).obtainStorage() : null;
        mProfileViewModel = new ProfileViewModel(mStorage);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_profile, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(PROFILE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
        }

        mProfileViewModel.loadProfile(mUsername);
    }


    /*private void bind(User user) {
        Picasso.with(getContext())
                .load(user.getImage().getPhotoUrl())
                .fit()
                .into(mProfileImage);
        mProfileName.setText(user.getDisplayName());
        mProfileCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
        mProfileLocation.setText(user.getLocation());
    }*/

    @Override
    public void onDetach() {
        mProfileViewModel.dispatchDetach();
        super.onDetach();
    }


}
