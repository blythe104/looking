package com.looking.classicalparty.moudles.about.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;
import com.looking.classicalparty.lib.utils.LogoConfig;

import java.io.File;
import java.io.FileOutputStream;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AboutUsActivity extends BaseActivity {


    /**
     * 黑点颜色
     */
    private static final int BLACK = 0xFF000000;
    /**
     * 白色
     */
    private static final int WHITE = 0xFFFFFFFF;

    //二维码定义字段 begin
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
    private TitleBar mTitleBar;
    private ImageView mImgCode;
    /**
     * 生成的二维码图片存储的URI
     */
    private Uri imageFileUri;

    //end

    @Override
    public void initView() {
        setContentView(R.layout.activity_about_us);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        //二维码位置
        mImgCode = (ImageView) findViewById(R.id.img_code);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("关于我们");
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {

            }
        });
        setImgCode();
    }

    /**
     * 设置二维码到指定位置
     */
    private void setImgCode() {

        String content = "hello welcome!";
        try {
            if (!TextUtils.isEmpty(content)) {
                LogoConfig logoConfig = new LogoConfig();
                Bitmap logoBitmap = logoConfig.modifyLogo(BitmapFactory.decodeResource(getResources(), R.mipmap
                        .white_bg), BitmapFactory.decodeResource(getResources(), R.mipmap.img_logo));
                Bitmap codeBitmap = createCode(content, logoBitmap);
                saveBitmap(codeBitmap, "classical_code.png");
                mImgCode.setImageBitmap(codeBitmap);
            } else {
                Crouton.makeText(this, "请输入要生成的字符串", Style.CONFIRM).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存二维码图片
     *
     * @param codeBitmap
     * @param s
     */
    private void saveBitmap(Bitmap codeBitmap, String bitName) {
        //获取与应用相关联的路径
        String imageFilePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        File imageFile = new File(imageFilePath, "/" + bitName);// 通过路径创建保存文件
        imageFileUri = Uri.fromFile(imageFile);
        if (imageFile.exists()) {
            imageFile.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(imageFile);
            if (codeBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 根据内容+logo 创建二维码
     *
     * @param content
     * @param logoBitmap
     * @return
     */
    private Bitmap createCode(String content, Bitmap logoBitmap) {
        int logoWidth = logoBitmap.getWidth();
        int logoHeight = logoBitmap.getHeight();
        int logoHaleWidth = logoWidth >= CODE_WIDTH ? LOGO_WIDTH_MIN : LOGO_WIDTH_MAX;
        int logoHaleHeight = logoHeight >= CODE_WIDTH ? LOGO_WIDTH_MIN : LOGO_WIDTH_MAX;
        // 将logo图片按martix设置的信息缩放
        Matrix m = new Matrix();
        float sx = (float) 2 * logoHaleWidth / logoWidth;
        float sy = (float) 2 * logoHaleHeight / logoHeight;
        m.setScale(sx, sy);// 设置缩放信息
        Bitmap newLogoBitmap = Bitmap.createBitmap(logoBitmap, 0, 0, logoWidth, logoHeight, m, false);
        int newLogoWidth = newLogoBitmap.getWidth();
        int newLogoHeight = newLogoBitmap.getHeight();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错级别,H为最高
        hints.put(EncodeHintType.MAX_SIZE, LOGO_WIDTH_MAX);// 设置图片的最大值
        hints.put(EncodeHintType.MIN_SIZE, LOGO_WIDTH_MIN);// 设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 2);//设置白色边距值
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
                /*
             * 取值范围,可以画图理解下
             * halfW + newLogoWidth / 2 - (halfW - newLogoWidth / 2) = newLogoWidth
             * halfH + newLogoHeight / 2 - (halfH - newLogoHeight) = newLogoHeight
                */
                if (x > halfW - newLogoWidth / 2 && x < halfW + newLogoWidth / 2 && y > halfH - newLogoHeight / 2 &&
                        y < halfH + newLogoHeight / 2) {// 该位置用于存放图片信息
                    /*
                     *  记录图片每个像素信息
					 *  halfW - newLogoWidth / 2 < x < halfW + newLogoWidth / 2
					 *  --> 0 < x - halfW + newLogoWidth / 2 < newLogoWidth
					 *   halfH - newLogoHeight / 2  < y < halfH + newLogoHeight / 2
					 *   -->0 < y - halfH + newLogoHeight / 2 < newLogoHeight
					 *   刚好取值newLogoBitmap。getPixel(0-newLogoWidth,0-newLogoHeight);
					 */
                    pixels[y * width + x] = newLogoBitmap.getPixel(x - halfW + newLogoWidth / 2, y - halfH +
                            newLogoHeight / 2);
                } else {
                    pixels[y * width + x] = matrix.get(x, y) ? BLACK : WHITE;// 设置信息
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


    @Override
    public void processClick(View v) {

    }
}
