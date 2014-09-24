package br.ufms.facom.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import br.ufms.facom.R;

public class LoginTask extends AsyncTask<String, Integer, String>
{
	
	private static ProgressDialog mWaiDialog;
	
	private Activity loginActivity;
	
	public LoginTask(Activity act)
	{
		loginActivity = act;
		
	}
	
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		
		mWaiDialog = ProgressDialog.show(loginActivity, "Please wait...", "Sig in...", true);
		
	}
	
	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);
		
		mWaiDialog.dismiss();
	}
	
	@Override
	protected String doInBackground(String... params)
	{
		doLogin();
		
		return null;
	}
	
	private void doLogin()
	{
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost httpPost = new HttpPost("http://200.129.205.20/~mais/ws.php");
		
		// Add user name and password
		EditText loginTxt = (EditText) loginActivity.findViewById(R.id.txtLogin);
		String username = loginTxt.getText().toString();
		
		EditText passTxt = (EditText) loginActivity.findViewById(R.id.txtPass);
		String password = passTxt.getText().toString();
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		
		// par chave=valor
		nameValuePairs.add(new BasicNameValuePair("username", username));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		
		try
		{
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = httpClient.execute(httpPost);
			
			String str = inputStreamToString(response.getEntity().getContent()).toString();
			
			JSONObject jsonResponse = new JSONObject(str);
			
			Log.d("Login result: ", str);
			Log.d("Login return: ", jsonResponse.getString("name"));
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	private StringBuilder inputStreamToString(InputStream is)
	{
		String line = "";
		StringBuilder total = new StringBuilder();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		
		// lê todos os dados até o fim da requisição
		
		try
		{
			while ((line = rd.readLine()) != null)
			{
				total.append(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return total;
	}
}
