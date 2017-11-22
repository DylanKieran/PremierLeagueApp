package com.example.dylan.premierleague;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "Database";

    //Database Version
    private static final int DATABASE_VERSION 	= 1;

    //Database Name
    private static final String DATABASE_NAME 	= "PremierLeagueDatabase";

    //Table Names
    public static final String TABLE_TEAM 	    = "Team";
    public static final String TABLE_FIXTURES   = "Fixtures";
    public static final String TABLE_RESULTS    = "Results";
    public static final String TABLE_USER       = "user";

    //Common column names
    public static final String KEY_HOMETEAM     = "homeTeam";
    public static final String KEY_AWAYTEAM     = "awayTeam";
    public static final String KEY_DATE         = "date";

    //Team Table - column names
    public static final String KEY_TEAMID	    = "_id";
    public static final String KEY_TEAMNAME     = "teamName";
    public static final String KEY_TEAMMANAGER  = "teamManager";
    public static final String KEY_TEAMSTADIUM  = "teamStadium";

    //Fixture Table - column names
    public static final String KEY_FIXTUREID    = "_id";
    public static final String KEY_FIXTURE      = "fixture";
    public static final String KEY_TIME         = "time";

    //Result Table - column names
    public static final String KEY_RESULTID         = "_id";
    public static final String KEY_HOMETEAMSCORE    = "homeTeamScore";
    public static final String KEY_AWAYTEAMSCORE    = "awayTeamScore";
    public static final String KEY_RESULT           = "result";

    //User Table - column names
    private static final String KEY_USER_ID         = "user_id";
    private static final String KEY_USER_NAME       = "user_name";
    private static final String KEY_USER_EMAIL      = "user_email";
    private static final String KEY_USER_PASSWORD   = "user_password";

    //Table Create Statements
    //Team table create statement
    private static final String CREATE_TABLE_TEAM = "CREATE TABLE "
            + TABLE_TEAM + "(" + KEY_TEAMID + " INTEGER PRIMARY KEY," + KEY_TEAMNAME
            + " TEXT," + KEY_TEAMMANAGER + " TEXT," + KEY_TEAMSTADIUM
            + " TEXT" + ")";

    //Fixture table create statement
    private static final String CREATE_TABLE_FIXTURES = "CREATE TABLE " + TABLE_FIXTURES
            + "(" + KEY_FIXTUREID + " INTEGER PRIMARY KEY," + KEY_FIXTURE + " TEXT,"
            + KEY_HOMETEAM + " TEXT," + KEY_AWAYTEAM + " TEXT,"
            + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" +")";

    //Results table create statement
    private static final String CREATE_TABLE_RESULTS = "CREATE TABLE " + TABLE_RESULTS
            + "(" + KEY_RESULTID + " INTEGER PRIMARY KEY," + KEY_HOMETEAM + " TEXT,"
            + KEY_AWAYTEAM + " TEXT," + KEY_HOMETEAMSCORE + " INTEGER,"
            + KEY_AWAYTEAMSCORE + " INTEGER," + KEY_RESULT + " TEXT,"
            + KEY_DATE + " TEXT" + ")";

    //User table create statement
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_NAME + " TEXT,"
            + KEY_USER_EMAIL + " TEXT," + KEY_USER_PASSWORD + " TEXT" + ")";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_FIXTURES);
        db.execSQL(CREATE_TABLE_RESULTS);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion)
    {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXTURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);

        // create new tables
        onCreate(db);
    }

    public int deleteTeam()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TEAM, null, null);
    }

    public int deleteFixtures()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_FIXTURES, null, null);
    }

    public int deleteResults()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_RESULTS, null, null);
    }


    // ------------------------------------- "Team" table methods ----------------------------------

    //Inserting a Team
    public long insertTeam (int _id, String teamName, String teamManager, String teamStadium)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TEAMID, _id);
        initialValues.put(KEY_TEAMNAME, teamName);
        initialValues.put(KEY_TEAMMANAGER, teamManager);
        initialValues.put(KEY_TEAMSTADIUM, teamStadium);

        return db.insert(TABLE_TEAM, null, initialValues);
    }

    /**
     * getting all Teams
     * */
    public List<Team> getAllTeams() {
        List<Team> team = new ArrayList<Team>();
        String selectQuery = "SELECT * FROM " + TABLE_TEAM;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Team td = new Team();
                td.setTeamID(c.getInt((c.getColumnIndex(KEY_TEAMID))));
                td.setTeamName((c.getString(c.getColumnIndex(KEY_TEAMNAME))));
                td.setTeamManager(c.getString(c.getColumnIndex(KEY_TEAMMANAGER)));
                td.setTeamStadium((c.getString(c.getColumnIndex(KEY_TEAMSTADIUM))));

                // adding to Teams
                team.add(td);
            } while (c.moveToNext());
        }

        return team;
    }

    public Cursor getAllTeamsCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_TEAMID,KEY_TEAMNAME};
        Cursor cursor = db.query(TABLE_TEAM, columns, null, null, null, null, null);

        return cursor;
    }


    // ------------------------------------- "Fixtures" table methods ------------------------------

    //Inserting a Fixture
    public long insertFixture (int _id, String date, String time, String fixture, String homeTeam, String awayTeam)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FIXTUREID, _id);
        initialValues.put(KEY_FIXTURE, fixture);
        initialValues.put(KEY_HOMETEAM, homeTeam);
        initialValues.put(KEY_AWAYTEAM, awayTeam);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_TIME, time);

        return db.insert(TABLE_FIXTURES, null, initialValues);
    }

    /**
     * getting all Fixtures
     * */
    public List<Fixture> getAllFixtures() {
        List<Fixture> fixture = new ArrayList<Fixture>();
        String selectQuery = "SELECT * FROM " + TABLE_FIXTURES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Fixture td = new Fixture();
                td.setFixtureid(c.getInt((c.getColumnIndex(KEY_FIXTUREID))));
                td.setDate((c.getString(c.getColumnIndex(KEY_DATE))));
                td.setTime((c.getString(c.getColumnIndex(KEY_TIME))));
                td.setFixture((c.getString(c.getColumnIndex(KEY_FIXTURE))));
                td.setHomeTeam((c.getString(c.getColumnIndex(KEY_HOMETEAM))));
                td.setAwayTeam(c.getString(c.getColumnIndex(KEY_AWAYTEAM)));

                // adding to FIXTURE List
                fixture.add(td);
            } while (c.moveToNext());
        }

        return fixture;
    }

    public Cursor getAllFixturesCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_FIXTUREID,KEY_HOMETEAM, KEY_AWAYTEAM, KEY_DATE, KEY_TIME};
        Cursor cursor = db.query(TABLE_FIXTURES, columns, null, null, null, null, null);

        return cursor;
    }

    // ------------------------------------- "Results" table methods -------------------------------

    //Inserting a Result
    public long insertResult ( int _id, String date, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, String result)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_RESULTID, _id);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_HOMETEAM, homeTeam);
        initialValues.put(KEY_AWAYTEAM, awayTeam);
        initialValues.put(KEY_HOMETEAMSCORE, homeTeamScore);
        initialValues.put(KEY_AWAYTEAMSCORE, awayTeamScore);
        initialValues.put(KEY_RESULT, result);

        return db.insert(TABLE_RESULTS, null, initialValues);
    }

    /**
     * getting all Results
     * */
    public List<Result> getAllResults() {
        List<Result> result = new ArrayList<Result>();
        String selectQuery = "SELECT * FROM " + TABLE_RESULTS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Result td = new Result();
                td.setResultID(c.getInt((c.getColumnIndex(KEY_RESULTID))));
                td.setDate((c.getString(c.getColumnIndex(KEY_DATE))));
                td.setHomeTeam((c.getString(c.getColumnIndex(KEY_HOMETEAM))));
                td.setAwayTeam((c.getString(c.getColumnIndex(KEY_AWAYTEAM))));
                td.setHomeScore((c.getInt(c.getColumnIndex(KEY_HOMETEAMSCORE))));
                td.setAwayScore(c.getInt(c.getColumnIndex(KEY_AWAYTEAMSCORE)));
                td.setResult(c.getString(c.getColumnIndex(KEY_RESULT)));

                // adding to Result List
                result.add(td);
            } while (c.moveToNext());
        }

        return result;
    }

    public Cursor getAllResultsCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_RESULTID, KEY_HOMETEAM, KEY_HOMETEAMSCORE, KEY_AWAYTEAM, KEY_AWAYTEAMSCORE, KEY_DATE, KEY_RESULT};
        Cursor cursor = db.query(TABLE_RESULTS, columns, null, null, null, null, null);

        return cursor;
    }

    // ------------------------------------- "User" table methods ----------------------------------

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, KEY_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }


    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, KEY_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                KEY_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = KEY_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                KEY_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = KEY_USER_EMAIL + " = ?" + " AND " + KEY_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}// end class