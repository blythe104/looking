package com.looking.classicalparty;

import android.app.Application;

import com.bugtags.library.Bugtags;
import com.looking.classicalparty.lib.utils.SharedPreUtils;

/**
 * Created by xin on 2016/10/18.
 */
public class ClassicalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreUtils.init(this);
        Bugtags.start("9beccb4c14e8f6bd2e0b46f61983d8dc", this, Bugtags.BTGInvocationEventBubble);
    }
}
