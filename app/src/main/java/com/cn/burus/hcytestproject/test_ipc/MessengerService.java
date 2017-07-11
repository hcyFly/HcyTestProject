package com.cn.burus.hcytestproject.test_ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.socks.library.KLog;

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT:
                    //接收客户端发来的信息
                    KLog.i(TAG,"客户端发来的信息:"+msg.getData().getString("msg"));
                    Messenger relpyClient = msg.replyTo;
                    Message relpyMessage = Message.obtain(null,MyConstants.MSG_FROM_SERVICE);
                    Bundle sbundle = new Bundle();
                    sbundle.putString("reply","你的消息收到，稍后回复你！");
                    relpyMessage.setData(sbundle);
                    try {
                        relpyClient.send(relpyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    break;
                default:
            }


            super.handleMessage(msg);
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();

    }
}
