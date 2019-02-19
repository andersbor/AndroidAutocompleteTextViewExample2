package dk.easj.anbo.autocompletetextviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.Arrays;

// https://developer.android.com/reference/android/widget/AutoCompleteTextView.html
public class MainActivity extends AppCompatActivity {
    // http://www.fao.org/wairdocs/tan/x5994e/x5994e01.htm
    private static final String[] FISH_NAMES = new String[]{
            "bream", "carp",
            "grayling", "perch", "pike", "pike-perch", "roach",
            "tench", "eel", "sturgeon", "salmon",
            "trout", "smelt", "rainbow trout", "whitefish",
            "picked dogfish", "shark", "skate",
            "smooth hound", "anchovy", "herring", "pilchard",
            "sardine", "sardinella", "sprat", "blue ling",
            "blue whitling", "cod", "greater forkbeard",
            "haddock", "hake", "ling", "pollack", "poor cod",
            "pout", "saithe", "tush", "whiting",
            "atherine", "bogue", "mullet", "picaral", "scad",
            "sea bream", "surmullet", "chub mackerel", "garfish",
            "mackerel", "swordfish", "albacore tuna",
            "bonito tuna", "skipjack tuna", "tuna"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Arrays.sort(FISH_NAMES);
        AutoCompleteTextView view = (AutoCompleteTextView) findViewById(R.id.main_breed_autocompletetextview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, FISH_NAMES);
        view.setAdapter(adapter);
    }

    public void buttonNextClicked(View view) {
        Intent intent = new Intent(this, DataFromRestActivity.class);
        startActivity(intent);
    }

    public void buttonShowClicked(View view) {
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.main_breed_autocompletetextview);
        Editable text = textView.getText();
        Toast.makeText(this, "You choose: " + text, Toast.LENGTH_SHORT).show();
    }
}