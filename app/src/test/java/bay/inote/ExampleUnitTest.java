package bay.inote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testInsertDataInTable(){
        NotesHelper mDbHelper = new NotesHelper(getContext());
        db.openOrCreateDatabase("inote_db", Context.MODE_PRIVATE,null);
        String str =   "INSERT INTO notes (_id,topic,content,priority,date,time) VALUES (null,\"one \" " +
             ", \"two three\" , 3, \"THIS IS A DATE\" , \"THIS IS A TIME\");";
    }

}