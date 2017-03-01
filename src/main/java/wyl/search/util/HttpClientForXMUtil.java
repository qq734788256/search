package wyl.search.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientForXMUtil {
	private static final String encoding="UTF-8";
	private final static int connectTimeout = 40000;
	
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;
	
	static {
		try {
			SSLContext sslContext = SSLContexts.custom().useTLS().build();
			sslContext.init(null,
			        new TrustManager[] { new X509TrustManager() {
						
			        	public X509Certificate[] getAcceptedIssuers() {
			                return null;
			            }

			            public void checkClientTrusted(
			                    X509Certificate[] certs, String authType) {
			            }

			            public void checkServerTrusted(
			                    X509Certificate[] certs, String authType) {
			            }
					}}, null);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
		            .register("http", PlainConnectionSocketFactory.INSTANCE)
		            .register("https", new SSLConnectionSocketFactory(sslContext))
		            .build();
			
			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			httpclient = HttpClients.custom().setConnectionManager(connManager).build();
			// Create socket configuration
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
			connManager.setDefaultSocketConfig(socketConfig);
			// Create message constraints
	        MessageConstraints messageConstraints = MessageConstraints.custom()
	            .setMaxHeaderCount(200)
	            .setMaxLineLength(2000)
	            .build();
	        // Create connection configuration
	        ConnectionConfig connectionConfig = ConnectionConfig.custom()
	            .setMalformedInputAction(CodingErrorAction.IGNORE)
	            .setUnmappableInputAction(CodingErrorAction.IGNORE)
	            .setCharset(Consts.UTF_8)
	            .setMessageConstraints(messageConstraints)
	            .build();
	        connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(200);
			connManager.setDefaultMaxPerRoute(20);
		} catch (KeyManagementException e) {
		
		} catch (NoSuchAlgorithmException e) {
			
		}
	}
	
	public static String doPost(String url_str,String param){
		HttpPost post = new HttpPost(url_str);
		try {
		    post.setHeader("Content-type", "application/json");
		    RequestConfig requestConfig = RequestConfig.custom()
		    		.setSocketTimeout(connectTimeout)
		    		.setConnectTimeout(connectTimeout)
		    		.setConnectionRequestTimeout(connectTimeout)
		    		.setExpectContinueEnabled(false).build();
		    post.setConfig(requestConfig);   
		    post.setEntity(new StringEntity(param, encoding));		    
		    CloseableHttpResponse response = httpclient.execute(post);
		    
		    try {
		    	System.out.println(response.getStatusLine().getStatusCode());
			    HttpEntity entity = response.getEntity();
			    try {
					if(entity != null){
						String str = EntityUtils.toString(entity, encoding);						
						return str;
					}
				} finally {
					if(entity != null){
						entity.getContent().close();
					}
				}
		    } finally {
				if(response != null){
					response.close();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return "";
	}
	

	public static String doGet(String url_str) {
		System.out.println(url_str);
		String responseString = null;
	    RequestConfig requestConfig = RequestConfig.custom()
	    		.setSocketTimeout(connectTimeout)
	    		.setConnectTimeout(connectTimeout)
	    		.setConnectionRequestTimeout(connectTimeout).build();
	    
		
		HttpGet get = new HttpGet(url_str);
		get.setConfig(requestConfig);
		get.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
		get.setHeader("Host","api.xiami.com");
		get.setHeader("Referer","https://h.xiami.com/index.html?f=&from=");
		get.setHeader("Cookie","_xiamitoken=53dad51c07768bc557e7f4a61b783549; cna=1f0qEfUzmz4CAWonyC8hZi/A; OUTFOX_SEARCH_USER_ID_NCOO=1771049346.7125103; gid="+SysTimeUtil.getSystemTime()+"35063; join_from=1zufSNtP6D010%2FjCCA; _unsign_token=64f3c7295f8647f2a18a2e1480372620; user_from=1; is_spider=https%3A//www.baidu.com/link%3Furl%3DnJ4qhUFethMV1rMCOOD9S8X-lTelmk1nxfcX6bOdvGS%26wd%3D%26eqid%3D9148d78a000026d60000000358b65a99; isg=Aq-vcEvjK5DXGy-s6I5epShnPsPTw6WAUMKd78E8S54lEM8SySSTxq3G4LrR; l=AgoK4jNXDN9BlX-DXjY-e0J02uus-45V");
		try {
			CloseableHttpResponse response = httpclient.execute(get);
			//System.out.println(response.getStatusLine().getStatusCode());
			try {
				HttpEntity entity = response.getEntity();
//				InputStream is= entity.getContent();
//				if (entity.getContentEncoding().getValue().contains("gzip")) {
//					is= new GZIPInputStream(is);
//				}			
				
				try {
					if(entity != null){
						responseString = EntityUtils.toString(entity, encoding);
					}
				} finally {
					if(entity != null){
						entity.getContent().close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(response != null){
					response.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
		return responseString;
	}
	
	
	/**
	 * HTTPS请求，默认超时为5S
	 * @param reqURL
	 * @param params
	 * @return
	 */
	public static String connectPostHttps(String reqURL, Map<String, String> params) {

		String responseContent = null;
		
		HttpPost httpPost = new HttpPost(reqURL); 
		try {
			RequestConfig requestConfig = RequestConfig.custom()
		    		.setSocketTimeout(connectTimeout)
		    		.setConnectTimeout(connectTimeout)
		    		.setConnectionRequestTimeout(connectTimeout).build();
			
			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); 
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, Consts.UTF_8));
			httpPost.setConfig(requestConfig);
			// 绑定到请求 Entry
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			CloseableHttpResponse response = httpclient.execute(httpPost);
			 try {
				// 执行POST请求
				HttpEntity entity = response.getEntity(); // 获取响应实体
				try {
					if (null != entity) {
						responseContent = EntityUtils.toString(entity, Consts.UTF_8);
					}
				} finally {
					if(entity != null){
						entity.getContent().close();
					}
				}
			} finally {
				if(response != null){
					response.close();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
	    return responseContent;
		
	}

	public static void main(String[] args){
		String url = "https://api.xiami.com/web?v=2.0&app_key=1&key=水木年华&page=1&limit=20&_ksTS=1488345795145_62&r=search/songs";
		System.out.println(HttpClientForXMUtil.doGet(url));
	}
}