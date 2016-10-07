package com.example.adam.lab1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    ArrayList<String> chat = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        final ListView view = (ListView)findViewById(R.id.listView);
        final Button send = (Button)findViewById(R.id.button4);
        final EditText message = (EditText)findViewById(R.id.editText3);

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        view.setAdapter(messageAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat.add(message.getText().toString());
                messageAdapter.notifyDataSetChanged();
                message.setText("");
            }
        });


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

