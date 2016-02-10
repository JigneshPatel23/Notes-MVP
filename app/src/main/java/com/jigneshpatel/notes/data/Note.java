package com.jigneshpatel.notes.data;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created on 8/2/16.
 */
public final class Note {

    private final String mId;
    @Nullable
    private final String mTitle;
    @Nullable
    private final String mDescription;
    @Nullable
    private final String mImageUrl;

    public Note(String title, String description) {
        this(title, description, null);
    }

    public Note(String title, String description, String imageUrl) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;
    }

    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getImageUrl() {
        return mImageUrl;
    }

    public boolean isEmpty(){
        return TextUtils.isEmpty(mTitle) && TextUtils.isEmpty(mDescription);
    }

}
