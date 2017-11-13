import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class CompanyListActivity extends ActionBarActivity
    implements EditCompanyDialog.CompanyDialogListener,
    ConfirmCompanyDeleteDialog.ConfirmCompanyDeleteDialogListener,
    CompanyListFragment.CompanyListFragmentCallback {
        private CompaniesModel mCompaniesModel;
        private ListView mListView;
        ArrayList<Company> mCompanies;
        CompanyListFragment cf;
        private static final String LIST_FRAGMENT = "list_fragment";

        private boolean mTwoPane;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FragmentManager fm = getFragmentManager();
            setContentView(R.layout.activity_company_list);

            mCompaniesModel = new CompaniesModel(this); // create db and tables
            mCompaniesModel.open();
            if (mCompaniesModel.getAllCompanies().size() == 0) { // db is empty
                mCompaniesModel.createCompany("Nintendo", "Japan", 1889,
                        1974, "still active", "Mario series");
                mCompaniesModel.createCompany("Sega", "Japan", 1940, 1960,
                        "still active", "Sonic the Hedgehog series");
                mCompaniesModel.createCompany("Warner Brothers Interactive Entertainment", "USA",
                        2004, 2004, "still active", "Batman: Arkham series");
                mCompaniesModel.createCompany("Ubisoft", "France", 1986, 1986,
                        "still active", "Assassin's Creed series");
                mCompaniesModel.createCompany("Activision", "USA", 1979, 1979, "still active",
                        "Call of Duty series");
                mCompaniesModel.createCompany("Microsoft Studios", "USA", 2002, 2002,
                        "still active", "Banjo-Kazooie series");
                mCompaniesModel.createCompany("Sledgehammer Games", "USA", 2009,
                        2009, "still active", "Call of Duty Advanced Warfare");
                mCompaniesModel.createCompany("Beenox", "Canada", 2000, 2000,
                        "still active", "Spider-man: Shattered Dimensions");
                mCompaniesModel.createCompany("Marvel Entertainment", "USA", 1998, 1998,
                        "still active", "Film (specifically Captain America: The Winter Soldier");
                mCompaniesModel.createCompany("Frontier Developments", "England", 1994, 1994,
                        "still active", "Thrillville series");
                mCompaniesModel.createCompany("Raven Software", "USA", 1990,
                        1990, "still active", "Wolfenstein series");
                mCompaniesModel.createCompany("Neversoft", "USA", 1994, 1994,
                        "merged into Infinity Ward", "Tony Hawk's Pro Skater series");
                mCompaniesModel.createCompany("Iron Galaxy Studios", "USA", 2008,
                        2008, "still active", "Killer Instinct Season 2");
                mCompaniesModel.createCompany("Double Helix Games", "USA", 2007,
                        2007, "still active", "Killer Instinct Season 1");
                mCompaniesModel.createCompany("Capcom", "Japan", 1979, 1983,
                        "still active", "Street Fighter series");
                mCompaniesModel.createCompany("Ninja Theory", "England", 2000,
                        2000, "still active", "Heavenly Sword");
                mCompaniesModel.createCompany("Gearbox Software", "USA", 1999,
                        1999, "still active", "Aliens: Colonial Marines");
                mCompaniesModel.createCompany("Atari, inc", "USA", 2003, 2003,
                        "Chapter 11 of the U.S. Bankruptcy Code", "Roller Coaster Tycoon series");
                mCompaniesModel.createCompany("Square Enix Holdings Co., Ltd", "Japan,", 2003, 2003,
                        "still active", "Final Fantasy series");
                mCompaniesModel.createCompany("EA (Electronic Arts)", "USA", 1982, 1982,
                        "still active", "Battlefield series");
                mCompaniesModel.createCompany("Epic Games", "USA", 1991, 1991, "still active",
                        "Epic Pinball");
                mCompaniesModel.createCompany("Epic Games Poland (formally People Can Fly)", "Poland",
                        2002, 2002, "still active", "Bullet Storm");
                mCompaniesModel.createCompany("Sumo Digital Ltd", "United Kingdom", 2003, 2003,
                        "still active", "Sonic & All-Stars Racing Transformed");
                mCompaniesModel.createCompany("Sonic Team Corp.", "Japan", 1990, 1990,
                        "still active", "Sonic Unleashed");
                mCompaniesModel.createCompany("SoMa Play Inc.", "USA", 2013, 2013, "still active",
                        "Tetris Ultimate");
                mCompaniesModel.createCompany("Hudson Soft Co., Ltd", "Japan", 1973,
                        1980, "Bought out by Konami Digital Entertainment", "Tetris Axis");
                mCompaniesModel.createCompany("Konami Corporation", "Japan", 1969, 1980,
                        "still active", "Dance Dance Revolution series");
                mCompaniesModel.createCompany("HAL Laboratory, Inc.", "Japan", 1980, 1980,
                        "still active", "Kirby series");
                mCompaniesModel.createCompany("Namco Bandai Games Inc.", "Japan", 1955, 1980,
                        "still active", "Tekken series");
                mCompaniesModel.createCompany("Sora ltd", "Japan", 2005, 2005, "still active",
                        "Super Smash Bros. Brawl");
                mCompaniesModel.createCompany("Infinity Ward", "USA", 2002, 2002, "still active",
                        "Call of Duty series");
                mCompaniesModel.createCompany("Rare Ltd.", "England", 1985, 1985, "still active",
                        "Killer Instinct series");
                mCompaniesModel.createCompany("Rovio Entertainment Limited", "Finland", 2003, 2003,
                        "still active", "Angry Birds series");
                mCompaniesModel.createCompany("Robomodo", "USA", 2008, 2008, "still active",
                        "Tony Hawk: Ride");
                mCompaniesModel.createCompany("PopCap Games Inc.", "USA", 2008, 2008,
                        "still active", "Bejeweled series");
                mCompaniesModel.createCompany("Silicon Knights", "Canada", 1992, 1992,
                        "Bankruptcy", "X-men Destiny");
                mCompaniesModel.createCompany("Treyarch", "USA", 1996, 1996, "still active",
                        "Call of Duty series");
                mCompaniesModel.createCompany("Shaba Games", "USA", 1997, 1997, "Bankruptcy",
                        "Spider-Man: Web of Shadows");
                mCompaniesModel.createCompany("Bizarre Creations", "United Kingdom", 1988, 1988,
                        "Acquired then dissolved", "Blur");
                mCompaniesModel.createCompany("Radical Entertainment", "Canada", 1991, 1991,
                        "Bankruptcy", "Prototype series");
                mCompaniesModel.createCompany("NetherRealm studios", "USA", 2010, 2010,
                        "still active", "Injustice: Gods Among Us");
                mCompaniesModel.createCompany("High Moon Studios", "USA", 2001, 2001,
                        "still active", "Deadpool");
                mCompaniesModel.createCompany("RedLynx Ltd.", "Finland", 2000, 2000, "still active",
                        "Trials series");
                mCompaniesModel.createCompany("LucasArts Entertainment Company, LLC", "USA", 1982,
                        1982, "Bought out by the Walt Disney Company", "Thrillvill series");
                mCompaniesModel.createCompany("Aksys Games Localization", "USA", 2006, 2006,
                        "still active", "BlazBlue series");
                mCompaniesModel.createCompany("Arc System Works", "Japan", 1988, 1988,
                        "still active", "Persona 4 Arena");
                mCompaniesModel.createCompany("Atlus Co., Ltd", "Japan", 1986, 1986,
                        "still active", "Persona series");
                mCompaniesModel.createCompany("Next Level Games Inc.", "Canada", 2002, 2002,
                        "still active", "Mario Strikers Charged");
                mCompaniesModel.createCompany("Foxconn (Han Hai Precision Industry Co., Ltd)",
                        "Taiwan", 1974, 0, "still active", "Electronics");
                mCompaniesModel.createCompany("20th Century Fox Games", "USA", 1994, 1994,
                        "still active", "Angry Birds Trilogy");
                mCompaniesModel.createCompany("Vicarious Visions", "USA", 1990, 1990,
                        "still active", "Guitar Hero: On Tour series");
                mCompaniesModel.createCompany("RedOctane", "USA", 1999, 1999, "Closed",
                        "Guitar Hero series");
                mCompaniesModel.createCompany("N-space", "USA", 1994, 1994, "still active",
                        "Call of Duty: Modern Warfare: Mobilized");
                mCompaniesModel.createCompany("DC Comics", "USA", 1934, 1980, "still active",
                        "Batman series");
                mCompaniesModel.createCompany("Retro Studios", "USA", 1998, 1998, "still active",
                        "Mario Kart 7");
                mCompaniesModel.createCompany("Rocksteady Studios", "United Kingdom", 2004, 2004,
                        "still active", "Batman: Arkham series");
                mCompaniesModel.createCompany("Platinum Games Inc.", "Japan", 2006, 2006,
                        "still active", "The Wonderful 101");
                mCompaniesModel.createCompany("Freestyle Games", "United Kingdom", 2002, 2002,
                        "still active", "Sing Party");
                mCompaniesModel.createCompany("Team Ninja", "Japan", 1995, 1995, "still active",
                        "Ninja Gaiden series");
                mCompaniesModel.createCompany("Tecmo", "Japan", 1967, 1980, "Merged with Koei",
                        "Ninja Gaiden series");
                mCompaniesModel.createCompany("Koei", "Japan", 1978, 1980, "Merged with with Tecmo",
                        "Samurai Warriors series");
                mCompaniesModel.createCompany("Treasure Co., Ltd", "Japan", 1992, 1992,
                        "still active", "Sin and Punishment: Star Successor");
                mCompaniesModel.createCompany("High Voltage Software", "USA", 1993, 1993,
                        "still active", "Tournament of Legends");
                mCompaniesModel.createCompany("Monster Games", "USA", 1996, 1996, "still active",
                        "Excite Truck");
                mCompaniesModel.createCompany("Good-Feel Co., Ltd", "Japan", 2005, 2005,
                        "still active", "Kirby's Epic Yarn");
                mCompaniesModel.createCompany("Midway", "USA", 1988, 1988, "Bankruptcy",
                        "Rush series");
                mCompaniesModel.createCompany("Toys for Bob", "USA", 1989, 1989, "still active",
                        "Skylanders series");
                mCompaniesModel.createCompany("Harmonix Music Systems", "USA", 1995, 1995,
                        "still active", "Guitar Hero series");
                mCompaniesModel.createCompany("EA Sports", "Canada", 1991, 1991, "still active",
                        "Madden NFL series");
                mCompaniesModel.createCompany("Acronym Games", "Canada", 2005, 2005,
                        "still active", "Family Guy Online");
                mCompaniesModel.createCompany("Armature Studios", "USA", 2008, 2008, "still active",
                        "The Unfinished Swan");
                mCompaniesModel.createCompany("Dimps Corporation", "Japan", 2000, 2000,
                        "still active", "Street Fighter series");
                mCompaniesModel.createCompany("Project Sora", "Japan", 2009, 2009, "Bankruptcy",
                        "Kid Icarus Uprising");
                mCompaniesModel.createCompany("Ubisoft Shanghai", "China", 1996, 1996,
                        "still active", "Trials Fusion");
                mCompaniesModel.createCompany("Ubisoft Kyiv", "Ukraine", 2008, 2008, "still active",
                        "Assassin's Creed 2");
                mCompaniesModel.createCompany("Arika", "Japan", 1995, 1995, "still active",
                        "Bust-a-Move series");
                mCompaniesModel.createCompany("Nintendo EAD " +
                                "(Entertainment Analysis and Development)",
                        "Japan", 1983, 1983, "still active", "Mario series");
                mCompaniesModel.createCompany("Nintendo EAD " +
                                "(Entertainment Analysis and Development) Tokyo", "Japan", 2002,
                        2002, "still active", "Mario series");
                mCompaniesModel.createCompany("1-UP Studio Co., Ltd", "Japan", 2000, 2000,
                        "still active", "Super Mario series");
                mCompaniesModel.createCompany("Monolith Soft Inc.", "Japan", 1999, 1999,
                        "still active", "Baten Kaitos series");
                mCompaniesModel.createCompany("Game Arts Co., Ltd", "Japan", 1985, 1985,
                        "still active", "Super Smash Bros Brawl");
                mCompaniesModel.createCompany("D-Rockets", "Japan", 2008, 2008, "still active",
                        "Metroid: Other M");
                mCompaniesModel.createCompany("Warner Bros. Games Montreal", "Canada", 2010, 2010,
                        "still active", "Batman: Arkham Origins");
                mCompaniesModel.createCompany("Certain Affinity", "USA", 2006, 2006, "still active",
                        "Call of Duty Ghosts");
                mCompaniesModel.createCompany("Pipeworks Software", "USA", 1999, 1999,
                        "still active", "Rampage Total Destruction");
                mCompaniesModel.createCompany("The Creative Assembly", "United Kingdom", 1987, 1987,
                        "still active", "Total War series");
                mCompaniesModel.createCompany("Q Entertainment", "Japan", 2003, 2003, "still active",
                        "Meteos");
                mCompaniesModel.createCompany("Eighting", "Japan", 1993, 1993, "still active",
                        "Marvel vs. Capcom 3: Fate of Two Worlds");
                mCompaniesModel.createCompany("Ubisoft Montreal", "Canada", 1997, 1997,
                        "still active", "Assassin's Creed series");
                mCompaniesModel.createCompany("Ubisoft Massive", "Sweden", 1997, 1997,
                        "still active", "Assassin's Creed");
                mCompaniesModel.createCompany("Ubisoft Annecy", "France", 1996, 1996, "still active",
                        "Assassin's Creed series");
                mCompaniesModel.createCompany("Ubisoft Singapore", "Singapore", 1986, 1986,
                        "still active", "Assassin's Creed series");
                mCompaniesModel.createCompany("Ubisoft Romania", "Romania", 1992, 1992, "still active",
                        "Assassin's Creed series");
                mCompaniesModel.createCompany("TimeGate Studios", "USA", 2008, 2008, "bankruptcy",
                        "Section 8 series");
                mCompaniesModel.createCompany("FatShark", "Sweden", 2008, 2008, "still active",
                        "War of the Roses");
                mCompaniesModel.createCompany("Backbone Entertainment", "USA", 1992, 1992,
                        "still active", "Sonic's Ultimate Genesis Collection");
                mCompaniesModel.createCompany("Housemarque", "Finland", 1995, 1995, "still active",
                        "Super Stardust");
                mCompaniesModel.createCompany("Nintendo SPD (Software Planning and Development",
                        "Japan", 2003, 2003, "still active", "Mario series");
                mCompaniesModel.createCompany("Activision Blizzard Inc.", "USA", 2008, 2008,
                        "still active", "Call of Duty series");
                mCompaniesModel.createCompany("EA Canada", "Canada", 1983, 1983, "still active",
                        "SSX series");
                mCompaniesModel.createCompany("Now Production", "Japan", 1986, 1986, "still active",
                        "Beautiful Katamari");
                mCompaniesModel.createCompany("Ubisoft Quebec", "Canada", 2005, 2005, "still active",
                        "Prince of Persia: The Forgotten Sands");
                mCompaniesModel.createCompany("Omega Force", "Japan", 2009, 2009, "still active",
                        "Dynasty Warriors series");
                mCompaniesModel.createCompany("Koei Tecmo Holdings Co., Ltd", "Japan", 2009, 2009,
                        "still active", "Dynasty Warriors series");
                mCompaniesModel.createCompany("Konami Digital Entertainment", "Japan", 2006, 2006,
                        "still active", "Dance Dance Revolution series");
                mCompaniesModel.createCompany("Creatures Inc.", "Japan", 1995, 1995, "still active",
                        "Pokemon series");
                mCompaniesModel.createCompany("United Games Artists", "Japan", 2000, 2000,
                        "Merged with Sonic Team Japan", "Space Channel 5");
                mCompaniesModel.createCompany("Nintendo IRD (Integrated Research and Development)",
                        "Japan", 1975, 0, "still active", "Nintendo video game consoles");
                mCompaniesModel.createCompany("NTD (Nintendo Technology Development)", "USA",
                        1996, 0, "still active", "Nintendo video game console");
                mCompaniesModel.createCompany("Mitsumi", "Japan", 1954, 0, "still active", "consumer " +
                        "electronics");
                mCompaniesModel.createCompany("Flextronics", "USA", 1969, 0, "still active", "electronic " +
                        "manufacturing");
                mCompaniesModel.createCompany("Microsoft", "USA", 1975, 0, "still active",
                        "XBox consoles");
                mCompaniesModel.createCompany("Nintendo RED (Research and Engineering Department)",
                        "Japan", 2003, 0, "still active", "Nintendo hand held game consoles");
                mCompaniesModel.createCompany("Wistron Corporation", "Taiwan", 2001, 0,
                        "still active", "Electronics");
                mCompaniesModel.createCompany("Celestica", "Canada", 1994, 0, "still active",
                        "Electronics");
                mCompaniesModel.createCompany("Big Red Button Entertainment", "USA", 2009, 2009,
                        "still active", "Sonic Boom: Rise of Lyric");
                mCompaniesModel.createCompany("IllFonic", "USA", 2007, 2007, "still active",
                        "Sonic Boom: Rise of Lyric");
                mCompaniesModel.createCompany("Sanzaru Games", "USA", 2007, 2007, "still active",
                        "God of War Collection");
                mCompaniesModel.createCompany("Traveller's Tales", "England", 1989, 1990,
                        "still active", "Lego Batman: Series");
                mCompaniesModel.createCompany("GungHo Online Entertainment", "Japan", 1998, 1998,
                        "still active", "Puzzle and Dragons series");
                mCompaniesModel.createCompany("Bethsda Softworks, LLC", "USA", 1986, 1986,
                        "still active", "Wolfenstein: The New Order");
                mCompaniesModel.createCompany("MachineGames", "Sweden", 2009, 2009,
                        "still active", "Wolfenstein: The New Order");
                mCompaniesModel.createCompany("EA Digital Illusions Creative Entertainment a.k.a." +
                        " EA DICE or DICE", "Sweden", 1992, 1992, "still active",
                        "Battlefield series");
                mCompaniesModel.createCompany("Visceral Games", "USA", 1998, 1998, "still active",
                        "Dead Space series");
                mCompaniesModel.createCompany("Criterion Games", "United Kingdom", 1993, 1993,
                        "still active", "Burnout series");
                mCompaniesModel.createCompany("343 Industries", "USA", 2009, 2009, "still active",
                        "Halo 5: Guardians");
                mCompaniesModel.createCompany("Bungie, Inc.", "USA", 1991, 1991, "still active",
                        "Destiny");
                mCompaniesModel.createCompany("Disruptive Games", "USA", 2008, 2008, "still active",
                        "Tony Hawk's Pro Skater 5");
                mCompaniesModel.createCompany("Ultimate Play The Game", "United Kingdom", 1982,
                        1982,"evolved into Rare", "Sabreman series");
                mCompaniesModel.createCompany("THQ", "USA", 1989, 1989, "formerly bankruptcy, now " +
                        "In-name-only unit of Nordic Games", "WWE series");
                mCompaniesModel.createCompany("Acclaim Entertainment", "USA", 1987, 1987,
                        "Bankruptcy", "Aggressive Inline");
                mCompaniesModel.createCompany("Underground Development", "USA", 1994, 1994,
                        "closed", "Dave Mirra Freestyle BMX 2");
                mCompaniesModel.createCompany("The Coalition", "Canada", 2010, 2010,
                        "still active", "Gears of War 4");
                mCompaniesModel.createCompany("Zen Studios", "Hungary", 2003, 2003,
                        "still active", "Marvel Pinball");
                mCompaniesModel.createCompany("Virtuos", "China", 2004, 2004,
                        "still active", "Batman: Return to Arkham");
                mCompaniesModel.createCompany("Deep Silver", "Germany", 2002, 2002,
                        "still active", "Mighty No. 9");
                mCompaniesModel.createCompany("FarSight Studios", "USA", 1988, 1988,
                        "still active", "Stern Pinball Arcade");
                mCompaniesModel.createCompany("Alliance Digital Media", "USA", 2013, 2013,
                        "still active", "Brunswich Pro Bowling");
                mCompaniesModel.createCompany("Vicious Cycle Software", "USA", 2000, 2000,
                        "still active", "Adventure Time: Finn & Jake Investigations");
                mCompaniesModel.createCompany("Little Orbit", "USA", 2010, 2010, "still active",
                        "Adventure Time: Finn & Jake Investigations");
                mCompaniesModel.createCompany("Telltale Incorporated", "USA", 2004, 2004,
                        "still active", "Batman: The Telltale Series");
                mCompaniesModel.createCompany("Jackbox Games, Inc.", "USA", 1993, 1993,
                        "still active", "You Don't Know Jack series");
                mCompaniesModel.createCompany("Berkeley Systems", "USA", 1987, 1987,
                        "Acquired then dissolved", "You Don't Know Jack series");
                mCompaniesModel.createCompany("Webfoot Technologies", "USA", 1993, 1993,
                        "still active", "Hello Kitty: Happy Party Pals");
                mCompaniesModel.createCompany("Majesco Entertainment", "USA", 1986, 1986,
                        "Dropped out of entertainment industry", "Advent Rising");
                mCompaniesModel.createCompany("TopWare Interactive", "Germany", 1995, 1995,
                        "still active", "Dream Pinball");
                mCompaniesModel.createCompany("Gaijin Entertainment", "Russia", 2002, 2002,
                        "still active", "X-Blades");
                mCompaniesModel.createCompany("SouthPeak Games", "USA", 1996, 1996,
                        "Dissolved", "Boombots");
                mCompaniesModel.createCompany("1C Company", "Russia", 1991, 1991,
                        "still active", "Men of War: Assault Squad");
                mCompaniesModel.createCompany("Straandlooper", "Ireland", 2008, 2008,
                        "still active", "Hector: Badge of Carnage");
                mCompaniesModel.createCompany("Night School Studio, LLC", "USA", 2014, 2014,
                        "still active", "Oxenfree");
                mCompaniesModel.createCompany("Supermassive Games", "England", 2008, 2008,
                        "still active", "Until Dawn");
                mCompaniesModel.createCompany("Sony Interactive Entertainment", "Japan", 1993, 1993,
                        "still active", "Playstation consoles");
                mCompaniesModel.createCompany("Sony", "Japan", 1946, 1991, "still active",
                        "Playstation");
                mCompaniesModel.createCompany("ASUSTek", "China", 1989, 0, "still active",
                        "ZenBook");
                mCompaniesModel.createCompany("Nintendo PTD (Platform Technology Development)",
                        "Japan", 2015, 0, "still active", "Nintendo Switch");
            }
            mCompanies = mCompaniesModel.getAllCompanies();

            // see if this is a two-pane display to load the details fragment or just the list
            if (findViewById(R.id.company_detail_container) != null) {
                // This container is there only on large screen or landscape
                mTwoPane = true;

                // create a new detail if one doesn't already exist
                CompanyDetailActivity.CompanyDetailFragment df = (CompanyDetailActivity.CompanyDetailFragment) fm.findFragmentByTag("detail");
                if (df == null) {
                    df = new CompanyDetailActivity.CompanyDetailFragment();
                    Bundle args = new Bundle();
                    args.putParcelable("Company", new Company("Example Company", "Example Country",
                            0, 0, "Example Fate", "Example Product"));
                    df.setArguments(args);
                    fm.beginTransaction()
                            .replace(R.id.company_detail_container, df, "detail")
                            .commit();
                }
            }

            if (savedInstanceState == null) {
                // get old company from rotate
            }

            // Initialize a new Companies list fragment, if one does not already exist
            if ( cf == null) {
                cf = new CompanyListFragment();
                Bundle arguments = new Bundle();
                arguments.putParcelableArrayList(CompanyListFragment.KEY_COMPANIES_LIST,
                        mCompaniesModel.getAllCompanies());
                cf.setArguments(arguments);
                fm.beginTransaction()
                        .replace(R.id.company_list_container, cf, CompanyListFragment.KEY_COMPANIES_LIST)
                        .commit();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            FragmentManager fm = getFragmentManager();
            switch (id) {
                case R.id.action_add:
                    EditCompanyDialog editCompanyDialog = new EditCompanyDialog();
                    editCompanyDialog.show(fm, "EditCompanyDialog");
                    return true;
                case R.id.action_delete:
                    cf.mAdapter.deleteSelectedItems(fm);
                    return true;
                case R.id.action_settings:
                    return true;
            }
            // not handled above, call super
            return super.onOptionsItemSelected(item);
        }


        // listener for the add company dialog
    public void onDialogPositiveClick(String name, String country, int dateFounded,
                                      int dateInVideoGames, String fate, String product) {
        Company newCompany = mCompaniesModel.createCompany(name, country, dateFounded,
                dateInVideoGames, fate, product);
        mCompanies.add(newCompany);
        cf.mAdapter.notifyDataSetChanged();
    }

    // listener for confirm delete positive and negative options
    public void onConfirmCompanyDeleteDialogPositiveClick() {
        cf.mAdapter.deleteProceed();
    }
    public void onConfirmCompanyDeleteDialogNegativeClick() {
        cf.mAdapter.deleteCancel();
    }

    public void deleteCompanyFromDB(long deleteId) {
        mCompaniesModel.deleteCompany(deleteId);
    }

    @Override
    public void onItemSelected(Company c) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            // Pass the selected Company object to the Detail Fragment
            arguments.putParcelable("Company", c);
            CompanyDetailActivity.CompanyDetailFragment fragment = new CompanyDetailActivity.CompanyDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.company_detail_container, fragment, "detail")
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, CompanyDetailActivity.class);
            detailIntent.putExtra("Company", c);

            startActivity(detailIntent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, LIST_FRAGMENT, cf);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            cf = (CompanyListFragment) getFragmentManager()
                    .getFragment(savedInstanceState, LIST_FRAGMENT);
        }
    }

    // callback for company list fragment to the companies
    public ArrayList<Company> getCompanies() {
        return mCompanies;
    }
}
