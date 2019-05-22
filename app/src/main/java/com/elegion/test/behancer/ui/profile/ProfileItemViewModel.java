package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.DateUtils;

public class ProfileItemViewModel {

    private String mImageUrl;
    private String mDisplayName;
    private String mUserName;
    private String mDateCreated;
    private String mLocation;

    public ProfileItemViewModel(User user) {
        mImageUrl = user.getImage().getPhotoUrl();
        mDisplayName = user.getDisplayName();
        mUserName = user.getUsername();
        mDateCreated = DateUtils.format(user.getCreatedOn());
        mLocation = user.getLocation();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public String getLocation() {
        return mLocation;
    }
}
