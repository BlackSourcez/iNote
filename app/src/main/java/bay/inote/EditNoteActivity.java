package bay.inote;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.StringTokenizer;

public class EditNoteActivity extends AppCompatActivity {


    private Note note;
//    private static final String EXTRA_NOTE = "EXTRA_NOTE";
//    private Note note;
//
//    /**
//     * Construye el Intent para llamar a esta actividad con una nota ya existente.
//     *
//     * @param context el contexto que la llama.
//     * @param note la nota a editar.
//     * @return un Intent.
//     */
//    public static Intent buildIntent(Context context, Note note) {
//        Intent intent = new Intent(context, EditNoteActivity.class);
//        intent.putExtra(EXTRA_NOTE, note);
//        return intent;
//    }

    private NotesHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        note = new Note();
        noteHelper = new NotesHelper(this);


        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final EditText txtTopic = (EditText) findViewById(R.id.editTextTopic);
        final EditText txtContent = (EditText) findViewById(R.id.editTextContent);
        txtContent.addTextChangedListener(new TextWatcher() {

//            boolean isSet = false;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


//            if(txtTopic.getText().toString().equalsIgnoreCase(txtContent.getText().toString())){
//                txtTopic.setText(txtContent.getText());
//                return;
//            }
//            if(!isSet){
//
//
////                if(txtTopic.getText().length() == 0 && txtContent.getText().length() > 0){
//                    if(txtContent.getText().toString().length() > 0){
//                        String[] line = txtContent.getText().toString().split("\\s");
//                        if(line.length > 0){
//                            txtTopic.setText(line[0]);
//                        }
//                        isSet = true;
//                    }
////                }
//            }

//            if(txtTopic.getText().length() >0 && (txtTopic.getText() != txtContent.getText()))
//                return ;
//            if(txtTopic.getText() != txtContent.getText()) return;
//            if(txtTopic.getText().length() > 0) return;

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(txtTopic.getText().length() > 0 && txtContent.getText().length() > 0){
                    if(note != null){
                        note.setTopic(txtTopic.getText().toString());
                        note.setContent(txtContent.getText().toString());
                        note.setPriority((int)ratingBar.getRating());
//                        note.setDate(null);
//                        note.setTime("NOW");

                        //check if true or false
                        if(noteHelper.insertData(note)){

                            if(txtTopic.getText().toString().length() == 0){

                                String[] line = txtContent.getText().toString().split("\\s");
                                if(line.length > 0){
                                    txtTopic.setText(line[0]);
                                }
                            }

                           Snackbar.make(view, note.toString(), Snackbar.LENGTH_LONG)
                              .setAction("Insert Success", null).show();
                            finish();
                        }
                        else{
                            Snackbar.make(view, note.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Insert Failed", null).show();
                        }



                    }

                }
                else{
//                    Snackbar.make(view, note.toString(), Snackbar.LENGTH_LONG)
//                            .setAction("Please enter your topic and content", null).show();

                    Snackbar.make(view,"Please enter your topic and content" , Snackbar.LENGTH_LONG).show();
                }
//                Log.d(note.toString());
            }
        });


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Snackbar.make(ratingBar, "" + , Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                note.setPriority((int)ratingBar.getRating());

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_selectDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            final int  mYear = c.get(Calendar.YEAR);
            final int mMonth = c.get(Calendar.MONTH);
            final int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

//                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            note.setDate(String.format("%02d/%02d/%d",mDay,mMonth,mYear));
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        else if(id == R.id.action_selectTime){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            final int mHour = c.get(Calendar.HOUR_OF_DAY);
            final int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            note.setTime(String.format("%02d:%02d",hourOfDay,mMinute));
//                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

//    boolean doubleBackToExitPressedOnce = false;
//
//    @Override
//    public void onBackPressed() {
////        if (doubleBackToExitPressedOnce) {
////            super.onBackPressed();
////            return;
////        }
//
////        this.doubleBackToExitPressedOnce = true;
////        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
////
////        new Handler().postDelayed(new Runnable() {
////
////            @Override
////            public void run() {
////                doubleBackToExitPressedOnce=false;
////            }
////        }, 2000);
//    }
}
