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


public class PMLJList extends IndividualList {

	private Vector<Individual> individuals;
	private AlfrescoClient aClient;

//	public PMLJList(String project){
//		queryPMLJ(project);
//	}

	public PMLJList(String project, AlfrescoClient ac){
		aClient = ac;
		queryPMLJ(project);
	}

	private void queryPMLJ(String project){
		individuals = new Vector<Individual>();
		project = "ALL_PROJECTS_$!!$!";

		String pml_j, query = "";
		if(project.equalsIgnoreCase("ALL_PROJECTS_$!!$!")){
			query = "SELECT ?nodeset ?conclusionURL WHERE {?nodeset a <http://inference-web.org/2.0/pml-justification.owl#NodeSet> . " +
					"?nodeset <http://inference-web.org/2.0/pml-justification.owl#hasConclusion> ?conclusion . " +
					"?conclusion <http://inference-web.org/2.0/pml-provenance.owl#hasURL> ?conclusionURL .}";

		}else{
			query = "SELECT ?nodeset ?conclusionURL WHERE {?nodeset a <http://inference-web.org/2.0/pml-justification.owl#NodeSet> . " +
					"?nodeset <http://inference-web.org/2.0/pml-justification.owl#hasConclusion> ?conclusion . " +
					"?conclusion <http://inference-web.org/2.0/pml-provenance.owl#hasURL> ?conclusionURL . " +
					"FILTER regex(str(?nodeset), \".*" + project + ".*\",\"i\") .}";
		}

		pml_j = aClient.executeQuery(query);
		ResultSet results = ResultSetFactory.fromXML(pml_j);

//		System.out.println(pml_j);
//		System.out.println(results);

		String conclusionName = "";
		String conclusionURI = "";

		if(results != null)
			while(results.hasNext()){

				QuerySolution QS = results.nextSolution();

				conclusionName = QS.get("?nodeset").toString();

				conclusionName = conclusionName.substring(0, conclusionName.indexOf('#'));
				
				if(conclusionName.contains("/")){
					if(conclusionName.lastIndexOf('/') == (conclusionName.length() - 1))
						conclusionName = conclusionName.substring(0, conclusionName.length() - 1);
					else
						conclusionName = conclusionName.substring(conclusionName.lastIndexOf('/') + 1);
				}

				conclusionURI = QS.get("?nodeset").toString();

				if(conclusionName != null && conclusionName.length() > 1){
					individuals.add(new Individual(conclusionURI, conclusionName, conclusionURI));
				}

			}

	}

	public Vector<Individual> getPMLList(){return individuals;}

}
