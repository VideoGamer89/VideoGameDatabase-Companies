import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CompanyListFragment extends ListFragment {
    private ArrayList<Company> mCompanies;
    public CompanyListAdapter mAdapter;
    private CompanyListFragmentCallback mCallback;

    public static final String KEY_COMPANIES_LIST = "companies";

    public interface CompanyListFragmentCallback {
        public void onItemSelected(Company c);
        public ArrayList<Company> getCompanies();
        public void deleteCompanyFromDB (long deleteId);
    }

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String COMPANY_ACTIVATED_POSITION = "activated_position";
    // The current activated item position. Only used on tablets.
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CompanyListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Get Company array from argument
        if (getArguments() != null && getArguments().containsKey(KEY_COMPANIES_LIST)) {
            mCompanies = getArguments().getParcelableArrayList("Companies");
        }
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mCallback = (CompanyListFragmentCallback) getActivity();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement onItemSelected");
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_listview, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new CompanyListAdapter(); // the list adapter
        if(savedInstanceState != null) {
            // read the person list from the saved company
            mCompanies = savedInstanceState.getParcelableArrayList(KEY_COMPANIES_LIST);
        } else {
            // load the person list
            mCompanies = mCallback.getCompanies();
        }
        setListAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_COMPANIES_LIST, mCompanies);
        super.onSaveInstanceState(outState);
    }

    public class CompanyListAdapter extends ArrayAdapter<Company> {
        private ArrayList<Long> deleteTheseDbIds = null;
        private ArrayList<Company> removeTheseCompanies = null;

        public CompanyListAdapter() {
            super(getActivity(), R.layout.fragment_company_listview, mCompanies);
        }

        // a view of what's in each item in the list
        private class ViewHolder {
            TextView name;
            CheckBox selected;
        }

        @Override
        public int getCount() {
            return mCompanies.size();
        }

        @Override
        public Company getItem(int pos) {
            return mCompanies.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return mCompanies.get(pos).getCompanyId();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {      // reusing an older view to save space
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_company, null);
                holder = new ViewHolder();
                // get the items that are in the list
                holder.name = (TextView) convertView.findViewById(R.id.list_item_string_company);
                holder.selected = (CheckBox) convertView.findViewById(R.id.list_item_cb_company);
                convertView.setTag(holder); // an "ID" for this line of the view

                // set what happens when the holder selected cb is clicked
                holder.selected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View checkBoxView) {
                        CheckBox cb = (CheckBox) checkBoxView;
                        Company company = (Company) cb.getTag(); // what company is in this line?
                        company.setSelected(cb.isChecked());
                    }
                });

                // set what happens when the holder selected company's name is clicked
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View nameView) {
                        TextView nameItem = (TextView) nameView;
                        Company company = (Company) nameItem.getTag(); // what company is in this line?
                        mCallback.onItemSelected(company);
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Company company = mCompanies.get(position);
            holder.name.setText(company.getCompanyName());
            holder.name.setTag(company);
            holder.selected.setChecked(company.isSelected());
            holder.selected.setTag(company);

            return convertView;
        }

        public void deleteSelectedItems(FragmentManager fm) {
            deleteTheseDbIds = new ArrayList<Long>();
            removeTheseCompanies = new ArrayList<>();
            // step through the list of items and see if they got selected
            for (int i = 0; i < mCompanies.size(); i++) {
                Company company = mCompanies.get(i);
                if (company.isSelected()) {
                    deleteTheseDbIds.add(company.getCompanyId());
                    removeTheseCompanies.add(company);
                    company.setSelected(false); // turn off the checkbox!
                }
            }

            // see if there are any items to be deleted, then confirm the delete
            if (removeTheseCompanies.size() != 0) {
                ConfirmCompanyDeleteDialog confirmCompanyDeleteDialog = new ConfirmCompanyDeleteDialog();
                confirmCompanyDeleteDialog.show(fm, "ConfirmCompanyDeleteDialog");
                // if OK to delete then deleteProceed will be called
            }
        }

        private void clearAllSelected() {
            for (int i = 0; i < mCompanies.size(); i++) {
                Company company = mCompanies.get(i);
                company.setSelected(false); // turn off the checkbox!
            }
            notifyDataSetChanged();
        }

        public void deleteProceed() {
            clearAllSelected();
            if (removeTheseCompanies != null && deleteTheseDbIds != null) {
                mCompanies.removeAll(removeTheseCompanies);
                // step through list of DB ids to delete
                for (long deleteId : deleteTheseDbIds) {
                    mCallback.deleteCompanyFromDB(deleteId);
                }
                notifyDataSetChanged();
            }
        }

        public void deleteCancel() {
            clearAllSelected();
            notifyDataSetChanged();
        }
    }
}
