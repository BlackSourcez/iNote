package bay.inote;

import android.net.Uri;
import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
//    public static final String TABLE_NAME = "notes"; // ชื่อเทเบิล notes
//    public static final String TIME = "time";        // ชื่อคอลัมน์ time
//    public static final String CONTENT = "content";  // ชื่อคอลัมน์ content




    public static final String TABLE_NAME = "notes"; // ชื่อเทเบิล notes

    public static final String TOPIC = "topic";
    public static final String CONTENT = "content";
    public static final String PRIORITY = "priority";

    public static final String DATE = "date";
    public static final String TIME = "time";



    public static final String AUTHORITY = "bay.inote";
    public static final Uri URI_NOTES = Uri.parse("content://" +
            AUTHORITY + "/" + TABLE_NAME);



}


/*

CREATE TABLE `notes` (
	`_id`	INTEGER NOT NULL UNIQUE,
	`topic`	TEXT NOT NULL,
	`content`	TEXT,
	`priority`	INTEGER,
	`date`	TEXT,
	`time`	TEXT,
	PRIMARY KEY(`priority`)
);

*/