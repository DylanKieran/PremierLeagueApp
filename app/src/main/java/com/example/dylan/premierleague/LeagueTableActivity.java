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


public class LeagueTableActivity extends AppCompatActivity {

    private Database db = new Database(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaguetableactivity);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_main:
                        Intent intent1 = new Intent(LeagueTableActivity.this, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        break;

                    case R.id.action_Fixtures:
                        Intent intent3 = new Intent(LeagueTableActivity.this, FixturesActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.action_Results:
                        Intent intent4 = new Intent(LeagueTableActivity.this, ResultsActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;

                    case R.id.action_Table:

                        break;
                }


                return false;
            }
        });

        populateListView();

    }

    private void populateListView()
    {
        Cursor cursor = db.getAllTeamsCursor();
        String[] fromFieldNames = new String[]{Database.KEY_TEAMNAME};
        int[] toViewIDs = new int[] {R.id.teamName};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(this,R.layout.rowleaguetablepractice, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);
    }

}
