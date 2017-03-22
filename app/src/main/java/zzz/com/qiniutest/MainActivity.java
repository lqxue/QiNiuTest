package zzz.com.qiniutest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;

import zzz.com.qiniutest.utils.Auth;
import zzz.com.qiniutest.utils.QiNiuInitialize;

import static zzz.com.qiniutest.utils.Config.ACCESS_KEY;
import static zzz.com.qiniutest.utils.Config.BUCKET_NAME;
import static zzz.com.qiniutest.utils.Config.SECRET_KEY;
import static zzz.com.qiniutest.utils.Config.TEST_DOMAIN;

public class MainActivity extends AppCompatActivity {

    //上传到七牛后保存的文件名
    String key = "myjava.jpg";
    private ImageView iv;
    private TextView tv;
    private Context mContext = MainActivity.this;
    private boolean isCancel;
    private Button btSure;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.textView);
        btSure = (Button) findViewById(R.id.button);
        btCancel = (Button) findViewById(R.id.button2);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCancel = true;
            }
        });
    }

    //获取token(开发中放到业务服务器)
    public String getUpToken() {
        return Auth.create(ACCESS_KEY, SECRET_KEY).uploadToken(BUCKET_NAME);
    }

    public void uploadImage() {
        //定义数据上传结束后的处理动作
        final UpCompletionHandler upCompletionHandler = new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                tv.setText("上传成功");
                Glide.with(mContext)
                        .load(TEST_DOMAIN + key)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(iv);
            }
        };
        final UploadOptions uploadOptions = new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String key, final double percent) {
                //百分数格式化
                NumberFormat fmt = NumberFormat.getPercentInstance();
                fmt.setMaximumFractionDigits(2);//最多两位百分小数，如25.23%
                tv.setText("图片已经上传:" + fmt.format(percent));
            }
        }, new UpCancellationSignal() {
            @Override
            public boolean isCancelled() {
                return isCancel;
            }
        });
        try {
            //上传图片
            QiNiuInitialize.getSingleton().put(getByte(), key, getUpToken(), upCompletionHandler, uploadOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取资源文件中的图片
    public byte[] getByte() {
        
     //测试pull
        Resources res = getResources();
        Bitmap bm = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        return baos.toByteArray();
    }
}
