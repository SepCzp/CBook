package com.example.czp.cookbook.ui.activity;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.api.RxManager;
import com.example.czp.cookbook.base.ui.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.img_wel)
    ImageView img_wel;
    @BindView(R.id.tv_skip)
    TextView tv_skip;

    private int time = 3;
    private boolean isSkip = false;

    @Override
    protected void initView() {
        img_wel.post(()->{
            ViewGroup.LayoutParams params = img_wel.getLayoutParams();
            params.height = img_wel.getHeight() + getStatusHeight();
        });

        setNoSkid(true);

        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)//计时三次
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return time - aLong;
                    }
                }).compose(RxManager.<Long>rxSchedulerHelper())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        tv_skip.setText("跳过 : " + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //完成跳转activity
                        if (!isSkip) {
                            goActivity(MainActivity.class);
                            finish();
                        }
                    }
                });

    }

    @OnClick(R.id.tv_skip)
    public void skipActivity() {
        goActivity(MainActivity.class);
        isSkip = true;
        finish();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_welcome;
    }

}
