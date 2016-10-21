package com.example.adam.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ChatWindow.java";
    ArrayList<String> chat = new ArrayList<String>();
    ChatDatabaseHelper temp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        final ListView view = (ListView)findViewById(R.id.listView);
        final Button send = (Button)findViewById(R.id.button4);
        final EditText message = (EditText)findViewById(R.id.editText3);

        temp = new ChatDatabaseHelper(this);
        db = temp.getWritableDatabase();




        Cursor c = db.query(false, temp.TABLE, new String[]{temp.KEY_MESSAGE}, null, null, null, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(temp.KEY_MESSAGE);
        while(!c.isAfterLast()){
            String text = c.getString(columnIndex);
            chat.add(text);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + c.getString( c.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE) ) );
            Log.i(ACTIVITY_NAME, "Cursor's column count = " + c.getColumnCount() );
            c.moveToNext();
        } c.close();

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        view.setAdapter(messageAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String field = message.getText().toString();
                chat.add(field);
                messageAdapter.notifyDataSetChanged();
                message.setText("");

                ContentValues values = new ContentValues();
                values.put(temp.KEY_MESSAGE, field);
                db.insert(temp.TABLE, null, values);
            }
        });
    }

    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }

    private class ChatAdapter extends ArrayAdapter<String>{

        ChatAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount(){
            return chat.size();
        }

        public String getItem(int position){
            return chat.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position % 2 == 0) result = inflater.inflate(R.layout.chat_row_incoming, null);
            else result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }
}

