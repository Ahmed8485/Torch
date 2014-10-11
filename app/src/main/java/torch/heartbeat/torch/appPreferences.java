package torch.heartbeat.torch;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Rana Ahmed on 10/11/2014.
 */
public class appPreferences extends PreferenceActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);

    }
}
