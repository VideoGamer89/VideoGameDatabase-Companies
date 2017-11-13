import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CompaniesModel {
    private SQLiteDatabase mCompanyDb; // database connection
    private CompanySQLiteHelper mCompanyDbHelper; // helper
    private static final String[] columns = {
            CompanySQLiteHelper.COLUMN_COMPANY_ID,
            CompanySQLiteHelper.COLUMN_COMPANY_NAME,
            CompanySQLiteHelper.COLUMN_COMPANY_COUNTRY,
            CompanySQLiteHelper.COLUMN_COMPANY_DATE_FOUNDED,
            CompanySQLiteHelper.COLUMN_COMPANY_DATE_IN_VIDEO_GAMES,
            CompanySQLiteHelper.COLUMN_COMPANY_FATE,
            CompanySQLiteHelper.COLUMN_COMPANY_PRODUCT
    };

    public CompaniesModel(Context context) {
        mCompanyDbHelper = new CompanySQLiteHelper(context);
    }

    public void open() {
        mCompanyDb = mCompanyDbHelper.getWritableDatabase();
    }

    public void close() {
        mCompanyDbHelper.close();
    }

    public Company createCompany(String companyName, String companyCountry,
                                 int companyDateFounded, int companyDateInVideoGames,
                                 String companyFate, String companyProduct) {
        ContentValues values = new ContentValues();
        values.put(CompanySQLiteHelper.COLUMN_COMPANY_NAME, companyName);
        values.put(CompanySQLiteHelper.COLUMN_COMPANY_COUNTRY, companyCountry);
        values.put(CompanySQLiteHelper.COLUMN_COMPANY_DATE_FOUNDED, companyDateFounded);
        values.put(CompanySQLiteHelper.COLUMN_COMPANY_DATE_IN_VIDEO_GAMES, companyDateInVideoGames);
        values.put(CompanySQLiteHelper.COLUMN_COMPANY_FATE, companyFate);
        values.put(CompanySQLiteHelper.COLUMN_COMPANY_PRODUCT, companyProduct);
        long insertCompanyId = mCompanyDb.insert(CompanySQLiteHelper.TABLE_COMPANIES, null, values);
        // record is inserted, get its data
        Cursor cursor = mCompanyDb.query(CompanySQLiteHelper.TABLE_COMPANIES, columns,
                CompanySQLiteHelper.COLUMN_COMPANY_ID + "=" + insertCompanyId,
                null, null, null, null);
        cursor.moveToFirst(); // first company returned
        Company company = cursorToCompany(cursor);
        cursor.close();
        return company;
    }

    public ArrayList<Company> getAllCompanies() {
        ArrayList<Company> companies = new ArrayList<Company>();
        Cursor cursor = mCompanyDb.query(CompanySQLiteHelper.TABLE_COMPANIES, columns,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Company company = cursorToCompany(cursor);
            companies.add(company); // add this record to the list of companies
            cursor.moveToNext();
        }
        return companies;
    }

    // converts the data at the cursor to a Company object
    private Company cursorToCompany(Cursor cursor) {
        Company company = new Company();
        company.setCompanyId(cursor.getLong(0)); // id is in field 0
        company.setCompanyName(cursor.getString(1));
        company.setCompanyCountry(cursor.getString(2));
        company.setDateFounded(cursor.getInt(3));
        company.setDateInVideoGames(cursor.getInt(4));
        company.setFate(cursor.getString(5));
        company.setProduct(cursor.getString(6));
        return company;
    }

    // delete a company with this id
    public int deleteCompany (long deleteCompanyId) {
        Cursor cursor = mCompanyDb.query(CompanySQLiteHelper.TABLE_COMPANIES, columns,
                CompanySQLiteHelper.COLUMN_COMPANY_ID + "=" + deleteCompanyId,
                null, null, null, null);
        if (cursor.getCount() == 1) { // found this company
            mCompanyDb.delete(CompanySQLiteHelper.TABLE_COMPANIES, CompanySQLiteHelper.COLUMN_COMPANY_ID +
                    "=" + deleteCompanyId, null);
            return 1;
        }
        return 0;
    }
}
