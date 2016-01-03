package com.smartchef.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * Created by Administrator on 14-May-15.
 */
public class ImageUtil {
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 8.0f;

    public static Bitmap roundCornerImage(Bitmap src, float round) {
        // Source image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create result bitmap output
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // set canvas for painting
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0, 0, 0, 0);

        // configure paint
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        // configure rectangle for embedding
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        // draw Round rectangle to canvas
        canvas.drawRoundRect(rectF, round, round, paint);

        // create Xfer mode
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // draw source image to canvasa
        canvas.drawBitmap(src, rect, rect, paint);

        // return final image
        return result;
    }

    public static Bitmap blurImage(Context context, Bitmap image) {

        Bitmap bitmap = image.copy(image.getConfig(), true);

        final RenderScript rs = RenderScript.create(context);
        final Allocation input = Allocation.createFromBitmap(rs, image, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(25);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
        image.recycle();
        rs.destroy();

        return bitmap;
    }

    public static Bitmap scaleImage(Bitmap bitmapInput, int boundBoxInDp) {
        // Get the ImageView and its bitmap
        Bitmap bitmap = bitmapInput;

        // Get current dimensions
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) boundBoxInDp) / width;
        float yScale = ((float) boundBoxInDp) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and c`onvert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
//        width = scaledBitmap.getWidth();
//        height = scaledBitmap.getHeight();
//
//        // Apply the scaled bitmap
//        view.setImageDrawable(result);
//
//        // Now change ImageView's dimensions to match the scaled image
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
//        params.width = width;
//        params.height = height;
//        view.setLayoutParams(params);
        return scaledBitmap;
    }

//    private int dpToPx(int dp) {
//        float density = getApplicationContext().getResources().getDisplayMetrics().density;
//        return Math.round((float) dp * density);
//    }
}
