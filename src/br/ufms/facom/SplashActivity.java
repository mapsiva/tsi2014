package br.ufms.facom;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity
{
	private Timer mSplash;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				Intent i = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(i);
			}
		};
		
		mSplash = new Timer();
		
		mSplash.schedule(task, 3000);
		
	}
	
	// killing myself!
	@Override
	protected void onRestart()
	{
		super.onRestart();
		
		this.finish();
	}
}
