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

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;

import edu.utep.cybershare.DerivA.util.AlfrescoClient;


@SuppressWarnings("serial")
public class SourcesList extends IndividualList {

	private Vector<Individual> individuals;
	private AlfrescoClient aClient;

	public SourcesList(AlfrescoClient ac){
		aClient = ac;
		queryPMLP();
	}

	private void queryPMLP(){
		individuals = new Vector<Individual>();

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

		String pml_j = aClient.executeQuery(query);

		ResultSet results = ResultSetFactory.fromXML(pml_j);

		//		System.out.println(pml_j);
		//		System.out.println(results);

		String personName = "";
		String personURI = "";

		if(results != null)
			while(results.hasNext()){

				QuerySolution QS = results.nextSolution();

				personName = QS.get("?NAME").toString();

				personName = personName.substring(0, personName.indexOf('^'));

				//				if(personName.contains("/")){
				//					personName = personName.substring(personName.lastIndexOf('/') + 1);
				//				}

				personURI = QS.get("?URI").toString();

				if(personName != null && personName.length() > 1){
					individuals.add(new Individual(personURI, personName, personURI));
				}

			}

	}

	public Vector<Individual> getSourceList(){return individuals;}

}
