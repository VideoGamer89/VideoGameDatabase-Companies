import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ConfirmCompanyDeleteDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.label_confirm_delete)
                .setPositiveButton(R.string.label_OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onConfirmCompanyDeleteDialogPositiveClick();
                    }
                })
                .setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onConfirmCompanyDeleteDialogNegativeClick();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface ConfirmCompanyDeleteDialogListener {
        public void onConfirmCompanyDeleteDialogPositiveClick();
        public void onConfirmCompanyDeleteDialogNegativeClick();
    }

    // Use this instance of the interface to deliver action events
    ConfirmCompanyDeleteDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ConfirmCompanyDeleteDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ConfirmCompanyDeleteDialogListener");
        }
    }
}
