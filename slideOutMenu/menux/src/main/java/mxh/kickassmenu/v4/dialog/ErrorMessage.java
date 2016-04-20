package mxh.kickassmenu.v4.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import mxh.kickassmenu.R;


/**
 * Created by hesk on 25/8/15.
 */
public class ErrorMessage extends DialogFragment {
    protected static Runnable onclickrun;

    public static void alert(final String message, FragmentManager manager) {
        try {
            ErrorMessage.message(message).show(manager, "errorMessageOnce");
        } catch (IllegalStateException e) {
            Log.d("dialog", "illegal state exception");
        } catch (NullPointerException e) {
            Log.d("dialog", "NullPointerException exception");
        } catch (Exception e) {
            Log.d("dialog", "exception" + e.getMessage());
        }

    }

    public static void alert(final String message, FragmentManager manager, Runnable onclickrun) {
        try {
            ErrorMessage.onclickrun = onclickrun;
            ErrorMessage.message(message).show(manager, "errorMessageOnce");
        } catch (IllegalStateException e) {
            Log.d("dialog", "illegal state exception");
        } catch (NullPointerException e) {
            Log.d("dialog", "NullPointerException exception");
        } catch (Exception e) {
            Log.d("dialog", "exception" + e.getMessage());
        }
    }

    public static ErrorMessage message(final String mes) {
        Bundle h = new Bundle();
        h.putString("message", mes);
        ErrorMessage e = new ErrorMessage();
        e.setArguments(h);
        return e;
    }

    public static Bundle getMessageBundle(final String mes) {
        Bundle h = new Bundle();
        h.putString("message", mes);
        return h;
    }

    protected void onNoticedErrorMessage(DialogInterface dialog, int id, String original_message) {
    }

    /**
     * the main message in the dialog box
     *
     * @return string
     */
    protected String button_message() {
        return getString(R.string.try_again);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getArguments().getString("message"))
                .setNeutralButton(button_message(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onNoticedErrorMessage(dialog, id, getArguments().getString("message"));
                        dialog.dismiss();
                        if (onclickrun != null) {
                            onclickrun.run();
                            onclickrun = null;
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
