package danazone.com.vitu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import danazone.com.vitu.bean.InfoUser;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Visu";
    private static final String TABLE_NAME = "info_user";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String GENDER = "gender";
    private static final String SCHOOL = "school";
    private static final String BIRTHDAY = "birthday";
    private static final String HOME_TOWN = "home_town";
    private static final String EX = "experience";
    private static final String TYPE = "type";
    private static final String AVATAR = "avatar";
    private static final String VERYFY = "verify";
    private static final String DEGREE = "degree";
    private static final String COIN = "coin";


    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " integer primary key, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                EMAIL + " TEXT, " +
                GENDER + " TEXT, " +
                SCHOOL + " TEXT, " +
                BIRTHDAY + " TEXT, " +
                HOME_TOWN + " TEXT, " +
                EX + " TEXT, " +
                TYPE + " TEXT, " +
                AVATAR + " TEXT, " +
                VERYFY + " TEXT, " +
                DEGREE + " TEXT, " +
                COIN + " TEXT)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Add new a USER
    public void addIfoUser(InfoUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, user.getName());
        values.put(PHONE, user.getPhone());
        values.put(EMAIL, user.getEmail());
        values.put(GENDER, user.getGender());
        values.put(SCHOOL, user.getSchool());
        values.put(BIRTHDAY, user.getBirthday());
        values.put(HOME_TOWN, user.getHomeTown());
        values.put(EX, user.getExperience());
        values.put(TYPE, user.getType());
        values.put(AVATAR, user.getAvatar());
        values.put(VERYFY, user.getVerify());
        values.put(DEGREE, user.getDegree());
        values.put(COIN, user.getCoin());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_NAME, null);
        return res;
    }
}
