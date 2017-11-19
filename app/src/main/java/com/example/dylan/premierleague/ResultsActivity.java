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
import android.widget.TextView;


public class ResultsActivity extends AppCompatActivity {

    private Database db = new Database(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultsactivity);

        TextView title = (TextView) findViewById(R.id.main);
        title.setText("Results");

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
                        Intent intent1 = new Intent(ResultsActivity.this, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        break;

                    case R.id.action_Team:
                        Intent intent2 = new Intent(ResultsActivity.this, TeamActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;

                    case R.id.action_Fixtures:
                        Intent intent3 = new Intent(ResultsActivity.this, FixturesActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.action_Results:

                        break;

                    case R.id.action_Table:
                        Intent intent4 = new Intent(ResultsActivity.this, LeagueTableActivity.class);
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
        Cursor cursor = db.getAllResultsCursor();
        String[] fromFieldNames = new String[] {Database.KEY_RESULTID, Database.KEY_DATE, Database.KEY_HOMETEAM, Database.KEY_HOMETEAMSCORE, Database.KEY_AWAYTEAM, Database.KEY_AWAYTEAMSCORE, Database.KEY_RESULT};
        int[] toViewIDs = new int[] {R.id.ResultID,R.id.Date, R.id.HomeTeam, R.id.HomeTeamScore, R.id.AwayTeam, R.id.AwayTeamScore, R.id.Result};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(this,R.layout.rowlayoutresults, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);
    }
}
