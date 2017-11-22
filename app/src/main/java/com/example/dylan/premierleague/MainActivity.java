package com.example.dylan.premierleague;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

        //Set card layouts
        ListView mListView = (ListView) findViewById(R.id.list_view);

        //Navigation Menu
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_main:

                        break;

                    case R.id.action_Fixtures:
                        Intent intent2 = new Intent(MainActivity.this, FixturesActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;

                    case R.id.action_Results:
                        Intent intent3 = new Intent(MainActivity.this, ResultsActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.action_Table:
                        Intent intent4 = new Intent(MainActivity.this, LeagueTableActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });

        //Stadiums Array
        ArrayList<Stadiums> allStadiums = new ArrayList<>();

        //Add all stadiums
        allStadiums.add(new Stadiums("Arsenal","drawable://" + R.drawable.emirates,"https://www.arsenal.com/"));
        allStadiums.add(new Stadiums("Bournemouth","drawable://" + R.drawable.deancourt,"https://www.afcb.co.uk/"));
        allStadiums.add(new Stadiums("Brighton & Hove Albion","drawable://" + R.drawable.falmer,"https://www.brightonandhovealbion.com/"));
        allStadiums.add(new Stadiums("Burnley","drawable://" + R.drawable.turfmoor,"https://www.burnleyfootballclub.com/"));
        allStadiums.add(new Stadiums("Chelsea", "drawable://" + R.drawable.stamfordbridge,"https://www.chelseafc.com/"));
        allStadiums.add(new Stadiums("Crystal Palace","drawable://" + R.drawable.selhurstpark,"https://www.cpfc.co.uk/"));
        allStadiums.add(new Stadiums("Everton","drawable://" + R.drawable.goodisonpark,"http://www.evertonfc.com/home"));
        allStadiums.add(new Stadiums("Huddersfield Town","drawable://" + R.drawable.kirklees,"https://www.htafc.com/"));
        allStadiums.add(new Stadiums("Leicester City","drawable://" + R.drawable.kingpower,"https://www.lcfc.com/"));
        allStadiums.add(new Stadiums("Liverpool","drawable://" + R.drawable.anfield,"http://www.liverpoolfc.com/"));
        allStadiums.add(new Stadiums("Manchester City","drawable://" + R.drawable.etihad,"https://www.mancity.com/"));
        allStadiums.add(new Stadiums("Manchester United","drawable://" + R.drawable.oldtrafford,"http://www.manutd.com/"));
        allStadiums.add(new Stadiums("Newcastle United","drawable://" + R.drawable.stjames,"https://www.nufc.co.uk/"));
        allStadiums.add(new Stadiums("Southampton","drawable://" + R.drawable.stmarys,"https://southamptonfc.com/"));
        allStadiums.add(new Stadiums("Stoke City","drawable://" + R.drawable.bet365,"https://www.stokecityfc.com/"));
        allStadiums.add(new Stadiums("Swansea City","drawable://" + R.drawable.liberty,"https://www.swanseacity.com/"));
        allStadiums.add(new Stadiums("Tottenham Hotspur","drawable://" + R.drawable.wembley,"http://www.tottenhamhotspur.com/"));
        allStadiums.add(new Stadiums("Watford","drawable://" + R.drawable.vicarage,"https://www.watfordfc.com/"));
        allStadiums.add(new Stadiums("West Bromwich Albion","drawable://" + R.drawable.hawthorns,"https://www.wba.co.uk/"));
        allStadiums.add(new Stadiums("West Ham United","drawable://" + R.drawable.londonstadium,"https://www.whufc.com/"));

        //Card List view
        CustomListAdapter adapter;
        adapter = new CustomListAdapter(this, R.layout.cardlayout, allStadiums);
        mListView.setAdapter(adapter);

        //Call Database Deletes and Inserts
        deleteInserts();
        createInserts();

        //Database Things
        //Get all Teams
        List<Team> allTeams = db.getAllTeams();
        for (Team team : allTeams) {
            Log.d("Team", team.getTeamName());
        }

        // Get all Fixtures
        List<Fixture> allFixtures = db.getAllFixtures();
        for (Fixture fixtures : allFixtures) {
            Log.d("Fixture", fixtures.getFixture());
        }

        // Get all Results
        List<Result> allResults = db.getAllResults();
        for (Result results : allResults) {
            Log.d("Results", results.getHomeTeam());
        }

        // Getting point total for each team
        // ****************************************************
        for (int i = 0; i < allTeams.size(); i++)
        {
            for (int j = 0; j < allResults.size(); j++)
            {
                Log.d("Team", allTeams.get(i).getTeamName());
                Log.d("Result", allResults.get(j).getResult());

                if(allTeams.get(i).getTeamName().equals(allResults.get(j).getAwayTeam()) && "A".equals(allResults.get(j).getResult()))
                {
                    allTeams.get(i).setPointTotal(3);
                }
                else if (allTeams.get(i).getTeamName().equals(allResults.get(j).getHomeTeam()) && "H".equals(allResults.get(j).getResult()))
                {
                    allTeams.get(i).setPointTotal(3);
                }
                else if (allTeams.get(i).getTeamName().equals(allResults.get(j).getHomeTeam()) || allTeams.get(i).getTeamName().equals(allResults.get(j).getAwayTeam()) && "D".equals(allResults.get(j).getResult()))
                {
                    allTeams.get(i).setPointTotal(1);
                }
            }
        }

        //Printing Point total
        for (Team team : allTeams) {
            Log.d("Point Total", "= " + team.getPointTotal());
        }
        //******************************************************

        db.close();
    }

    void deleteInserts()
    {
        //Delete Table Data
        db.deleteTeam();
        db.deleteFixtures();
        db.deleteResults();
    }

    void createInserts()
    {
        //Insert Data into Teams
        db.insertTeam(1,"Arsenal", "Arsène Wenger", "emirates");
        db.insertTeam(2,"Bournemouth", "Eddie Howe", "deancourt");
        db.insertTeam(3,"Brighton", "Chris Hughton", "falmer");
        db.insertTeam(4,"Burnley", "Sean Dyche", "turfmoor");
        db.insertTeam(5,"Chelsea", "Antonio Conte", "stamfordbridge");
        db.insertTeam(6,"Crystal Palace", "Roy Hodgson", "selhurstpark");
        db.insertTeam(7,"Everton", "David Unsworth", "goodisonpark");
        db.insertTeam(8,"Huddersfield Town", "David Wagner", "kirklees");
        db.insertTeam(9,"Leicester City", "Claude Puel", "kingpower");
        db.insertTeam(10,"Liverpool", "Jürgen Klopp", "anfield");
        db.insertTeam(11,"Manchester City", "Pep Guardiola", "etihad");
        db.insertTeam(12,"Manchester United", "José Mourinho", "oldtrafford");
        db.insertTeam(13,"Newcastle United", "Rafael Benítez", "stjamespark");
        db.insertTeam(14,"Southampton", "Mauricio Pellegrino", "stmarys");
        db.insertTeam(15,"Stoke City", "Mark Hughes", "bet365");
        db.insertTeam(16,"Swansea City", "Paul Clement", "liberty");
        db.insertTeam(17,"Tottenham Hotspur", "Mauricio Pochettino", "wembley");
        db.insertTeam(18,"Watford", "Marco Silva", "vicarage");
        db.insertTeam(19,"West Brom", "", "hawthorns");
        db.insertTeam(20,"West Ham United", "David Moyes", "londonstadium");

        //Insert Data into Fixtures
        db.insertFixture(1,"04/11/2017","15:00","Chelsea V Manchester United" ,"Chelsea","Manchester United");
        db.insertFixture(2,"04/11/2017","15:00","Everton V Watford" ,"Everton","Watford");
        db.insertFixture(3,"04/11/2017","15:00","Huddersfield Town V West Bromwich Albion" ,"Huddersfield Town","West Bromwich Albion");
        db.insertFixture(4,"04/11/2017","15:00","Manchester City V Arsenal" ,"Manchester City","Arsenal");
        db.insertFixture(5,"04/11/2017","15:00","Newcastle United V Bournemouth" ,"Newcastle United","Bournemouth");
        db.insertFixture(6,"04/11/2017","15:00","Southampton V Burnley" ,"Southampton","Burnley");
        db.insertFixture(7,"04/11/2017","15:00","Stoke City V Leicester City" ,"Stoke City","Leicester City");
        db.insertFixture(8,"04/11/2017","15:00","Swansea City V Brighton & Hove Albion" ,"Swansea City","Brighton & Hove Albion");
        db.insertFixture(9,"04/11/2017","15:00","Tottenham Hotspur V Crystal Palace" ,"Tottenham Hotspur","Crystal Palace");
        db.insertFixture(10,"04/11/2017","15:00","West Ham United V Liverpool" ,"West Ham United","Liverpool");
        db.insertFixture(12,"18/11/2017","15:00","Arsenal V Tottenham Hotspur" ,"Arsenal","Tottenham Hotspur");
        db.insertFixture(13,"18/11/2017","15:00","Bournemouth V Huddersfield Town" ,"Bournemouth","Huddersfield Town");
        db.insertFixture(14,"18/11/2017","15:00","Brighton & Hove Albion V Stoke City" ,"Brighton & Hove Albion","Stoke City");
        db.insertFixture(15,"18/11/2017","15:00","Burnley V Swansea City" ,"Burnley","Swansea City");
        db.insertFixture(16,"18/11/2017","15:00","Crystal Palace V Everton","Crystal Palace","Everton");
        db.insertFixture(17,"18/11/2017","15:00","Leicester City V Manchester City" ,"Leicester City","Manchester City");
        db.insertFixture(18,"18/11/2017","15:00","Liverpool V Southampton" ,"Liverpool","Southampton");
        db.insertFixture(19,"18/11/2017","15:00","Manchester United V Newcastle United" ,"Manchester United","Newcastle United");
        db.insertFixture(20,"18/11/2017","15:00","Watford V West Ham United" ,"Watford","West Ham United");
        db.insertFixture(21,"18/11/2017","15:00","West Bromwich Albion V Chelsea" ,"West Bromwich Albion","Chelsea");
        db.insertFixture(22,"25/11/2017","15:00","Burnley V Arsenal" ,"Burnley","Arsenal");
        db.insertFixture(23,"25/11/2017","15:00","Crystal Palace V Stoke City" ,"Crystal Palace","Stoke City");
        db.insertFixture(24,"25/11/2017","15:00","Huddersfield Town V Manchester City" ,"Huddersfield Town","Manchester City");
        db.insertFixture(25,"25/11/2017","15:00","Liverpool V Chelsea" ,"Liverpool","Chelsea");
        db.insertFixture(26,"25/11/2017","15:00","Manchester United V Brighton & Hove Albion" ,"Manchester United","Brighton & Hove Albion");
        db.insertFixture(27,"25/11/2017","15:00","Newcastle United V Watford" ,"Newcastle United","Watford");
        db.insertFixture(28,"25/11/2017","15:00","Southampton V Everton" ,"Southampton","Everton");
        db.insertFixture(29,"25/11/2017","15:00","Swansea City V Bournemouth" ,"Swansea City","Bournemouth");
        db.insertFixture(30,"25/11/2017","15:00","Tottenham Hotspur V West Bromwich Albion" ,"Tottenham Hotspur","West Bromwich Albion");
        db.insertFixture(31,"25/11/2017","15:00","West Ham United V Leicester City" ,"West Ham United","Leicester City");
        db.insertFixture(32,"28/11/2017","19:45","Arsenal V Huddersfield Town" ,"Arsenal","Huddersfield Town");
        db.insertFixture(33,"28/11/2017","19:45","Bournemouth V Burnley" ,"Bournemouth","Burnley");
        db.insertFixture(34,"28/11/2017","19:45","Brighton & Hove Albion V Crystal Palace" ,"Brighton & Hove Albion","Crystal Palace");
        db.insertFixture(35,"28/11/2017","19:45","Leicester City V Tottenham Hotspur" ,"Leicester City","Tottenham Hotspur");
        db.insertFixture(36,"28/11/2017","19:45","Watford V Manchester United" ,"Watford","Manchester United");
        db.insertFixture(37,"28/11/2017","20:00","West Bromwich Albion V Newcastle United" ,"West Bromwich Albion","Newcastle United");
        db.insertFixture(38,"29/11/2017","19:45","Chelsea V Swansea City" ,"Chelsea","Swansea City");
        db.insertFixture(39,"29/11/2017","19:45","Everton V West Ham United" ,"Everton","West Ham United");
        db.insertFixture(40,"29/11/2017","20:00","Manchester City V Southampton" ,"Manchester City","Southampton");
        db.insertFixture(41,"29/11/2017","20:00","Stoke City V Liverpool" ,"Stoke City","Liverpool");

        //Insert Data into Results
        db.insertResult(1,"11/08/17","Arsenal","Leicester",4,3,"H");
        db.insertResult(2,"12/08/17","Brighton","Man City",0,2,"A");
        db.insertResult(3,"12/08/17","Chelsea","Burnley",2,3,"A");
        db.insertResult(4,"12/08/17","Crystal Palace","Huddersfield",0,3,"A");
        db.insertResult(5,"12/08/17","Everton","Stoke",1,0,"H");
        db.insertResult(6,"12/08/17","Southampton","Swansea",0,0,"D");
        db.insertResult(7,"12/08/17","Watford","Liverpool",3,3,"D");
        db.insertResult(8,"12/08/17","West Brom","Bournemouth",1,0,"H");
        db.insertResult(9,"13/08/17","Man United","West Ham",4,0,"H");
        db.insertResult(10,"13/08/17","Newcastle","Tottenham",0,2,"A");
        db.insertResult(11,"19/08/17","Bournemouth","Watford",0,2,"A");
        db.insertResult(12,"19/08/17","Burnley","West Brom",0,1,"A");
        db.insertResult(13,"19/08/17","Leicester","Brighton",2,0,"H");
        db.insertResult(14,"19/08/17","Liverpool","Crystal Palace",1,0,"H");
        db.insertResult(15,"19/08/17","Southampton","West Ham",3,2,"H");
        db.insertResult(16,"19/08/17","Stoke","Arsenal",1,0,"H");
        db.insertResult(17,"19/08/17","Swansea","Man United",0,4,"A");
        db.insertResult(18,"20/08/17","Huddersfield","Newcastle",1,0,"H");
        db.insertResult(19,"20/08/17","Tottenham","Chelsea",1,2,"A");
        db.insertResult(20,"21/08/17","Man City","Everton",1,1,"D");
        db.insertResult(21,"26/08/17","Bournemouth","Man City",1,2,"A");
        db.insertResult(22,"26/08/17","Crystal Palace","Swansea",0,2,"A");
        db.insertResult(23,"26/08/17","Huddersfield","Southampton",0,0,"D");
        db.insertResult(24,"26/08/17","Man United","Leicester",2,0,"H");
        db.insertResult(25,"26/08/17","Newcastle","West Ham",3,0,"H");
        db.insertResult(26,"26/08/17","Watford","Brighton",0,0,"D");
        db.insertResult(27,"27/08/17","Chelsea","Everton",2,0,"H");
        db.insertResult(28,"27/08/17","Liverpool","Arsenal",4,0,"H");
        db.insertResult(29,"27/08/17","Tottenham","Burnley",1,1,"D");
        db.insertResult(30,"27/08/17","West Brom","Stoke",1,1,"D");
        db.insertResult(31,"09/09/17","Arsenal","Bournemouth",3,0,"H");
        db.insertResult(32,"09/09/17","Brighton","West Brom",3,1,"H");
        db.insertResult(33,"09/09/17","Everton","Tottenham",0,3,"A");
        db.insertResult(34,"09/09/17","Leicester","Chelsea",1,2,"A");
        db.insertResult(35,"09/09/17","Man City","Liverpool",5,0,"H");
        db.insertResult(36,"09/09/17","Southampton","Watford",0,2,"A");
        db.insertResult(37,"09/09/17","Stoke","Man United",2,2,"D");
        db.insertResult(38,"10/09/17","Burnley","Crystal Palace",1,0,"H");
        db.insertResult(39,"10/09/17","Swansea","Newcastle",0,1,"A");
        db.insertResult(40,"11/09/17","West Ham","Huddersfield",2,0,"H");
        db.insertResult(41,"15/09/17","Bournemouth","Brighton",2,1,"H");
        db.insertResult(42,"16/09/17","Crystal Palace","Southampton",0,1,"A");
        db.insertResult(43,"16/09/17","Huddersfield","Leicester",1,1,"D");
        db.insertResult(44,"16/09/17","Liverpool","Burnley",1,1,"D");
        db.insertResult(45,"16/09/17","Newcastle","Stoke",2,1,"H");
        db.insertResult(46,"16/09/17","Tottenham","Swansea",0,0,"D");
        db.insertResult(47,"16/09/17","Watford","Man City",0,6,"A");
        db.insertResult(48,"16/09/17","West Brom","West Ham",0,0,"D");
        db.insertResult(49,"17/09/17","Chelsea","Arsenal",0,0,"D");
        db.insertResult(50,"17/09/17","Man United","Everton",4,0,"H");
        db.insertResult(51,"23/09/17","Burnley","Huddersfield",0,0,"D");
        db.insertResult(52,"23/09/17","Everton","Bournemouth",2,1,"H");
        db.insertResult(53,"23/09/17","Leicester","Liverpool",2,3,"A");
        db.insertResult(54,"23/09/17","Man City","Crystal Palace",5,0,"H");
        db.insertResult(55,"23/09/17","Southampton","Man United",0,1,"A");
        db.insertResult(56,"23/09/17","Stoke","Chelsea",0,4,"A");
        db.insertResult(57,"23/09/17","Swansea","Watford",1,2,"A");
        db.insertResult(58,"23/09/17","West Ham","Tottenham",2,3,"A");
        db.insertResult(59,"24/09/17","Brighton","Newcastle",1,0,"H");
        db.insertResult(60,"25/09/17","Arsenal","West Brom",2,0,"H");
        db.insertResult(61,"30/09/17","Bournemouth","Leicester",0,0,"D");
        db.insertResult(62,"30/09/17","Chelsea","Man City",0,1,"A");
        db.insertResult(63,"30/09/17","Huddersfield","Tottenham",0,4,"A");
        db.insertResult(64,"30/09/17","Man United","Crystal Palace",4,0,"H");
        db.insertResult(65,"30/09/17","Stoke","Southampton",2,1,"H");
        db.insertResult(66,"30/09/17","West Brom","Watford",2,2,"D");
        db.insertResult(67,"30/09/17","West Ham","Swansea",1,0,"H");
        db.insertResult(68,"01/10/17","Arsenal","Brighton",2,0,"H");
        db.insertResult(69,"01/10/17","Everton","Burnley",0,1,"A");
        db.insertResult(70,"01/10/17","Newcastle","Liverpool",1,1,"D");
    }
}
