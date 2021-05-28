package co.com.ceiba.mobile.pruebadeingreso.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.databinding.CustomDialogBinding;

public class LoaderDialog {
    private Activity activity;
    private AlertDialog dialog;

    public LoaderDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void showLoader() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }

    public void hideLoader() {
        dialog.dismiss();
    }
}
