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

package edu.utep.cybershare.DerivAUI.tools;

import java.awt.Cursor;
import java.util.Vector;

import javax.swing.JOptionPane;

import edu.utep.cybershare.DerivA.util.NodeSetBuilder;
import edu.utep.cybershare.DerivAUI.components.IndividualList;
import edu.utep.cybershare.DerivAUI.components.InferenceRulesList;


@SuppressWarnings("serial")
public class AddAgentTool extends javax.swing.JFrame {

	// Variables declaration - do not modify                     
    private javax.swing.JLabel addAgentLabel;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel addIRLabel;
    private javax.swing.JLabel addNameLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTF;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton submitButton;
	
	private IndividualList Avail_IR;
	private IndividualList Selected_IR;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> AvailableIRs;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> SelectedIRs;
	
    private String ServerURL, uploaderName, uploaderPass, CIProject;
	// End of variables declaration         
	
	/** Creates new form addNewAgentTool */
	public AddAgentTool(String tURL, String project, String tUName, String tPass) {
    	uploaderName = tUName;
    	uploaderPass = tPass;
    	ServerURL = tURL;
    	CIProject = project;
		initComponents();
	}

	private void initComponents() {

		addAgentLabel = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		addButton = new javax.swing.JButton();
		removeButton = new javax.swing.JButton();
		addIRLabel = new javax.swing.JLabel();
		addNameLabel = new javax.swing.JLabel();
		nameTF = new javax.swing.JTextField();
		cancelButton = new javax.swing.JButton();
		submitButton = new javax.swing.JButton();
        descriptionLabel = new javax.swing.JLabel();
        descriptionTF = new javax.swing.JTextField();
		
		AvailableIRs = new InferenceRulesList().getPMLList();
		
		Avail_IR = new IndividualList();
		Selected_IR = new IndividualList();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		addAgentLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		addAgentLabel.setText("Add New Agent");

		jScrollPane1.setViewportView(Avail_IR);
		Avail_IR.setModel(AvailableIRs);

		jScrollPane2.setViewportView(Selected_IR);

		addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/001_01.gif"))); // NOI18N
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAction(evt);
			}
		});

		removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/001_02.gif"))); // NOI18N
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAction(evt);
			}
		});
		
		addIRLabel.setText("Add Inference Rules to Agent");

		addNameLabel.setText("Agent Name:");
        descriptionLabel.setText("Description:");

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelAction(evt);
			}
		});

		submitButton.setText("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitAction(evt);
			}
		});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addAgentLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(addIRLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton)
                            .addComponent(removeButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descriptionTF, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addAgentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNameLabel)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addIRLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cancelButton)
                                    .addComponent(submitButton)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeButton)))
                .addContainerGap())
        );
        pack();
		setLocationRelativeTo(null);
	}                       

	public void addAction(java.awt.event.ActionEvent evt){
		Object CSS = Avail_IR.getSelectedValue();
		if(SelectedIRs != null){
			if(!SelectedIRs.contains(CSS)){
				SelectedIRs.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSS);
			}
		}else{
			SelectedIRs = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();
			SelectedIRs.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSS);
		}

		Selected_IR.setModel(SelectedIRs);
		Selected_IR.repaint();
	}
	
	public void removeAction(java.awt.event.ActionEvent evt){
		Object CSA = Selected_IR.getSelectedValue();
		if(SelectedIRs != null){
			if(SelectedIRs.contains(CSA)){
				SelectedIRs.remove(CSA);
			}
		}
		Selected_IR.setModel(SelectedIRs);
		Selected_IR.repaint();
	}
	
	public void cancelAction(java.awt.event.ActionEvent evt){
		setVisible(false);
	}
	
	public void submitAction(java.awt.event.ActionEvent evt){
		
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//fetch all info
		String fullName = nameTF.getText();
		String description = descriptionTF.getText();
		
		NodeSetBuilder NSB = new NodeSetBuilder();
		String AgentString = NSB.createAgent("http://someuri.owl#uri", fullName, description, ServerURL + "pmlp/" + fullName);
			
		//Aggregate to the triple store
		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
}

