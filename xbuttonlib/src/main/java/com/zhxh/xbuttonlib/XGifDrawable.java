package com.zhxh.xbuttonlib;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifOptions;
import pl.droidsonroids.gif.InputSource;

/**
 * Created by zhxh on 2018/6/29
 */
public final class XGifDrawable extends GifDrawable {
    public XGifDrawable(@NonNull Resources res, int id) throws Resources.NotFoundException, IOException {
        super(res, id);
    }

    public XGifDrawable(@NonNull AssetManager assets, @NonNull String assetName) throws IOException {
        super(assets, assetName);
    }

    public XGifDrawable(@NonNull String filePath) throws IOException {
        super(filePath);
    }

    public XGifDrawable(@NonNull File file) throws IOException {
        super(file);
    }

    public XGifDrawable(@NonNull InputStream stream) throws IOException {
        super(stream);
    }

    public XGifDrawable(@NonNull AssetFileDescriptor afd) throws IOException {
        super(afd);
    }

    public XGifDrawable(@NonNull FileDescriptor fd) throws IOException {
        super(fd);
    }

    public XGifDrawable(@NonNull byte[] bytes) throws IOException {
        super(bytes);
    }

    public XGifDrawable(@NonNull ByteBuffer buffer) throws IOException {
        super(buffer);
    }

    public XGifDrawable(@Nullable ContentResolver resolver, @NonNull Uri uri) throws IOException {
        super(resolver, uri);
    }

    protected XGifDrawable(@NonNull InputSource inputSource, @Nullable GifDrawable oldDrawable, @Nullable ScheduledThreadPoolExecutor executor, boolean isRenderingTriggeredOnDraw, @NonNull GifOptions options) throws IOException {
        super(inputSource, oldDrawable, executor, isRenderingTriggeredOnDraw, options);
    }
}
