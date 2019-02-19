package dk.easj.anbo.autocompletetextviewexample;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class ReadHttpTask extends AsyncTask<String, Void, CharSequence> {
    public static final String LOG_TAG = "myTag";

    @Override
    protected CharSequence doInBackground(String... urls) {
        String urlString = urls[0];
        try {
            CharSequence result = HttpHelper.GetHttpResponse(urlString);
            return result;
        } catch (IOException ex) {
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            Log.e(LOG_TAG, errorMessage);
            return errorMessage;
        }
    }
}