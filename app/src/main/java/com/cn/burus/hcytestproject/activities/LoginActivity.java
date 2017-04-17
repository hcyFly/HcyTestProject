package com.cn.burus.hcytestproject.activities;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.base.BaseActivity;
import com.cn.burus.hcytestproject.utils.SystemInfo;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.socks.library.KLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    public static String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.but_commit)
    Button mButCommit;
    @BindView(R.id.rl_rootview)
    RelativeLayout mRlRootview;
    private int mTicket = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.et_name, R.id.et_password, R.id.but_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_name:
                break;
            case R.id.et_password:
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


}
