package torch.heartbeat.torch;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MyActivity extends ActionBarActivity {
    Camera cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ToggleButton switchTorch = (ToggleButton)findViewById(R.id.Switch);
        switchTorch.setChecked(false);

        switchTorch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switchTorch.isChecked()){
                    cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();

                }
                else{
                    cam.release();
                }


            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent("torch.heartbeat.torch.appPreferences");
            startActivity(i);
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this,"About",Toast.LENGTH_SHORT);
            AboutDialog a = new AboutDialog();
            a.show(getFragmentManager(),"QQQ");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class AboutDialog extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder about = new AlertDialog.Builder(getActivity());

            about.setMessage(Html.fromHtml("<b>About</b><br><hr><br>" +
                    "This App uses your devices LED. <br><br>You can set a Notification to quickly turn LED On or Off.<br>" +
                    "Also you can use a Launcher Widget to quickly Turn LED On or Off."  + "<br><br><i> This is my first PlayStore App<i>" +
                            "<br><br><b>Warning<b><br> Leaving LED on for extended periods can harm your device"
                ));
            about.setTitle("Torchlight");
            about.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            return about.create();
        }

    }
}

