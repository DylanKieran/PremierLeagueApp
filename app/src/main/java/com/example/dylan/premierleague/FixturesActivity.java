package com.example.dylan.premierleague;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class FixturesActivity extends AppCompatActivity {

    private Database db = new Database(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixturesactivity);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_main:
                        Intent intent1 = new Intent(FixturesActivity.this, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        break;

                    case R.id.action_Fixtures:


                        break;

                    case R.id.action_Results:
                        Intent intent3 = new Intent(FixturesActivity.this, ResultsActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.action_Table:
                        Intent intent4 = new Intent(FixturesActivity.this, LeagueTableActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });

        populateListView();
    }

    private void populateListView()
    {
        Cursor cursor = db.getAllFixturesCursor();
        String[] fromFieldNames = new String[] {Database.KEY_HOMETEAM, Database.KEY_AWAYTEAM, Database.KEY_DATE, Database.KEY_TIME};
        int[] toViewIDs = new int[] {R.id.HomeTeam, R.id.AwayTeam, R.id.Date, R.id.Time};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(this,R.layout.rowlayoutfixtures, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);
    }
}
