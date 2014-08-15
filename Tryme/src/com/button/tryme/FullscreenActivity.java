package com.button.tryme;

import java.io.File;
import java.io.IOException;



import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
	boolean RECORD_ON = false;
	private static String ENV = null;
	MediaPlayer mp = null;
	MediaRecorder mr = null;
	CountDownTimer count = null;
	
	public FullscreenActivity() {
		ENV = Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_fullscreen);

		setContentView(R.layout.activity_fullscreen);
		
	}
	
	
	public void playSound(View view) { 
		
		ImageView image = (ImageView)findViewById(R.id.imageView1);
    	image.setImageResource(R.drawable.cross);
    	
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

	     
	    	AssetFileDescriptor descriptor = null;
	    	boolean newsoundExists = false;
	    	try {
	    	  File f = new File(ENV + "/newsound"+(COUNTER)+".mp3");
	    	  
	    	  if (f.exists()) {
	    	    mp.setDataSource(ENV + "/newsound"+(COUNTER)+".mp3");
	    	    newsoundExists = true;

		    	TextView text = (TextView)findViewById(R.id.text2);
		    	text.setText("newsound"+(COUNTER)+".mp3");
	    	  }
	    	  
	    	} catch (Exception e) {
		        e.printStackTrace();
		    }
	    	
	    	if (newsoundExists == false) {
	    		descriptor = getAssets().openFd("sound"+(COUNTER)+".mp3");
	    		TextView text = (TextView)findViewById(R.id.text2);
		    	text.setText("sound"+(COUNTER)+".mp3");
	    		mp.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(), descriptor.getLength());
	          }
	    	
  
	        mp.prepare();
	        mp.start();
	        COUNTER++;
	        image = (ImageView)findViewById(R.id.imageView1);
	    	image.setImageResource(R.drawable.button1);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}  
	
	public void onRecordClick(View view) { 
		
		
		
		 if (RECORD_ON == false) {
	            startRecording();
	            ImageView image = (ImageView)findViewById(R.id.imageView2);
		    	image.setImageResource(R.drawable.stop);
	        } else {
	            stopRecording();
	            ImageView image = (ImageView)findViewById(R.id.imageView2);
		    	image.setImageResource(R.drawable.recbutton);
	        }
}  


	 public void startRecording() {
	        mr = new MediaRecorder();
	        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
	        mr.setMaxDuration(10000);
	        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	        mr.setAudioEncodingBitRate(128);
	        mr.setOutputFile(ENV + "/newsound"+(COUNTER)+".mp3");
	        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

	        try {
	            mr.prepare();
	        } catch (IOException e) {
	            Log.e("", "prepare() failed");
	        }
	        mr.start();
	        
	        
	        
	        count = new CountDownTimer(10000, 1000) {
	        	
	        	TextView text = (TextView)findViewById(R.id.text1);

	            public void onTick(long millisUntilFinished) {
	            	
	            	
	            	text.setText("Secs left: " + millisUntilFinished / 1000);
	            }

	            public void onFinish() {
	                text.setText("done!");
	            }
	         }.start();
	         
	        RECORD_ON = true;
	    }

	    private void stopRecording() {
	    	count.cancel();
	    	TextView text = (TextView)findViewById(R.id.text1);
	    	text.setText("done!");
	    	ImageView image = (ImageView)findViewById(R.id.imageView1);
	    	image.setImageResource(R.drawable.button1);
	        mr.stop();
	        mr.release();
	        mr = null;
	        RECORD_ON = false;
	    }	
	    
	    public void removeSound(View view) { 
	    	try {
		    	  File f = new File(ENV + "/newsound"+(COUNTER-1)+".mp3");
		    	  
		    	  if (f.exists()) {
		    	    f.delete();
		    	    TextView text = (TextView)findViewById(R.id.text2);
			    	text.setText("Done!! newsound"+(COUNTER-1)+".mp3");
		    	  }
		    	  
		    	} catch (Exception e) {
			        e.printStackTrace();
			    }
			 }


}
