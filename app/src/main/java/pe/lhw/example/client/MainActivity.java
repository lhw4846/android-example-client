package pe.lhw.example.client;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import pe.lhw.example.Example;
import pe.lhw.example.ExampleConsumer;
import pe.lhw.example.ExampleManager;
import pe.lhw.example.ExampleNotifier;

public class MainActivity extends AppCompatActivity implements ExampleConsumer {
    private static final String TAG = "MainActivity";
    private static final int UNIQUE_ID = 0;

    private TextView tv;

    private ExampleManager mExampleManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        // Create example manager
        mExampleManager = ExampleManager.getInstanceForApplication(this);
        mExampleManager.bind(this);
    }

    @Override
    protected void onDestroy() {
        try {
            mExampleManager.stopExampleUpdates(UNIQUE_ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mExampleManager.unBind(this);
        super.onDestroy();
    }

    @Override
    public void onExampleServiceConnect() {
        mExampleManager.setExampleNotifier(new ExampleNotifier() {
            @Override
            public void onExampleChanged(Example example) {
                // events
                if(example != null) {
                    Log.d(TAG, "received data: " + example.getIdata() + ", " + example.getFdata() + ", " + example.getDdata());
                    tv.setText("received data: " + example.getIdata() + ", " + example.getFdata() + ", " + example.getDdata());
                }
            }
        });

        try {
            mExampleManager.startExampleUpdates(UNIQUE_ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
