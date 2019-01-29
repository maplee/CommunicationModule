package com.matt.connection.inner;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author:Created by Matt on 2018/7/18.
 *
 */

public class PipeProvider extends ContentProvider {

    private static final String TAG = "Pipe";

    private Context mContext = null;


    private UriMatcher mMatcher;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        initMatcher();
        return false;
    }

    private void initMatcher() {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(Constant.AUTHORITY+PipeHelper.getPackage(), Constant.QUERY, Constant.CODE_QUERY);
        mMatcher.addURI(Constant.AUTHORITY+PipeHelper.getPackage(), Constant.UPDATE, Constant.CODE_UPDATE);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (CompileConfig.DEBUG) {
            Log.i(TAG, "query Uri = " + uri.toString());
        }
        MatrixCursor cursor;
        String result = null;
        switch (mMatcher.match(uri)){
            case Constant.CODE_QUERY:
                cursor = new MatrixCursor(new String[]{"value"});
                result = PipeHelper.query(selection);
                if (CompileConfig.DEBUG) {
                    Log.d(TAG, "query = " + result);
                }
                cursor.addRow(new Object[]{result});
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (CompileConfig.DEBUG) {
            Log.d(TAG, "update Uri = " + uri.toString());
        }
        if(values == null){
            return 0;
        }
        switch (mMatcher.match(uri)){
            case Constant.CODE_UPDATE:
                String data = values.getAsString(Constant.DATA);
                String source = values.getAsString(Constant.SOURCE);
                PipeHelper.result(source,data);
                break;
        }
        return 0;
    }
}
