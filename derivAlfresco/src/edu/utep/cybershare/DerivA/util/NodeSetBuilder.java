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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.inference_web.pml.v2.pmlp.IWAgent;
import org.inference_web.pml.v2.pmlp.IWInformation;
import org.inference_web.pml.v2.util.PMLObjectManager;
import org.inference_web.pml.v2.vocabulary.PMLP;

import pml.dumping.writer.NodesetWriter;
import edu.utep.cybershare.DerivAUI.components.IndividualList.Individual;

public class NodeSetBuilder {

	//General Variables
	private AlfrescoClient aClient;
	public String[] sources;
	public String pmljURI;
	public String dateTime;
	public String artifactURI;
	public String formatURI;
	public String fileName;

	//Assertion Variables
	public String docTypeURI;

	//Derivation Variables
	public String agentURI;
	public String IRURI;
	public Vector<Individual> antecedentsURIs;

	public String ServerURL, projectName;
	public String username, password;

	public NodeSetBuilder(){}

	public NodeSetBuilder(AlfrescoClient AC){
		aClient = AC;
	}

	public NodeSetBuilder(String user, String pass, String server, String proj){
		ServerURL = server;
		projectName = proj;
		username = user;
		password = pass;

		aClient = new AlfrescoClient(user, pass, server);

	}

	public String assertArtifact(){

		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal;
		String time = "";

		try
		{
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			time = xgcal.toXMLFormat();
		}
		catch(Exception e)
		{e.printStackTrace();}

		NodesetWriter wtr = new NodesetWriter();

		//Set Information Instance (type)
		//		wtr.setInformationByClass(docTypeURI);
		System.out.println("docTypeURI: " + docTypeURI);
		wtr.setInformation(docTypeURI);

		//Set Time
		System.out.println("time: " + time);
		wtr.setTimestamp(time);

		//Set Conclusion
		System.out.println("artifactURI: " + artifactURI);
		System.out.println("formatURI: " + formatURI);
		wtr.setConclusionAsURL(artifactURI, formatURI);

		//Set Rule
		wtr.setRule("http://inference-web.org/registry/DPR/Told.owl#Told");

		String uniqueFileName = wtr.setFileName(fileName);

		//Log in to Alfresco and Create Node
		if(aClient == null){
			aClient = new AlfrescoClient();
			aClient.logIn(username, password, ServerURL);
		}

		String node_pml_url = aClient.createNode(projectName, uniqueFileName);

		//Set Paths
		wtr.setBasePath("");
		wtr.setBaseURL(aClient.getBaseUrl() + node_pml_url.substring(0,node_pml_url.lastIndexOf('/')));

		//Set Source
		String[] aSource = sources;
		wtr.setSource(aSource);

		wtr.setIdentifier();

		return writePMLToServer(wtr);
	}

	public String derivateArtifact(){

		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal;
		String time = "";

		try
		{
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			time = xgcal.toXMLFormat();
		}
		catch(Exception e)
		{e.printStackTrace();}

		NodesetWriter wtr = new NodesetWriter();

		//Set Antecedents
		for(Iterator<Individual> iter = antecedentsURIs.iterator(); iter.hasNext();){
			Individual URI = iter.next();
			//		nodeSet = (IWNodeSet) PMLObjectManager.createPMLObject(PMLJ.NodeSet_lname);

			wtr.addAntecedent(URI.getURI());
		}

		//Set Time
		wtr.setTimestamp(time);

		//Set Paths
		String uniqueFileName = wtr.setFileName(fileName);

		//Log in to Alfresco and Create Node
		if(aClient == null){
			aClient = new AlfrescoClient();
			aClient.logIn(username, password, ServerURL);
		}

		String node_pml_url = aClient.createNode(projectName, uniqueFileName);

		//Set Paths
		wtr.setBasePath("");
		wtr.setBaseURL(aClient.getBaseUrl() + node_pml_url.substring(0,node_pml_url.lastIndexOf('/')));


		//Set Conclusion Information
		wtr.setInformation(docTypeURI);
		wtr.setConclusionAsURL(artifactURI, formatURI);
		wtr.setEngine(agentURI);
		wtr.setRule(IRURI);

		wtr.setIdentifier();

		return writePMLToServer(wtr);
	}

	public String createAgent(String id, String agentName, String description, String URL){

		id.replaceAll("(\\r|\\n)", "");
		URL.replaceAll("(\\r|\\n)", "");

		IWAgent agent = (IWAgent)PMLObjectManager.createPMLObject(PMLP.Agent_lname);

		agent.setIdentifier(PMLObjectManager.getObjectID(id));

		agent.setHasName(agentName);

		IWInformation info = (IWInformation)PMLObjectManager.createPMLObject(PMLP.Information_lname);
		info.setHasRawString(description);
		info.setHasLanguage("http://inference-web.org/registry/LG/English.owl#English");
		info.setHasURL(URL);

		agent.addHasDescription(info);

		//		IWInferenceRule IR = (IWInferenceRule)PMLObjectManager.createPMLObject(PMLP.InferenceRule_lname);
		//		IR.setIdentifier(PMLObjectManager.getObjectID("http://someuri2.owl#uri"));
		//		IR.setHasName("SOMENAME");
		//		IR.setHasOwner(agent);
		//		PMLObjectManager.savePMLObject(IR, "c:/IR.owl");

		String AgentString = PMLObjectManager.printPMLObjectToString(agent);
		return AgentString;

		//		PMLObjectManager.savePMLObject(agent, "c:/agent.owl");

	}

	private String writePMLToServer(NodesetWriter wtr){

		/**
		 * START UPLOAD PROCESS
		 */

		//Get Name w/ ID
		String resultURI = wtr.getIWNodeSet().getIdentifier().getURIString();
		String tempFileName = resultURI.substring(resultURI.lastIndexOf('/') + 1, resultURI.lastIndexOf('#'));

		//Log in to Alfresco and Create Node
		if(aClient == null){
			aClient = new AlfrescoClient();
			aClient.logIn(username, password, ServerURL);
		}

		//String node_pml_url = aClient.createNode(projectName, tempFileName);
		String node_pml_url = "/d/a/workspace/SpacesStore/" + aClient.getObjectUuid("Projects/" + projectName + "/" + tempFileName) + "/" + tempFileName;

		

		// Create temp file to write PML
		try{
			FileWriter fstream = new FileWriter(tempFileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("");
			fstream.close();
			out.close();
		}catch (Exception e){System.err.println("Error: " + e.getMessage());}

		//setNewBaseURL and Write PML
//		String base = node_pml_url.substring(0,node_pml_url.lastIndexOf('/'));
//		wtr.setBaseURL(aClient.getBaseUrl() + base);
		String resultURI2 = wtr.writePML();

		//Create temp file with Provenance to upload
		File pmlFile = new File(tempFileName);
		pmlFile.deleteOnExit();

		//Update dummy BaseURL with new real Alfresco URL
		//updatePMLReference(pmlFile, "http://EMPTYSPACETOREPLACE", aClient.getBaseUrl() + base);

		aClient.addContentToNode(node_pml_url, pmlFile);

		return resultURI2;
	}

//	private void updatePMLReference(File file, String oldLine, String newLine){
//		try{
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//			String line = "", oldtext = "";
//			while((line = reader.readLine()) != null)
//			{
//				oldtext += line + "\r\n";
//			}
//			reader.close();
//
//			//To replace a line in a file
//			String newtext = oldtext.replaceAll(oldLine, newLine);
//
//			FileWriter writer = new FileWriter(file.getName());
//			writer.write(newtext);
//			writer.close();
//		}catch (IOException ioe){ioe.printStackTrace();
//		}
//	}

	public static void main(String[] args){

		NodeSetBuilder ns = new NodeSetBuilder();

		//General
		ns.username = "admin";
		ns.password = "admin";
		ns.ServerURL = "http://localhost:8080/alfresco";
		ns.projectName = "ProjectY";

		//Assertion
		ns.docTypeURI = "http://rio.cs.utep.edu/ciserver/ciprojects/wdo/Visualization-Parameters#d14";
		ns.artifactURI = "http://example.com";
		ns.formatURI = "http://rio.cs.utep.edu/ciserver/ciprojects/formats/PLAINTEXT.owl#PLAINTEXT";
		ns.fileName = "Provenance";
		ns.sources = new String[1];
		ns.sources[0] = "http://somesource.owl#source";

		//Derivation
		ns.agentURI = "http://someengine.owl";
		ns.IRURI = "http://rio.cs.utep.edu/ciserver/ciprojects/wdo/AerostatWDO#m2";

		ns.assertArtifact();

	}
}

