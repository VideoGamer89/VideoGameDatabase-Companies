import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class EditCompanyDialog extends DialogFragment {
    private EditText mCompanyName;
    private EditText mCompanyCountry;
    private EditText mDateFounded;
    private EditText mDateInVideoGames;
    private EditText mFate;
    private EditText mProduct;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View editCompanyView = inflater.inflate(R.layout.fragment_edit_company, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setTitle(R.string.edit_company)
                .setView(editCompanyView)
                .setPositiveButton(R.string.label_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        View v = editCompanyView;
                        EditText mCompanyName = (EditText) v.findViewById(R.id.editCompanyName);
                        EditText mCompanyCountry = (EditText) v.findViewById(R.id.editCompanyCountry);
                        EditText mDateFounded = (EditText) v.findViewById(R.id.editDateFounded);
                        EditText mDateInVideoGames = (EditText) v.findViewById(R.id.editDateInVideoGames);
                        EditText mFate = (EditText) v.findViewById(R.id.editFate);
                        EditText mProduct = (EditText) v.findViewById(R.id.editProduct);

                        String name = mCompanyName.getText().toString();
                        String country = mCompanyCountry.getText().toString();
                        int dateFounded = Integer.parseInt(mDateFounded.getText().toString());
                        int dateInVideoGames = Integer.parseInt(mDateInVideoGames.getText().toString());
                        String fate = mFate.getText().toString();
                        String product = mProduct.getText().toString();
                        mListener.onDialogPositiveClick(name, country, dateFounded,
                                dateInVideoGames, fate, product);
                    }
                })
                .setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditCompanyDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /* The activity that create an instance of this dialog fragment must
   * implement this interface in order to receive event callbacks.
   * Each method passes the DialogFragment in case the host needs to query it. */
    public interface CompanyDialogListener{
        void onDialogPositiveClick(String name, String country,
                                   int dateFounded, int dateInVideoGames,
                                   String fate, String product);
    }
    // Use this instance of the interface to deliver action events
    CompanyDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (CompanyDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement CompanyDialogListener");
        }
    }
}
