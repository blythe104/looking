package com.looking.classicalparty.lib.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.media.ThumbnailUtils;
import android.os.Build;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * 项目名称 : ZXingScanQRCode
 * 版本 :	[v1.0]
 * 类描述 : LOGO图片加上白色背景图片
 */
public class LogoConfig {
    /**
     * 蓝点颜色
     */
    private static final int BLUE = 0xFF163355;
    /**
     * 白色
     */
    private static final int WHITE = 0xFFFFFFFF;
    /**
     * 正方形二维码宽度
     */
    private static final int CODE_WIDTH = 440;
    /**
     * LOGO宽度值,最大不能大于二维码20%宽度值,大于可能会导致二维码信息失效
     */
    private static final int LOGO_WIDTH_MAX = CODE_WIDTH / 5;
    /**
     * LOGO宽度值,最小不能小鱼二维码10%宽度值,小于影响Logo与二维码的整体搭配
     */
    private static final int LOGO_WIDTH_MIN = CODE_WIDTH / 10;
    private static String path = "/sdcard/qrcode/";

    /**
     * 绘制圆角矩形
     *
     * @param bitmap 需要转换的图片
     * @param pixels 圆角弧度
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap toRoundRect(Bitmap bitmap, int pixels) {

        //绘制圆角矩形
        Bitmap mOut = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), pixels, pixels, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        return mOut;
    }

    /**
     * @return 返回带有白色背景框logo
     */
    public Bitmap modifyLogo(Bitmap bgBitmap, Bitmap logoBitmap) {

        //读取背景图片，并构建绘图对象
        int bgWidth = bgBitmap.getWidth();
        int bgHeigh = bgBitmap.getHeight();
        //通过ThumbnailUtils压缩原图片，并指定宽高为背景图的3/4
        logoBitmap = ThumbnailUtils.extractThumbnail(logoBitmap, bgWidth, bgHeigh, ThumbnailUtils
                .OPTIONS_RECYCLE_INPUT);
        Bitmap cvBitmap = Bitmap.createBitmap(bgWidth, bgHeigh, Config.ARGB_8888);
        Canvas canvas = new Canvas(cvBitmap);
        // 开始绘制图片
        canvas.drawBitmap(bgBitmap, 0, 0, null);
        canvas.drawBitmap(logoBitmap, (bgWidth - logoBitmap.getWidth()) / 2, (bgHeigh - logoBitmap.getHeight()) / 2,
                null);
        canvas.save(Canvas.ALL_SAVE_FLAG);// 保存
        canvas.restore();
        if (cvBitmap.isRecycled()) {
            cvBitmap.recycle();
        }
        return cvBitmap;
    }

    /**
     * 生成带LOGO的二维码
     */
    public Bitmap createCode(String content, Bitmap logoBitmap) throws WriterException {
        int logoWidth = logoBitmap.getWidth();
        int logoHeight = logoBitmap.getHeight();
        int logoHaleWidth = logoWidth >= CODE_WIDTH ? LOGO_WIDTH_MIN : LOGO_WIDTH_MAX;
        int logoHaleHeight = logoHeight >= CODE_WIDTH ? LOGO_WIDTH_MIN : LOGO_WIDTH_MAX;
        // 将logo图片按martix设置的信息缩放
        Matrix m = new Matrix();
        float sx = (float) 1.3 * logoHaleWidth / logoWidth;
        float sy = (float) 1.3 * logoHaleHeight / logoHeight;
        m.setScale(sx, sy);// 设置缩放信息
        Bitmap newLogoBitmap = Bitmap.createBitmap(logoBitmap, 0, 0, logoWidth, logoHeight, m, false);
        int newLogoWidth = newLogoBitmap.getWidth();
        int newLogoHeight = newLogoBitmap.getHeight();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错级别,H为最高
        hints.put(EncodeHintType.MAX_SIZE, LOGO_WIDTH_MAX);// 设置图片的最大值
        hints.put(EncodeHintType.MIN_SIZE, LOGO_WIDTH_MIN);// 设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置白色边距值
        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_WIDTH,
                hints);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int halfW = width / 2;
        int halfH = height / 2;
        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > halfW - newLogoWidth / 2 && x < halfW + newLogoWidth / 2 && y > halfH - newLogoHeight / 2 &&
                        y < halfH + newLogoHeight / 2) {// 该位置用于存放图片信息
                    pixels[y * width + x] = newLogoBitmap.getPixel(x - halfW + newLogoWidth / 2, y - halfH +
                            newLogoHeight / 2);
                } else {
                    pixels[y * width + x] = matrix.get(x, y) ? BLUE : WHITE;// 设置信息
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
