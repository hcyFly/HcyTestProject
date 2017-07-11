package com.cn.burus.hcytestproject.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.base.BaseActivity;
import com.cn.burus.hcytestproject.utils.SystemInfo;
import com.cn.burus.hcytestproject.utils.T;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.socks.library.KLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";
    private Context mContext;
    private int mTicket = 20;
    @BindView(R.id.but_intent_hide)
    Button mButIntentHide;
    @BindView(R.id.but_intent_hide2)
    Button butIntentHide2;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.but_commit)
    Button mButCommit;
    @BindView(R.id.rl_rootview)
    RelativeLayout mRlRootview;
    @BindView(R.id.but_code_s)
    Button mButCodeS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.et_name, R.id.et_password, R.id.but_commit, R.id.but_code_s, R.id.but_intent_hide, R.id.but_intent_hide2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_name:
                break;
            case R.id.et_password:
                break;
            case R.id.but_intent_hide:
                Intent mIntent = new Intent("com.cn.burus.hcytestproject.SENCOND_ATY");
                mIntent.addCategory("com.cn.burus.hcytestproject.SENCOND_ATY.MY_ACTION");
                startActivity(mIntent);
                T.showShort(mContext, "click intent hide");
                break;

            case R.id.but_intent_hide2:
                // TODO: 2017/6/27   rxjava test
                Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> observableEmitter) throws Exception {
                        for (int i = 0; ; i++) {
                            observableEmitter.onNext(i);
                        }

                    }
                }).subscribeOn(Schedulers.io()).sample(2, TimeUnit.MICROSECONDS);

                Observer<Integer> observer =new  Observer<Integer>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG,""+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }

                };

                observable1.subscribe(observer);


                break;

            case R.id.but_code_s:
                // TODO: 2017/4/21  验证码
                verifyCodeSuccess(8, 180);
                break;
            case R.id.but_commit:

                Acp.getInstance(this).request(new AcpOptions.Builder()
                                .setPermissions(Manifest.permission.READ_PHONE_STATE)
                                .build(),
                        new AcpListener() {
                            @Override
                            public void onGranted() {
                                String deviceid = SystemInfo.generateUniqueDeviceId();
                                KLog.i(TAG, "deviceid--" + deviceid);
                            }

                            @Override
                            public void onDenied(List<String> permissions) {
                                Toast.makeText(LoginActivity.this, permissions.get(0) + "，请到‘设置’中开启，才能使用此功能。", Toast.LENGTH_SHORT).show();
                            }
                        });


//                for (int i = 0; i < 21; i++) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //买票
//                            sellTicket();
//                        }
//                    }).start();
//                }
                break;
        }
    }

    private void sellTicket() {
        mTicket--;
        KLog.e(TAG, "剩余车票：" + mTicket);
    }

    //验证码
    public void verifyCodeSuccess(int reaskDuration, int expireDuration) {
        Toast.makeText(LoginActivity.this, "验证码已经发送，请注意查看," + expireDuration + " 秒内有效", Toast.LENGTH_SHORT).show();
        startTime(new WeakReference<Button>(mButCodeS), "获取验证码", reaskDuration, 1);
    }

    /**
     * 计时器 android 自封装
     *
     * @param but
     * @param defaultStr
     * @param max
     * @param interval
     */
    private static void startTime(final WeakReference<Button> but, final String defaultStr, int max, int interval) {

        but.get().setEnabled(false);
        new CountDownTimer(max * 1000, interval * 1000 - 10) {
            @Override
            public void onTick(long l) {
                if (null == but.get()) {
                    this.cancel();
                    KLog.i(TAG, "CountDownTimer cancel --113line");
                } else {
                    but.get().setText(((l + 15) / 1000) + "s");
                }
            }

            @Override
            public void onFinish() {
                if (null == but.get()) {
                    this.cancel();
                    KLog.i(TAG, "CountDownTimer cancel --123line");
                    return;
                }
                but.get().setEnabled(true);
                but.get().setText(defaultStr);
            }
        }.start();
    }

    /**
     * 菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_item:
                T.showShort(mContext, "click add");
                break;
            case R.id.remove_item:
                T.showShort(mContext, "click remove");
                break;
            default:
                break;
        }
        return true;
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


}
