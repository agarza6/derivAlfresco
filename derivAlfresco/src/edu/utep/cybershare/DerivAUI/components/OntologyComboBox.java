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

package edu.utep.cybershare.DerivAUI.components;

import java.util.Vector;

import java.util.HashMap;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;

import edu.utep.cybershare.DerivA.util.AlfrescoClient;

public class OntologyComboBox extends IndividualComboBox {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Integer> prettyNames = new HashMap<String,Integer>();
	private AlfrescoClient aClient;
	
	public OntologyComboBox(String project) {
		super();
		queryOntologies(project);
	}
	
	public OntologyComboBox(String project, AlfrescoClient ac) {
		super();
		aClient = ac;
		queryOntologies(project);
	}
	
	public static String stripURI(String formatURI)
	{
		int start = formatURI.indexOf('#') + 1;
		String name = formatURI.substring(start);
		
		return name;
	}

	public void queryOntologies(String project) {
		Vector<Individual> individuals = new Vector<Individual>();

		String query = "PREFIX wdo: <http://trust.utep.edu/2.0/wdo.owl#>" + '\n' + 
						"SELECT ?WDO WHERE {" +  '\n' + 
						"?WDO a wdo:WorkflowDrivenOntology . " + '\n' +  
						"}";

		String WDOs = aClient.executeQuery(query);
		
		ResultSet results = ResultSetFactory.fromXML(WDOs);
		
//		System.out.println(project);
//		System.out.println(WDOs);
//		System.out.println(results);
		
		String WDO, WDOLabel;

		individuals.add(new Individual("none", " No Ontology Selected ", "none"));
		
		// try web service and update local store	
		if(results != null)
			while(results.hasNext())
			{
				QuerySolution QS = results.nextSolution();
				WDO = QS.get("?WDO").toString();
				
				//WDOLabel = QS.get("?wdoLabel").toString();
				WDOLabel = WDO.substring(WDO.lastIndexOf('/') + 1);
		
				if(WDO == null || WDOLabel == null)
				{
					System.out.println("Null Pretty Name Conversion");
					break;
				}
				else
				{
					individuals.add(new Individual(WDO, WDOLabel, WDO));
				}

				//			System.out.println(results.nextSolution().get("?x").toString());
			}
		// if web service not reached, populate from local store

		this.setIndividuals(individuals);
	}
}
