package com.example.emilioward.mobilecoursework;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by emilioward on 07/03/2016.
 */
public class UILConfig {

    public static ImageLoaderConfiguration config(Context context){


        DisplayImageOptions defaultOptions =
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .showImageOnLoading(android.R.drawable.stat_sys_download)
                        .showImageForEmptyUri(android.R.drawable.ic_dialog_alert)
                        .showImageOnFail(android.R.drawable.stat_notify_error)
                        .considerExifParams(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                        .build();

        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        return config;
    }
}
