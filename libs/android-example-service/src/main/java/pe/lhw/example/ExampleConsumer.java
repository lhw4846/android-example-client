package pe.lhw.example;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/**
 * Created by lhw on 2016-09-09.
 */
public interface ExampleConsumer {
    /**
     * Called when the ExampleService is running and ready to accept your commands through the ExampleManager
     */
    public void onExampleServiceConnect();
    /**
     * Called by the ExampleManager to get the context of your Service or Activity.  This method is implemented by Service or Activity.
     * You generally should not override it.
     * @return the application context of your service or activity
     */
    public Context getApplicationContext();
    /**
     * Called by the ExampleManager to bind your ExampleConsumer to the  ExampleService.  This method is implemented by Service or Activity, and
     * You generally should not override it.
     * @return the application context of your service or activity
     */
    public void unbindService(ServiceConnection connection);
    /**
     * Called by the ExampleManager to unbind your ExampleConsumer to the ExampleService.  This method is implemented by Service or Activity, and
     * You generally should not override it.
     * @return the application context of your service or activity
     */
    public boolean bindService(Intent intent, ServiceConnection connection, int mode);
}
