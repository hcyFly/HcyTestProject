package com.cn.burus.hcytestproject.test_ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.activities.LoginActivity;
import com.socks.library.KLog;

public class IpcActivity extends AppCompatActivity {


    private static final String TAG = "IpcActivity";

    private Messenger getRelpyMessager = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case MyConstants.MSG_FROM_SERVICE:

                    KLog.i(TAG,"receive msg from service:"+msg.getData().getString("reply"));

                    break;
                default:
            }
            super.handleMessage(msg);
        }
    }


    private Messenger mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mService = new Messenger(service);
            Message msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello ,this is client");
            msg.setData(bundle);
            //服务端返回能接受 必须加以下
            msg.replyTo=getRelpyMessager;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);

        //绑定远程服务
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        KLog.i(TAG,"bind service sesscee");
    }

    @Override
    protected void onRestart() {


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, IpcActivity.class);
        context.startActivity(intent);
    }
}
