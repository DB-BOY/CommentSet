//package cn.dbboy.commentlib.base;
//
//import android.content.Context;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.GlideBuilder;
//import com.bumptech.glide.Registry;
//import com.bumptech.glide.annotation.GlideModule;
//import com.bumptech.glide.load.DecodeFormat;
//import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
//import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
//import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
//import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
//import com.bumptech.glide.load.engine.cache.LruResourceCache;
//import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
//import com.bumptech.glide.module.AppGlideModule;
//
//import java.io.File;
//
///**
// * Created by db.boy on 2018/5/3.
// */
//@GlideModule
//public class DBGlideModule extends AppGlideModule {
//
//
//    private static final String TAG = "DBGlideModule";
//
//    int diskSize = 1024 * 1024 * 100;
//    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存
//
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//        // 默认内存和图片池大小
//        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
//        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
//        int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
//        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize)); // 该两句无需设置，是默认的
//        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));
//        //定义图片的本地磁盘缓存
//        File cacheDir = context.getExternalCacheDir();//指定的是数据的缓存地址
//        int diskCacheSize = 1024 * 1024 * 30;//最多可以缓存多少字节的数据
//        //设置磁盘缓存大小
//        if (cacheDir != null) {
//            builder.setDiskCache(new DiskLruCacheFactory(cacheDir.getPath(), "glide", diskCacheSize));
//        }
//        // 定义缓存大小和位置
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  //内存中
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize)); //sd卡中
//      
//        // 定义图片格式
//        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565); // 默认
//        // 自定义内存和图片池大小
//        builder.setMemoryCache(new LruResourceCache(memorySize));
//        builder.setBitmapPool(new LruBitmapPool(memorySize));
//        //builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//    }
//
//    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//
//    }
//
//    @Override
//    public boolean isManifestParsingEnabled() {
//        return false;
//    }
//}
