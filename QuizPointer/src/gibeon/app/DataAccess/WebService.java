package gibeon.app.DataAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebService {

	public static HttpContext CreateContext() {
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		return httpContext;
	}

public static String post(String url, JSONObject jsonObject)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(jsonObject.toString()));
		HttpResponse response = client.execute(request);
		StringBuilder builder = new StringBuilder();

		HttpEntity entity = response.getEntity();

		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));

		String line;

		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		return builder.toString();
	}
public static String post2(String url,JSONArray jsonArray)
		throws ClientProtocolException, IOException {
	HttpClient client = new DefaultHttpClient();
	HttpPost request = new HttpPost(url);
	request.setHeader("Accept", "application/json");
	request.setHeader("Content-Type", "application/json");
	request.setEntity(new StringEntity(jsonArray.toString()));
	HttpResponse response = client.execute(request);
	StringBuilder builder = new StringBuilder();

	HttpEntity entity = response.getEntity();

	InputStream content = entity.getContent();
	BufferedReader reader = new BufferedReader(new InputStreamReader(
			content));

	String line;

	while ((line = reader.readLine()) != null) {
		builder.append(line);
	}

	return builder.toString();
}

public static String post(String url, JSONObject jsonObject,
			HttpContext httpContext) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(jsonObject.toString()));
		HttpResponse response = client.execute(request, httpContext);
		StringBuilder builder = new StringBuilder();

		HttpEntity entity = response.getEntity();

		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));

		String line;

		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		return builder.toString();
	}

public static String get(String url) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		StringBuilder builder = new StringBuilder();

		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));

		String line;

		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		return builder.toString();
	}	

}