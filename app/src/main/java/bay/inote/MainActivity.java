package bay.inote;



import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

import static bay.inote.Constants.*;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Note> arrOfNotes;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this) {
        };
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    ArrayList<String> listItems = new ArrayList<String>();

    private NotesHelper noteHelper;
    private HashMap<Integer, Note> allNotes;

//    private final ListView listView = (ListView) findViewById(R.id.lvItems);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        allNotes = new HashMap<>();
        arrOfNotes = new ArrayList<Note>();
        noteHelper = new NotesHelper(this);
//        noteHelper.onCreate();
        Cursor cursor = getAllNotes();
        showNotes(cursor);

//        listView.setAdapter(new ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Insert Success", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void addNote(String str) {
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(CONTENT, str);
        getContentResolver().insert(URI_NOTES, values);
    }

    //    private static String[] COLUMNS = {BaseColumns._ID, TIME, CONTENT}; //original
    private static String[] COLUMNS = {BaseColumns._ID, TOPIC, CONTENT, PRIORITY, TIME, DATE};
    private static String ORDER_BY = TIME + " DESC";

    private Cursor getAllNotes() {
        return managedQuery(URI_NOTES, COLUMNS, null, null, ORDER_BY);
    }

//    private static int[] VIEWS = {R.id.rowid, R.id.time, R.id.content};

    private void showNotes(Cursor cursor) {
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                R.layout.item, cursor, COLUMNS, VIEWS, 0);
//        setListAdapter(adapter);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_queryAllData) {


            final Cursor res = noteHelper.getAllData();
            if (res.getCount() == 0) {
                showMessage("Error", "No data in database");
                return false;
            } else {
                StringBuffer buff = new StringBuffer();
                while (res.moveToNext()) {

                    Note noteToAdd = new Note() {
                        {
                            setTopic(res.getString(1));
                            setContent(res.getString(2));
                            setPriority(Integer.valueOf(res.getString(3)));
                            setDate(res.getString(4));
                            setTime(res.getString(5));
                        }
                    };
                    allNotes.put(Integer.valueOf(res.getString(0)), noteToAdd);
//                    buff.append("ID: " + res.getString(0) + "\n");
//                    buff.append("Topic: " + res.getString(1) + "\n");
//                    buff.append("Content: " + res.getString(2) + "\n");
//                    buff.append("PPriority: " + res.getString(3) + "\n");
//                    buff.append("Date: " + res.getString(4) + "\n");
//                    buff.append("Time: " + res.getString(5) + "\n");
//

                }
//                buff.append(noteToAdd.toString());
                for (int key : allNotes.keySet()) {
//                    ListView listView = (ListView)findViewById(R.id.lvItems);
//                    listView.add
//                    listItems.add("Clicked : " + clickCounter++);
//                    adapter.notifyDataSetChanged();
                    buff.append(String.format("id: %d\n%s\n", key, allNotes.get(key).toString()));
                }
                showMessage("All data", buff.toString());
            }
            return true;
        } else if (id == R.id.action_deleteAll) {
            try {
                noteHelper.deleteAll();
                allNotes.clear();
                showMessage("Status", "Delete success.");
            } catch (Exception ex) {
                showMessage("Status", "Delete failed:\n" + ex.getMessage());
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
