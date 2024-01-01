package com.example.lab5_3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class SlowTask extends AsyncTask<String, Long, Void> {
    @SuppressWarnings("deprecation")
    private ProgressDialog pdWaiting;
    private Context context;
    private Long startTime;
    private TextView tvStatus;
    public SlowTask(Context context, TextView tvStatus) {
        this.context = context;
        this.tvStatus = tvStatus;
    }
    @SuppressWarnings("deprecation")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // We can use UI thread here
        pdWaiting = new ProgressDialog(context);
        startTime = System.currentTimeMillis();
        tvStatus.setText("Start time: " + startTime);
        pdWaiting.setMessage(context.getString(R.string.please_wait));
        pdWaiting.show();
    }
    @SuppressWarnings("deprecation")
    @Override
    protected Void doInBackground(String... params) {
        // Doing SLOW job
        try {
            for (Long i = 0L; i < 3L; i++) {
                Thread.sleep(2000);
                publishProgress((Long) i);
            }
        } catch (Exception e) {
            Log.e("SlowJob", e.getMessage());
        }
        return null;
    }
    @SuppressWarnings("deprecation")
    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        tvStatus.append("\nWorking.... " + values[0]);
    }
    @SuppressWarnings("deprecation")
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (pdWaiting != null && pdWaiting.isShowing()) {
            pdWaiting.dismiss();
        }
        // Show done message
        tvStatus.append("\nEnd Time: " + System.currentTimeMillis());
        tvStatus.append("\nDone!");
    }

}