package com.rick.typespeed;

import android.app.Service;

import android.content.Intent;
import android.os.IBinder;

public class InputCheckService extends Service implements Runnable {

	private long startTime;
	
	private Thread thread;
	private boolean flag = true;
			
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent,  startId);
		// save current time
		startTime = System.currentTimeMillis();
		System.out.println("InputCheckService started");
		if(thread != null)
		{
			flag = false;
			thread = null;
		}
		else
		{
			flag = true;
			thread = new Thread(this);
			thread.start();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag)
		{
			
		}
	}
}
