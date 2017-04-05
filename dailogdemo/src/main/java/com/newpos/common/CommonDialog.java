package com.newpos.common;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.newpos.R;

/**
 * Created by 张玉栋 on 2017/4/4.
 */

public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context, R.style.dialog);
    }

    public interface onOkListener{
        boolean onOK(CommonDialog dlg);
    }
    /**
     * Called when Cancel button be pressed.
     * Dismiss the dialog is the default action, no need to write in onCancel().
     */
    public interface onCancelListener{
        void onCancel(CommonDialog dlg);
    }

    public onOkListener okListener;
    public onCancelListener cancelListener;


    public void setTitle(String title){
        TextView view = (TextView) findViewById(R.id.titile);
        if(title != null){
            view.setText(title);
        }
    }

    public void setOkBut(String msg, onOkListener listener){
       Button ok = (Button) findViewById(R.id.ok);
        ok.setText(msg);
        this.okListener = listener;
    }

    public void setCancle(String msg, onCancelListener listener){
        Button cancle = (Button) findViewById(R.id.cancle);
        cancle.setText(msg);
        cancle.setVisibility(View.GONE);
        this.cancelListener = listener;
    }

    public static CommonDialog build(Context context){
        final CommonDialog dialog = new CommonDialog(context);
        dialog.setContentView(R.layout.dialog_item);
        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog.okListener != null){
                    dialog.okListener.onOK(dialog);
                }
                dialog.dismiss();
            }
        });
        Button cancle = (Button) dialog.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog.cancelListener != null){
                    dialog.cancelListener.onCancel(dialog);
                }
                dialog.cancel();
            }
        });
        return dialog;
    }
}
