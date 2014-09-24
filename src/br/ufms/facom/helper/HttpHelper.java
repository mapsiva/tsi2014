package br.ufms.facom.helper;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpHelper
{
	private LogHelper logHelper;
	
	public HttpHelper()
	{
		logHelper = new LogHelper(this.getClass());
	}
	
	public HttpResponse doHttpGet(String uri) throws Exception
	{
		try
		{
			HttpClient http = new DefaultHttpClient();
			
			HttpGet request = new HttpGet(uri);
			
			logHelper.debug(request);
			
			HttpResponse response = http.execute(request);
			
			logHelper.debug(response);
			
			return response;
		}
		catch (ClientProtocolException e)
		{
			throw new Exception("Problemas na conexão com o servidor!", e);
		}
		catch (IOException e)
		{
			throw new Exception("Sem rede de dados! Impossível atualizar imformações do aplicativo", e);
		}
	}
	
	public String getResponseContentString(HttpResponse response) throws Exception
	{
		try
		{
			String conversionResult = EntityUtils.toString(response.getEntity());
			
			logHelper.debug("Obtendo conteudo da HTTP response: " + conversionResult);
			
			return conversionResult;
		}
		catch (ParseException e)
		{
			throw new Exception("Falha na interpretação na resposta do servidor!", e);
		}
		catch (IOException e)
		{
			throw new Exception("Falha na interpretação na resposta do servidor!", e);
		}
	}
}
