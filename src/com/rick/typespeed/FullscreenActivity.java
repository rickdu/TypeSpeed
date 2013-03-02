package com.rick.typespeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);
        
    }
    
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    	case R.id.startButton:
    		startTesting();
    		break;
    	case R.id.optionButton:
    		showOptions();
    		break;
    	case R.id.rankButton:
    		showRanks();
    		break;
    	}
    }
    
    private void showRanks()
    {
    	
    }
    
    private void showOptions()
    {
    	
    }
    
    private void startTesting()
    {
    	// show activity
    	Intent intent = new Intent(this, InputActivity.class);
    	startActivity(intent);
    	// start monitor
    	startMonitor();
    }
    
    private void startMonitor()
    {
    	Intent intent = new Intent(this, InputCheckService.class);
    	startService(intent);
    }

}
