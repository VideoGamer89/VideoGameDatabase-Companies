import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CompanySQLiteHelper extends SQLiteOpenHelper {
    // database information
    public static final String DATABASE_NAME = "companies.db";
    public static final int DATABASE_VERSION = 1;
    // companies table information
    public static final String TABLE_COMPANIES = "companies";
    public static final String COLUMN_COMPANY_ID = "_id";
    public static final String COLUMN_COMPANY_NAME = "company_name";
    public static final String COLUMN_COMPANY_COUNTRY = "company_country";
    public static final String COLUMN_COMPANY_DATE_FOUNDED = "company_date_founded";
    public static final String COLUMN_COMPANY_DATE_IN_VIDEO_GAMES = "company_date_in_video_games";
    public static final String COLUMN_COMPANY_FATE = "company_fate";
    public static final String COLUMN_COMPANY_PRODUCT = "company_product";
    // table create SQL statement
    private static final String DATABASE_CREATE = "create table " +
            TABLE_COMPANIES + " (" +
            COLUMN_COMPANY_ID + " integer primary key autoincrement, " +
            COLUMN_COMPANY_NAME + " text not null, " +
            COLUMN_COMPANY_COUNTRY + " text not null, " +
            COLUMN_COMPANY_DATE_FOUNDED + " integer, " +
            COLUMN_COMPANY_DATE_IN_VIDEO_GAMES + " integer, " +
            COLUMN_COMPANY_FATE + " text not null, " +
            COLUMN_COMPANY_PRODUCT + " text not null);";

    public CompanySQLiteHelper(Context context) {
        // create the database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE); // creates companies table
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(CompanySQLiteHelper.class.getName(),
                "Upgrading database from version " +
                        oldVersion + " to " + newVersion + " (destroys old data)");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIES);
        onCreate(database);
    }
}
