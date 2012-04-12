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

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;

import edu.utep.cybershare.DerivA.util.AlfrescoClient;

public class AgentComboBox extends IndividualComboBox {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Integer> prettyNames = new HashMap<String,Integer>();
	private AlfrescoClient aClient;
	
	public AgentComboBox(boolean bol) {
		super();
		if(bol)
			queryAgents();
	}
	
	public AgentComboBox(AlfrescoClient ac) {
		super();
		aClient = ac;
	}
	
	public static String stripURI(String formatURI)
	{
		int start = formatURI.indexOf('#') + 1;
		String name = formatURI.substring(start);
		
		return name;
	}

	public void queryAgents() {
		Vector<Individual> individuals = new Vector<Individual>();

		String query = "SELECT ?inferenceEngine WHERE {?inferenceEngine a <http://inference-web.org/2.0/pml-provenance.owl#Agent> .}";

		String agents = aClient.executeQuery(query);
		
		ResultSet results = ResultSetFactory.fromXML(agents);
		
//		System.out.println(agents);
//		System.out.println(results);
		
		String format;

		individuals.add(new Individual("Choose Agent", " -- Choose Agent -- ", "Choose Agent"));
		
		// try web service and update local store	
		if(results != null)
			while(results.hasNext())
			{
				
				format = results.nextSolution().get("?inferenceEngine").toString();

				String prettyName = stripURI(format);		
				if(format == null || prettyName == null)
				{
					System.out.println("Null Pretty Name Conversion");
					break;
				}
				else
				{
					individuals.add(new Individual(format, prettyName, format));
				}

				//			System.out.println(results.nextSolution().get("?x").toString());
			}
		// if web service not reached, populate from local store

		this.setIndividuals(individuals);
	}
}
