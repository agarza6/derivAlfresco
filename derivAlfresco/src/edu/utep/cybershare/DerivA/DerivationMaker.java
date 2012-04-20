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

package edu.utep.cybershare.DerivA;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import edu.utep.cybershare.DerivA.util.*;
import edu.utep.cybershare.DerivAUI.components.IndividualList.Individual;

public class DerivationMaker {
	
	private AlfrescoClient aClient;
	private String password, username;
	private String project;
	private String serverPath;
	private File file;
	
	private String agentURI, inferenceRuleURI;
	private String conclusionURI, conclusionFormatURI, conclusionTypeURI;
	private Vector<Individual> antecedentURIs;
	
	private static final String DATE_FORMAT_NOW = "YYYY-MM-DDTHH:MM:SSZ";
	
	public void setUsername(String name){username = name;}
	public void setUserPassword(String pass){password = pass;}
	public void setCIProjectName(String name){project = name;}
	public void setCIServerPath(String path){serverPath = path;}
	public void setAgentURI(String URI){agentURI = URI;}
	public void setInferenceRuleURI(String URI){inferenceRuleURI = URI;}
	public void setConclusionURI(String URI){conclusionURI = URI;}
	public void setConclusionFormatURI(String frmat){conclusionFormatURI = frmat;}
	public void setConclusionTypeURI(String type){conclusionTypeURI = type;}
	public void setAntecedentURIs(Vector<Individual> URIs){antecedentURIs = URIs;}
	public void setFile(File f){file = f;}
	
	public DerivationMaker(AlfrescoClient ac){
		aClient = ac;
	}
	
	public String getDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
	public void generateDerivation(){
		
		NodeSetBuilder NSB = new NodeSetBuilder(aClient);

		String dataFileName = file.getName();
		dataFileName = dataFileName.replaceAll("(\\r|\\n)", "");
		dataFileName = dataFileName.replaceAll(" ", "_");
		conclusionURI = aClient.uploadFile(project, file);
		
		try {
			
			dataFileName = URLEncoder.encode(dataFileName, "UTF-8");
			NSB.projectName = project;
			NSB.artifactURI = conclusionURI;
			NSB.formatURI = conclusionFormatURI;
			NSB.docTypeURI = conclusionTypeURI;
			NSB.antecedentsURIs = antecedentURIs;
			NSB.IRURI = inferenceRuleURI;
			NSB.agentURI = agentURI;
			
			
		} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		
		NSB.fileName = dataFileName;
		String pmljURI = NSB.derivateArtifact();
		
		//Aggregate to a Triple Store HERE
		aClient.crawlProject(project);
	}
	
}
