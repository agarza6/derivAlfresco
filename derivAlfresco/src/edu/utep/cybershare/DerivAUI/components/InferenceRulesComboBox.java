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

public class InferenceRulesComboBox extends IndividualComboBox {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Integer> prettyNames = new HashMap<String,Integer>();
	private String Ontology = null;
	private AlfrescoClient aClient;

	public void setOntology(String ont) {
		Ontology = ont;
	}

	public InferenceRulesComboBox(boolean bol) {
		super();
		if(bol)
			queryAgents();
	}

	public InferenceRulesComboBox(AlfrescoClient ac) {
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



		String rules = "", query = "";
		if(Ontology == null){
			query = "SELECT DISTINCT ?inferenceRule ?ruleLabel ?ruleClass WHERE {?inferenceRule <http://www.w3.org/2000/01/rdf-schema#subClassOf> " +
					"<http://inference-web.org/2.0/pml-provenance.owl#MethodRule> . " +
					"?inferenceRule <http://www.w3.org/2000/01/rdf-schema#label> ?ruleLabel . " +
					"?inferenceRule a ?ruleClass . " +
					"FILTER (! regex(str(?ruleClass), \".*Thing.*\",\"i\")) . " +
					"FILTER (! regex(str(?ruleClass), \".*Rule.*\",\"i\")) .}" +
					"ORDER BY ?ruleLabel";
		}else{
			query = "SELECT DISTINCT ?inferenceRule ?ruleLabel ?ruleClass " +
					"WHERE {?inferenceRule <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://inference-web.org/2.0/pml-provenance.owl#MethodRule> . " +
					"?inferenceRule <http://www.w3.org/2000/01/rdf-schema#label> ?ruleLabel . " +
					"?inferenceRule a ?ruleClass . " +
					"FILTER (! regex(str(?ruleClass), \".*Thing.*\",\"i\")) . " +
					"FILTER (! regex(str(?ruleClass), \".*Rule.*\",\"i\")) . " +
					"FILTER regex(str(?ruleClass),\" " + Ontology  + " .*\", \"i\") .} " +
					"ORDER BY ?ruleLabel";
		}

		rules = aClient.executeQuery(query);
		ResultSet results = ResultSetFactory.fromXML(rules);

		String rule, IRClass, IRName;

		individuals.add(new Individual("Choose Inference Rule", " -- Choose Inference Rule -- ", "Choose Inference Rule"));

		// try web service and update local store	
		if(results != null)
			while(results.hasNext())
			{
				QuerySolution QS = results.nextSolution();

				rule = QS.get("?inferenceRule").toString();
				IRName = QS.get("?ruleLabel").toString();
				IRClass = QS.get("?ruleClass").toString();
				
				String prettyName = IRName;

				if(!IRClass.substring(IRClass.lastIndexOf('/') + 1).equalsIgnoreCase("rdf-schema#Class")){
					rule = null;
				}

				if(prettyName.contains("@")){
					prettyName = prettyName.substring(0, prettyName.indexOf('@'));
				}

				if(prettyName.startsWith("\"")){
					prettyName = prettyName.substring(1);
				}

				//				if(prettyName.equalsIgnoreCase("null") || prettyName.equalsIgnoreCase(""))
				//					prettyName = stripURI(rule);

				if(rule != null && prettyName != null)
				{
					individuals.add(new Individual(rule, prettyName, rule));
				}

				//			System.out.println(results.nextSolution().get("?x").toString());
			}
		// if web service not reached, populate from local store

		this.setIndividuals(individuals);
	}
}
