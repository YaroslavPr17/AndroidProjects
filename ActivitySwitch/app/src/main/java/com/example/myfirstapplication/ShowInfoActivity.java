package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Insets;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowInfoActivity extends AppCompatActivity {

    TextView lastEnteredStudent;
    EditText etNameOut, etBirthdayOut, etCourseOut;
    Button chooseWebsiteButton, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

/*      @Deprecated
        // Получим Display из the WindowManager
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay();
*/
        final WindowMetrics metrics = ((WindowManager)getSystemService(WINDOW_SERVICE)).getCurrentWindowMetrics();
        // Gets all excluding insets
        final WindowInsets windowInsets = metrics.getWindowInsets();
        Insets insets = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars()
                | WindowInsets.Type.displayCutout());

        int insetsWidth = insets.right + insets.left;
        int insetsHeight = insets.top + insets.bottom;

        // Legacy size that Display#getSize reports
        final Rect bounds = metrics.getBounds();
        final Size legacySize = new Size(bounds.width() - insetsWidth,
                bounds.height() - insetsHeight);
        lastEnteredStudent = findViewById(R.id.lastEnteredStudentTextView);
        lastEnteredStudent.setWidth((int) Math.round(0.99*legacySize.getWidth()));
        lastEnteredStudent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lastEnteredStudent.setLineSpacing(0.45F, 0.6F);

        Bundle arguments = getIntent().getExtras();
        System.out.println(arguments);
        if(arguments!=null){
            etNameOut = findViewById(R.id.showNameValue);
            etBirthdayOut = findViewById(R.id.showDateOfBirthValue);
            etCourseOut = findViewById(R.id.showCourseValue);

            etNameOut.setText(arguments.get("name").toString());
            etBirthdayOut.setText(arguments.get("birthday").toString());
            etCourseOut.setText(arguments.get("course").toString());
        }


        chooseWebsiteButton = findViewById(R.id.chooseWebsiteButton);
        chooseWebsiteButton.setOnClickListener(this::onInputAddressClick);

    }

    private void onInputAddressClick(View button) {
        Intent emailIntent = new Intent(this, EnterWebsiteActivity.class);
        startActivityForResult(emailIntent, 1);
    }

    String siteValue;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {return;}
        siteValue = data.getStringExtra("siteValue");
        chooseWebsiteButton.setVisibility(View.INVISIBLE);

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this::onSearchClick);
        searchButton.setVisibility(View.VISIBLE);
    }

    private void onSearchClick(View button) {
        Toast.makeText(getApplicationContext(), siteValue,
            Toast.LENGTH_LONG).show();
        Intent searchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + siteValue));
        startActivity(searchIntent);
    }

}














