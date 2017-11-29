package com.padc.healthcaredirectory.views.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.padc.healthcaredirectory.R;

/**
 * Created by Phyoe Khant on 10/5/2016.
 */
public class ViewDialog {

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_about_us);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonClose);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}