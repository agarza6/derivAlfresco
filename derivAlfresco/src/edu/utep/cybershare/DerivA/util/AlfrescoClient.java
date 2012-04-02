/**
Copyright (c) 2012, University of Texas at El Paso
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation 
and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH 
DAMAGE.
 */

package edu.utep.cybershare.DerivA.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.swing.JOptionPane;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;

import edu.utep.cybershare.DerivAUI.DerivAUI;

public class AlfrescoClient extends javax.swing.JFrame {

	// Variables declaration - do not modify
	private javax.swing.JLabel Label;
	private javax.swing.JLabel Label2;
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel passwordLabel;
	private javax.swing.JPasswordField passwordText;
	private javax.swing.JLabel projectLabel;
	private javax.swing.JTextField projectText;
	private javax.swing.JSeparator separator;
	private javax.swing.JLabel serverLabel;
	private javax.swing.JTextField serverText;
	private javax.swing.JButton submitButton;
	private javax.swing.JLabel usernameLabel;
	private javax.swing.JTextField usernameText;
	// End of variables declaration

	public DerivAUI inst;

	protected HttpConnectionManager connectionManager;
	protected String username;
	protected String password;
	protected String server, project;
	protected static String alfrescoBaseUrl;
	protected boolean loggedIn = false;

	public static final String PATH_SERVICE = "/service/";

	public AlfrescoClient() {

		// Use the example from CommonsHTTPSender - we need to make sure connections are freed properly
		MultiThreadedHttpConnectionManager cm = new MultiThreadedHttpConnectionManager();
		cm.getParams().setDefaultMaxConnectionsPerHost(5);
		cm.getParams().setMaxTotalConnections(5);
		cm.getParams().setConnectionTimeout(8000);
		this.connectionManager = cm;
	}

	public AlfrescoClient(String user, String pass, String server){

		// Use the example from CommonsHTTPSender - we need to make sure connections are freed properly
		MultiThreadedHttpConnectionManager cm = new MultiThreadedHttpConnectionManager();
		cm.getParams().setDefaultMaxConnectionsPerHost(5);
		cm.getParams().setMaxTotalConnections(5);
		cm.getParams().setConnectionTimeout(8000);
		this.connectionManager = cm;

		this.logIn(user, pass, server);

	}

	/**
	 * Create Alfresco Login Object with DerivaUI instance.
	 * @param i
	 */
	public AlfrescoClient(DerivAUI i) {

		inst = i;
		initComponents();

		// Use the example from CommonsHTTPSender - we need to make sure connections are freed properly
		MultiThreadedHttpConnectionManager cm = new MultiThreadedHttpConnectionManager();
		cm.getParams().setDefaultMaxConnectionsPerHost(5);
		cm.getParams().setMaxTotalConnections(5);
		cm.getParams().setConnectionTimeout(8000);
		this.connectionManager = cm;

		setVisible(true);
	}

	/**
	 * ALFRESCO USER INTERFACE CODE
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {

		Label = new javax.swing.JLabel();
		usernameLabel = new javax.swing.JLabel();
		passwordLabel = new javax.swing.JLabel();
		usernameText = new javax.swing.JTextField();
		cancelButton = new javax.swing.JButton();
		submitButton = new javax.swing.JButton();
		passwordText = new javax.swing.JPasswordField();
		separator = new javax.swing.JSeparator();
		serverLabel = new javax.swing.JLabel();
		serverText = new javax.swing.JTextField();
		projectLabel = new javax.swing.JLabel();
		projectText = new javax.swing.JTextField();
		Label2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		this.setTitle("Alfresco Server Login");

		Label.setText("Set Authentication ");

		usernameLabel.setText("Username:");

		passwordLabel.setText("Password:");

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		submitButton.setText("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});

		serverLabel.setText("Server: ");

		serverText.setText("http://localhost:8080/alfresco");

		projectLabel.setText("Project: ");

		Label2.setText("User Credentials");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(submitButton)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cancelButton))
										.addComponent(separator)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(serverLabel)
														.addComponent(projectLabel))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(serverText)
																.addComponent(projectText)))
																.addGroup(layout.createSequentialGroup()
																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(Label)
																				.addComponent(Label2))
																				.addGap(0, 0, Short.MAX_VALUE))
																				.addGroup(layout.createSequentialGroup()
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(passwordLabel)
																								.addComponent(usernameLabel))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(usernameText)
																										.addComponent(passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
																										.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(Label)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(serverLabel)
								.addComponent(serverText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(projectLabel)
										.addComponent(projectText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(Label2)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(usernameLabel)
												.addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(passwordLabel)
														.addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(cancelButton)
																.addComponent(submitButton))
																.addContainerGap())
				);
		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>

	public String getUsername(){return username;}
	public String getPassword(){return password;}
	public boolean isLoggedIn(){return loggedIn;}

	public void setBaseUrl(String alfrescoBaseUrl) {
		if(alfrescoBaseUrl.endsWith("/")){
			alfrescoBaseUrl = alfrescoBaseUrl.substring(0,alfrescoBaseUrl.length()-1);
		}
		this.alfrescoBaseUrl = alfrescoBaseUrl;
	}

	public String getBaseUrl() {
		return this.alfrescoBaseUrl;
	}

	public boolean setAuthentication(String username, String password) throws Exception {

		this.username = username;
		this.password = password;

		// Try to access webdav URL to see if credentials are valid
		StringBuilder url = getWebdavUrl();
		GetMethod getMethod = new GetMethod();

		try {
			String encodedUrl = encode(url);
			String charset = getMethod.getParams().getUriCharset();
			URI uri = new URI(encodedUrl, true, charset);

			getMethod.setURI(uri);

			/////////////////////////////
			HttpClient httpclient = getHttpClient();
			httpclient.executeMethod(getMethod);

			if (getMethod.getStatusCode() == HttpStatus.SC_UNAUTHORIZED){
				JOptionPane.showMessageDialog(null, "User credentials not valid.");
				return false;
			}
			/////////////////////////////


		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			getMethod.releaseConnection();
		}

		return true; 
	}

	protected StringBuilder getWebdavUrl() {
		StringBuilder url = new StringBuilder(alfrescoBaseUrl);
		url.append("/webdav/");
		return url;
	}

	protected void executeMethod(HttpMethod method) throws Exception {
		try {
			HttpClient httpclient = getHttpClient();
			httpclient.executeMethod(method);

			if (method.getStatusCode() == HttpStatus.SC_UNAUTHORIZED)
				throw new Exception("The user is not authorized to perform this operation.");

			if (method.getStatusCode() >= 400) {
				if(method.getStatusCode() == 511) {
					throw new RuntimeException("A resource with this name already exists!");

				} else {
					String response = method.getResponseBodyAsString();
					throw new RuntimeException("Error sending content: " + method.getStatusLine().toString() + "\n" + response);
				}
			}
		} catch (HttpException e) {
			throw new RuntimeException("Error sending content.", e);
		} catch (IOException e) {
			throw new RuntimeException("Error sending content.", e);
		}
	}

	protected HttpClient getHttpClient() {
		// Instantiating the way CommonsHTTPSender does
		HttpClient httpclient = new HttpClient(this.connectionManager);

		// the timeout value for allocation of connections from the pool
		// 0 means infinite timeout
		httpclient.getParams().setConnectionManagerTimeout(0);

		// we're hoping this speeds things up by eliminating the handshake
		//httpclient.getParams().setBooleanParameter(HttpClientParams.PREEMPTIVE_AUTHENTICATION, true);

		if(username != null) {
			Credentials defaultcreds = new UsernamePasswordCredentials(username, password);
			httpclient.getState().setCredentials(AuthScope.ANY, defaultcreds);
		}

		// Set the proxy server
		String proxyHost = System.getProperties().getProperty("http.proxyHost");
		String proxyPort = System.getProperties().getProperty("http.proxyPort");

		if(proxyHost != null && proxyPort != null) {
			System.out.println("HttpClient configuring proxy " + proxyHost + ":" + proxyPort);
			httpclient.getHostConfiguration().setProxy(proxyHost, Integer.valueOf(proxyPort));
		}

		return httpclient;

	}

	protected StringBuilder getCMISWebScriptUrl() {
		StringBuilder url = new StringBuilder(alfrescoBaseUrl);
		url.append(PATH_SERVICE);
		url.append("cmis");
		return url;
	}
	protected StringBuilder getAPIWebScriptUrl() {
		StringBuilder url = new StringBuilder(alfrescoBaseUrl);
		url.append(PATH_SERVICE);
		url.append("api");
		return url;
	}

	public static StringBuilder getDerivAWebScriptUrl() {
		StringBuilder url = new StringBuilder(alfrescoBaseUrl);
		url.append(PATH_SERVICE);
		url.append("derivA/");
		return url;
	}

	//here down was copied from other utility classes
	public static final String SLASH = "/";
	public static final String QUESTION = "?";
	public static final String EQUALS = "=";
	public static final String AMPERSAND = "&";

	/**
	 * Append the given String paths to the {@link StringBuilder} URL, separating with {@link #SLASH}. The {@link StringBuilder} when finished will not end in a {@link #SLASH}.
	 *
	 * @param url
	 *          {@link StringBuilder} to append to
	 * @param paths
	 *          String array (varargs) of paths to append
	 */
	public static void appendPaths(StringBuilder url, String... paths) {
		if (!endsWith(url, SLASH)) {
			url.append(SLASH);
		}

		for (String path : paths) {
			if (!path.isEmpty()) {
				url.append(path);
			}

			if (!endsWith(url, SLASH)) {
				url.append(SLASH);
			}
		}

		if (endsWith(url, SLASH)) {
			url.deleteCharAt(url.length() - 1);
		}
	}

	/**
	 * Test if the given {@link StringBuilder} ends with the given String.
	 *
	 * @param stringBuilder
	 *          {@link StringBuilder} to test
	 * @param value
	 *          String to test if at the end of the {@link StringBuilder}
	 * @return boolean true if the {@link StringBuilder} ends with the String
	 */
	private static boolean endsWith(StringBuilder stringBuilder, String value) {
		int index = stringBuilder.lastIndexOf(value);

		return index == (stringBuilder.length() - 1);
	}

	/**
	 * Append the given request parameter name and value to the {@link StringBuilder} URL.
	 *
	 * @param url
	 *          {@link StringBuilder} to append parameter to
	 * @param name
	 *          String name of the parameter
	 * @param value
	 *          String value of the parameter
	 */
	public static void appendParameter(StringBuilder url, String name, String value) {
		if (!name.isEmpty()) {
			if (!contains(url, QUESTION)) {
				url.append(QUESTION);
			}

			if (!endsWith(url, QUESTION) && !endsWith(url, AMPERSAND)) {
				url.append(AMPERSAND);
			}

			url.append(name);
			url.append(EQUALS);

			if (!value.isEmpty()) {
				url.append(value);
			}
		}
	}

	/**
	 * Test if the given {@link StringBuilder} contains the given String.
	 *
	 * @param stringBuilder
	 *          {@link StringBuilder} to search within
	 * @param value
	 *          String to search for
	 * @return boolean true if the {@link StringBuilder} contains the given String
	 */
	private static boolean contains(StringBuilder stringBuilder, String value) {
		int index = stringBuilder.indexOf(value);

		return index != -1;
	}

	/**
	 * Encode the path and query segments of the given {@link StringBuilder} URL.
	 *
	 * @param url
	 *          {@link StringBuilder} URL with path and query segments
	 * @return String encoded URL
	 */
	public static String encode(StringBuilder url) {
		try {
			return URIUtil.encodePathQuery(url.toString());
		} catch (URIException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * create a black node and return the URI in order to 
	 * later add content to it.
	 * @return URI of node
	 */
	public String createNode(String project, String filename){

		try {
			filename = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		StringBuilder createNodeURL = getDerivAWebScriptUrl();
		createNodeURL.append("createNode");
		appendParameter(createNodeURL, "project", project);
		appendParameter(createNodeURL, "filename", filename);

		GetMethod getMethod = new GetMethod();

		try {
			String encodedUrl = encode(createNodeURL);
			String charset = getMethod.getParams().getUriCharset();
			URI uri = new URI(encodedUrl, true, charset);

			getMethod.setURI(uri);

			executeMethod(getMethod);

			return getMethod.getResponseBodyAsString();

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			System.out.println("Error: Node Already Exists");
			return null;
		} finally {
			getMethod.releaseConnection();
		} 
	}

	public boolean exists(String path){
		try{
			StringBuilder url = getAPIWebScriptUrl();

			appendPaths(url, "path");
			appendPaths(url, "Workspace");
			appendPaths(url, "SpacesStore");
			appendPaths(url, "company_home");
			appendPaths(url, path);

			GetMethod getMethod = new GetMethod();
			String encodedUrl = encode(url);
			String charset = getMethod.getParams().getUriCharset();
			URI uri = new URI(encodedUrl, true, charset);

			getMethod.setURI(uri);
			executeMethod(getMethod);
			System.out.println(getMethod.getResponseBodyAsString());
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * Upload a given file to an already created node in Alfresco
	 */
	public void addContentToNode(String nodeURL, File file){

		String[] uuid = nodeURL.split("/");

		PostMethod postMethod = new PostMethod();

		StringBuilder uploadFileURL = getDerivAWebScriptUrl();
		uploadFileURL.append("fileUploader");
		appendParameter(uploadFileURL, "uuid", uuid[5]);

		try {
			String encodedUrl = encode(uploadFileURL);
			String charset = postMethod.getParams().getUriCharset();
			URI uri = new URI(encodedUrl, true, charset);

			postMethod.setURI(uri);
			postMethod.setContentChunked(true);
			FileRequestEntity fre = new FileRequestEntity(file, null);
			postMethod.setRequestEntity(fre);

			executeMethod(postMethod);

		} catch (IOException e) {
			throw new RuntimeException(e);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}   
	}

	/**
	 * Full process of uploading a file and getting the final URI for the file.
	 * @param project where to upload the file
	 * @param file itself
	 * @return Final URI of the file
	 */
	public String uploadFile(String project, File file){

		String nodeURL = createNode(project, file.getName());

		addContentToNode(nodeURL, file);

		return alfrescoBaseUrl + nodeURL;
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {

		server = serverText.getText();
		project = projectText.getText();
		username = usernameText.getText();
		char[] tempPassword = passwordText.getPassword();
		password = "";
		for(int i = 0; i < tempPassword.length; i++){
			password = password + tempPassword[i];
			tempPassword[i] = 0;
		} 

		try {
			AlfrescoClient tester = new AlfrescoClient();
			tester.setBaseUrl(server);
			loggedIn = tester.setAuthentication(username, password);

			if(inst != null)
				inst.setCredentials(username, password, tester.getBaseUrl(), project);	//derivA Method

			if(loggedIn)
				setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//		setVisible(false);
	}

	public void logIn(String Username, String Password, String Server){
		setBaseUrl(Server);
		try {
			setAuthentication(Username, Password);
		} catch (Exception e) {
			System.out.println("Incorrect Credentials");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {


		//		try{
		//			// Create file 
		//			FileWriter fstream = new FileWriter("temp");
		//			BufferedWriter out = new BufferedWriter(fstream);
		//			out.write("Hello Java");
		//			//Close the output stream
		//			out.close();
		//		}catch (Exception e){//Catch exception if any
		//			System.err.println("Error: " + e.getMessage());
		//		}
		//		
		//		File file = new File("temp");
		//		file.deleteOnExit();
		//		
		//		String nodeURI = "";
		//		AlfrescoClient AC = new AlfrescoClient();
		//		
		//		try {
		//			AC.setBaseUrl("http://localhost:8080/alfresco");
		//			AC.setAuthentication("admin", "admin");
		//
		//			nodeURI = AC.createNode("ProjectX", "Something.owl");
		//			System.out.println(nodeURI);
		//			
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//		
		//		String[] aNodeURI = nodeURI.split("/");
		//		AC.addContentToNode(aNodeURI[5], file);	//aNodeURI[5] == uuid

	}

	public String executeQuery(String query){

		StringBuilder url = getDerivAWebScriptUrl();
		appendPaths(url, "queryrdfstore");
		appendParameter(url, "query", query);
		GetMethod getMethod = new GetMethod();

		try {
			String encodedUrl = encode(url);
			String charset = getMethod.getParams().getUriCharset();
			URI uri = new URI(encodedUrl, true, charset);

			getMethod.setURI(uri);
			executeMethod(getMethod);

			return getMethod.getResponseBodyAsString();

		} catch (IOException e) {
			throw new RuntimeException(e);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}

	public String getPersonsRdf(){

		String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
				"PREFIX pmlj: <http://inference-web.org/2.0/pml-justification.owl#>" +
				"PREFIX pmlp: <http://inference-web.org/2.0/pml-provenance.owl#>" +
				"PREFIX pml-sparql: <http://trust.utep.edu/sparql-pml#>" +
				"PREFIX ds: <http://inference-web.org/2.0/ds.owl#>" +
				"select ?URI ?NAME where {" +
				"?URI a pmlp:Person ." +
				"?URI pmlp:hasName ?NAME ." +
				"}";


		StringBuilder url = getDerivAWebScriptUrl();
		appendPaths(url, "queryrdfstore");
		appendParameter(url, "query", query);
		//		appendParameter(url, "query", "SELECT ?person ?name WHERE {?person a <http://inference-web.org/2.0/pml-provenance.owl#Person> . ?person <http://inference-web.org/2.0/pml-provenance.owl#hasName> ?name .}");
		GetMethod getMethod = new GetMethod();

		try {
			String encodedUrl = encode(url);
			String charset = getMethod.getParams().getUriCharset();
			URI uri = new URI(encodedUrl, true, charset);

			getMethod.setURI(uri);
			executeMethod(getMethod);

			return getMethod.getResponseBodyAsString();

		} catch (IOException e) {
			throw new RuntimeException(e);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}

}