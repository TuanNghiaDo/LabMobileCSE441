package com.cse441.arsyntask.model;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cse441.arsyntask.R;


public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    Activity contextCha;

    public MyAsyncTask(Activity ctx) {
        contextCha = ctx;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }


    @Override
    protected Void doInBackground(Void... arg0) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
        ProgressBar paCha = (ProgressBar) contextCha.findViewById(R.id.progressBar1);
        int giatri = values[0];
        paCha.setProgress(giatri);
        TextView txtmsg = (TextView) contextCha.findViewById(R.id.textView1);
        txtmsg.setText(giatri + "%");
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        Toast.makeText(contextCha, "Update xong roi do!", Toast.LENGTH_LONG).show();
    }
}
