package com.elegion.test.behancer.ui.profile;

import android.databinding.ObservableBoolean;
import android.view.View;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {
    private Disposable mDisposable;
    private Storage mStorage;
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);

    public ProfileViewModel(Storage storage) {
        mStorage = storage;
    }

    public void loadProfile(String mUsername) {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            //bind(response.getUser());
                        },
                        throwable -> {
                            mIsErrorVisible.set(true);
                        });
    }

    public void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ObservableBoolean getmIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableBoolean getmIsLoading() {
        return mIsLoading;
    }
}
