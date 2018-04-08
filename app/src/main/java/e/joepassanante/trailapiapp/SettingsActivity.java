package e.joepassanante.trailapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsHolder.CURRENT_THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.StyleGroup);
        setCheckMark(radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                switch (checkedRadioButton.getText().toString()){
                    case("Blue(Default)"): SettingsHolder.CURRENT_THEME=R.style.BlueTheme;break;
                    case("Purple"): SettingsHolder.CURRENT_THEME=R.style.PurpleTheme;break;
                    case("Orange"):SettingsHolder.CURRENT_THEME=R.style.OrangeTheme;break;
                    case("Green"): SettingsHolder.CURRENT_THEME=R.style.GreenTheme;break;
                }
            }
        });
    }

    public void onApplyClick(View view){
        Log.i("Theme Button","Clicked");
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    private void setCheckMark(RadioGroup radioGroup){
        int count = radioGroup.getChildCount();
        for (int i=0;i<count;i++) {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
                String text = ((RadioButton) o).getText().toString();
                Log.i("text",text);
                if(text.equalsIgnoreCase("Blue(Default)")&&SettingsHolder.CURRENT_THEME==R.style.BlueTheme){
                    ((RadioButton) o).setChecked(true);
                    return;
                }
                if(text.equalsIgnoreCase("Purple")&&SettingsHolder.CURRENT_THEME==R.style.PurpleTheme){
                    ((RadioButton) o).setChecked(true);
                    return;
                }
                if(text.equalsIgnoreCase("Orange")&&SettingsHolder.CURRENT_THEME==R.style.OrangeTheme){
                    ((RadioButton) o).setChecked(true);
                    return;
                }
                if(text.equalsIgnoreCase("Green")&&SettingsHolder.CURRENT_THEME==R.style.GreenTheme){
                    ((RadioButton) o).setChecked(true);
                    return;
                }
            }
        }
    }
}
