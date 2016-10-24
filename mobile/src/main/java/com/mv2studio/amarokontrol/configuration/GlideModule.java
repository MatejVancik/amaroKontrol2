/*
 * Copyright (c) 2016 Localhost s.r.o. - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mv2studio.amarokontrol.configuration;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

import java.io.File;

/**
 * Created by matej on 16/08/16.
 */

public class GlideModule implements com.bumptech.glide.module.GlideModule {

    private static DiskCache sDiskCache;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(() -> {
            File cacheLocation = context.getExternalFilesDir("pictures");
            cacheLocation.mkdirs();
            sDiskCache = DiskLruCacheWrapper.get(cacheLocation, 50048576);
            return sDiskCache;
        });

        final int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 4);
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) { }

    public static DiskCache getDiskCache() {
        return sDiskCache;
    }

}
