package com.example.danisharshad.mixpanelproject.tasks;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.danisharshad.mixpanelproject.R;
import com.example.danisharshad.mixpanelproject.Utils.ImageUtils;

import java.lang.ref.WeakReference;

/**
 * Created by danisharshad on 5/17/16.
 */
public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private String path;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        this.path = imageView.getTag().toString();

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return ImageUtils.downloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (!imageView.getTag().toString().equals(path)) {
                    return;
                }
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    Drawable placeholder = imageView.getContext().getResources().
                            getDrawable(R.drawable.ic_launcher);
                    imageView.setImageDrawable(placeholder);
                }
            }
        }
    }
}