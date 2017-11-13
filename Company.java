import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
    private long mCompanyId;
    private String mCompanyName;
    private String mCompanyCountry;
    private int mDateFounded;
    private int mDateInVideoGames;
    private String mFate;
    private String mProduct;
    private boolean mSelected; // set when check in list is set

    public static final String KEY_COMPANY_NAME = "name", KEY_COMPANY_COUNTRY = "country",
            KEY_COMPANY_DATE_FOUNDED = "company_date_founded",
            KEY_COMPANY_DATE_IN_VIDEO_GAMES = "company_date_in_video_games",
            KEY_FATE = "fate", KEY_PRODUCT = "product",
            KEY_COMPANY_ID = "id", KEY_SELECTED = "selected";

    public Company() { }

    public Company(String companyName, String companyCountry, int dateFounded,
                   int dateInVideoGames, String fate, String product) {
        mCompanyName = companyName;
        mCompanyCountry = companyCountry;
        mDateFounded = dateFounded;
        mDateInVideoGames = dateInVideoGames;
        mFate = fate;
        mProduct = product;
        mSelected = false;
    }

    public long getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(long companyId) {
        mCompanyId = companyId;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCompanyCountry() {
        return mCompanyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        mCompanyCountry = companyCountry;
    }

    public int getDateFounded() {
        return mDateFounded;
    }

    public void setDateFounded(int dateFounded) {
        mDateFounded = dateFounded;
    }

    public int getDateInVideoGames() {
        return mDateInVideoGames;
    }

    public void setDateInVideoGames(int dateInVideoGames) {
        mDateInVideoGames = dateInVideoGames;
    }

    public String getFate() {
        return mFate;
    }

    public void setFate(String fate) {
        mFate = fate;
    }

    public String getProduct() {
        return mProduct;
    }

    public void setProduct(String product) {
        mProduct = product;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    @Override
    public String toString() {
        return "Company{" +
                "mCompanyName='" + mCompanyName + '\'' +
                ", mCompanyCountry='" + mCompanyCountry + '\'' +
                ", mDateFounded='" + mDateFounded + '\'' +
                ", mDateInVideoGames='" + mDateInVideoGames + '\'' +
                ", mFate='" + mFate + '\'' +
                ", mProduct='" + mProduct + '\'' +
                '}';
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    private Company(Parcel in) {
        mCompanyName = in.readString();
        mCompanyCountry = in.readString();
        mDateFounded = in.readInt();
        mDateInVideoGames = in.readInt();
        mFate = in.readString();
        mProduct = in.readString();
        mCompanyId = in.readLong();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // create a bundle for the key value pairs
        Bundle bundle = new Bundle();
        // insert the key value pairs to the bundle
        bundle.putString(KEY_COMPANY_NAME, mCompanyName);
        bundle.putString(KEY_COMPANY_COUNTRY, mCompanyCountry);
        bundle.putInt(KEY_COMPANY_DATE_FOUNDED, mDateFounded);
        bundle.putInt(KEY_COMPANY_DATE_IN_VIDEO_GAMES, mDateInVideoGames);
        bundle.putString(KEY_FATE, mFate);
        bundle.putString(KEY_PRODUCT, mProduct);
        bundle.putLong(KEY_COMPANY_ID, mCompanyId);
        bundle.putBoolean(KEY_SELECTED, mSelected);
        // write the key value pairs to the parcel
        dest.writeBundle(bundle);
    }

    /**
     * Creator required for class implementing the parcelable interface.
     */
    public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {

        @Override
        public  Company createFromParcel(Parcel source) {
            // read the bundle containing key value pairs from the parcel
            Bundle bundle = source.readBundle();

            // Instantiate a person using values from the bundle
            // return new Company(source);
            return new Company(bundle.getString(KEY_COMPANY_NAME),
                    bundle.getString(KEY_COMPANY_COUNTRY), bundle.getInt(KEY_COMPANY_DATE_FOUNDED),
                    bundle.getInt(KEY_COMPANY_DATE_IN_VIDEO_GAMES), bundle.getString(KEY_FATE),
                    bundle.getString(KEY_PRODUCT));
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}
