package com.button.tryme;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	int COUNTER = 1;
	MediaPlayer mp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_fullscreen);

		setContentView(R.layout.activity_fullscreen);
		
	}
	
	
	public void playSound(View view) { 
		
		 if (mp != null && mp.isPlaying()) {
             mp.stop();
             mp.release();
             mp = null;
         }
 
		 mp = new MediaPlayer();

	    try {
	
	     if (COUNTER >= 7) {
	    	 COUNTER = 1;
	     } 

	    	AssetFileDescriptor descriptor = getAssets().openFd("sound"+(COUNTER++)+".mp3");
	        mp.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(), descriptor.getLength());    
	        mp.prepare();
	        mp.start();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}  


	


}
