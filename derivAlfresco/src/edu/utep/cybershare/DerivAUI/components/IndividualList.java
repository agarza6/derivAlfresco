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

import java.awt.Component;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;


public class IndividualList extends JList {
	private static final long serialVersionUID = 1L;

	public IndividualList() {
		super();
		this.setCellRenderer(new IndividualListCellRenderer());
	}
	
	/**
	 * Sets the model of the list.
	 * Sorts the list being passed using the local name of Individuals as the sorting key 
	 * @param list
	 */
	public void setModel(Vector<Individual> list) {
		// get the local names of individuals to use as key to sort the list
		HashMap<String,Individual> temp = new HashMap<String,Individual>();
		for (Iterator<Individual> i=list.iterator(); i.hasNext(); ) {
			Individual ind = i.next();
			temp.put(ind.getURI(), ind);
			
		}
		
		// sort by local name
		String[] sortedNames = new String[temp.size()];
		temp.keySet().toArray(sortedNames);
		Arrays.sort(sortedNames);
		Individual[] sortedList = new Individual[temp.size()];
		for (int i=0; i<sortedList.length; i++) {
			sortedList[i] = temp.get(sortedNames[i]);
		}
		// set model with sorted list
		setModel(new IndividualListModel(sortedList));
	}
	
    /**
     * Model for Individual list
     * @author Leonardo Salayandia
     */
    private class IndividualListModel extends AbstractListModel {
		private static final long serialVersionUID = 1L;
		Individual[] list;
    	
    	protected IndividualListModel(Individual[] list) {
			super();
			this.list = list;			
		}
		
    	@Override
        public int getSize() { return list.length; }
        
		@Override
		public Object getElementAt(int index) {
			return list[index];
		}
    }
    
	/**
	 * Item used to populate the IndividualComboBox model.
	 * @author Leonardo Salayandia
	 */
	public class Individual implements Comparable<Individual> {
		private String uri;
    	private String friendlyName;
    	private String description;
    	
    	public Individual(String uri, String name, String desc) {
    		this.uri = uri;
    		friendlyName = name;
    		description = desc;
    	}
    	
    	public String getURI() {
    		return uri;
    	}
    	
    	public String getName() {
    		return friendlyName;
    	}
    	
    	public String getDescription() {
    		return description;
    	}
    	
    	@Override
    	public boolean equals(Object o) {
    		if (o != null && o instanceof Individual) {
    			Individual ind = (Individual) o;
    			return getURI().equalsIgnoreCase(ind.getURI());
    		}
    		return false;
    	}
    	
    	@Override
    	public String toString() {
    		return (friendlyName == null || friendlyName.isEmpty()) ? getURI() : getName();
    	}

		@Override
		public int compareTo(Individual o) {
			int ans = toString().compareTo(o.toString());
			// if friendly names are the same and URIs are not the same, compare based on URIs
			if (ans == 0 && !equals(o)) {
				ans = getURI().compareTo(o.getURI());
			}
			return ans;
		}
	}
    
    
    /**
     * Cell renderer for Individual List
     * @author Leonardo Salayandia
     */
    private class IndividualListCellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		
		protected IndividualListCellRenderer() {
			super();
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Individual ind = (Individual) value;
//			setText(SAW.getIndividualLocalName(ind));
			setToolTipText(ind.getURI());
			return this;
		}
    }
}

