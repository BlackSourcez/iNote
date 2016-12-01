package bay.inote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static bay.inote.Constants.*;


public class NotesHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inote_database.db";
    private static final int DATABASE_VERSION = 1;

    // คอนสตรัคเตอร์
    public NotesHelper(Context context) {
        // เรียกคอนสตรัคเตอร์ของคลาส SQLiteOpenHelper
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // สร้างเทเบิล notes ที่ประกอบด้วยคอลัมน์ _id, time และ content
//        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + BaseColumns._ID
//                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME
//                + " INTEGER, " + CONTENT + " TEXT NOT NULL);");



            db.execSQL(String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s INTEGER, " +
                    "%s TEXT, " +
                    "%s TIME);",TABLE_NAME, BaseColumns._ID ,TOPIC , CONTENT, PRIORITY, DATE, TIME ));
        /*
CREATE TABLE `notes` (
	`_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`topic`	TEXT NOT NULL,
	`content`	TEXT NOT NULL,
	`priority`	INTEGER,
	`date`	TEXT,
	`time`	TEXT
);
         */

  /*
  INSERT INTO notes (_id,topic,content,priority,date,time)
VALUES (null,"one " , "two three" , 3, "THIS IS A DATE" , "THIS IS A TIME");

   */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // โค้ดที่ใช้อัพเกรดฐานข้อมูล
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public boolean insertData(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValuess = new ContentValues();
        contentValuess.put(TOPIC,note.getTopic());
        contentValuess.put(CONTENT,note.getContent());
        contentValuess.put(PRIORITY,note.getPriority());
        contentValuess.put(DATE,note.getDate());
        contentValuess.put(TIME,note.getTime());

        long result = db.insert(TABLE_NAME,null, contentValuess);
        return result==-1?false:true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT *  from " + TABLE_NAME , null);
        return res;
    }

    public void deleteAll(){
         this.getReadableDatabase().execSQL("DELETE FROM " + TABLE_NAME);
         this.getReadableDatabase().execSQL(String.format("delete from sqlite_sequence where name='%s';", TABLE_NAME)); //clear sqlite subsequence
    }

}
