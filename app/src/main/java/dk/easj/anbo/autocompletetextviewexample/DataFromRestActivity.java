package dk.easj.anbo.autocompletetextviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DataFromRestActivity extends AppCompatActivity {

    public static final String REST_URI = "http://anbo-fish.azurewebsites.net/Service1.svc/fish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from_rest);

        ReadFishNamesTask task = new ReadFishNamesTask();
        task.execute(REST_URI);
    }

    class ReadFishNamesTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            final List<String> fishNames = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    String name = array.getString(i);
                    fishNames.add(name);
                }
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, fishNames);
                AutoCompleteTextView view =
                        findViewById(R.id.datafromrest_fishname_autocompletetextview);
                view.setAdapter(adapter);
            } catch (JSONException ex) {
                TextView errorView = findViewById(R.id.datafromrest_error_textview);
                errorView.setText(ex.getMessage());
                Log.e("FISH", ex.getMessage());
            }
        }

        @Override
        protected void onCancelled(CharSequence errorMessage) {
            TextView errorView = findViewById(R.id.datafromrest_error_textview);
            errorView.setText(errorMessage);
            Log.e("FISH", errorMessage.toString());
        }
    }
}
