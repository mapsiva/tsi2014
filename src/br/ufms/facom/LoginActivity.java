package br.ufms.facom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.ufms.facom.task.LoginTask;

public class LoginActivity extends Activity implements View.OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final Button buttonLogin = (Button) findViewById(R.id.btnLogin);
		buttonLogin.setOnClickListener(this);
		
		final Button buttonRegister = (Button) findViewById(R.id.btnRegister);
		buttonLogin.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btnLogin:
				new LoginTask(this).execute();
				break;
		
		}
	}
}
