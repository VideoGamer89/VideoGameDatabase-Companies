import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CompanyDetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.

        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            Company company = b.getParcelable("Company");
            CompanyDetailFragment fragment = new CompanyDetailFragment();
            fragment.setArguments(b);
            getFragmentManager().beginTransaction()
                    .add(R.id.company_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure.
            NavUtils.navigateUpTo(this, new Intent(this, CompanyListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class CompanyDetailFragment extends Fragment {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        public static final String ARG_ITEM_ID = "item_id";
        private Company mCompany;

        /**
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
        public CompanyDetailFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // If intent arguments have a Company object, get it

            mCompany = null;

            Bundle args = getArguments();
            if (args != null && args.containsKey("Company")) {
                mCompany = args.getParcelable("Company");
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate( R.layout.fragment_company_detail, container, false);

            // Show the selected Company or a welcome screen if nothing selected
            if (mCompany != null) {
                ((TextView) rootView.findViewById(R.id.company_detail)).setText(mCompany.getCompanyName()
                        + "\n" + mCompany.getCompanyCountry()+ "\n" + mCompany.getDateFounded() + "\n"
                        + "\n" + mCompany.getDateInVideoGames() + "\n" + "\n" + mCompany.getFate()
                        + "\n" + mCompany.getProduct());
            } else {
                ((TextView) rootView.findViewById(R.id.company_detail)).setText("Welcome to Companies");
            }

            return rootView;
        }
    }
}
