package br.ufms.facom.helper;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.util.Log;

public class LogHelper
{
	private String tag;
	
	public LogHelper(@SuppressWarnings("rawtypes") Class userClass)
	{
		tag = userClass.getName();
	}
	
	public LogHelper(Object object)
	{
		tag = object.getClass().getName();
	}
	
	public void logError(String message, Throwable throwable)
	{
		Log.e(tag, message, throwable);
	}
	
	public void logError(String message)
	{
		Log.e(tag, message);
	}
	
	public void debug(HttpGet request)
	{
		Log.d(tag, "HTTP GET");
		Log.d(tag, request.getURI().toString());
		
		for (Header header : request.getAllHeaders())
		{
			Log.d(tag, header.getName() + ": " + header.getValue());
		}
	}
	
	public void debug(HttpResponse response)
	{
		Log.d(tag, "HTTP RESPONSE");
		Log.d(tag, "HTTP/" + response.getStatusLine().getProtocolVersion() + " " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
		
		for (Header header : response.getAllHeaders())
		{
			Log.d(tag, header.getName() + ": " + header.getValue());
		}
	}
	
	public void debug(String msg)
	{
		Log.d(tag, msg);
	}
	
}
