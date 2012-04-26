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
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.utep.cybershare.DerivA.util.AlfrescoClient;
import edu.utep.cybershare.DerivAUI.DerivAUI;
import edu.utep.cybershare.DerivAUI.components.*;

/**
 * openOntology.java
 * @ Author Antonio Garza
 * Created on Mar 15, 2011, 11:16:24 AM
 */
public class OpenWorkflowTool extends javax.swing.JFrame {

    /** Creates new form openOntology */
    public OpenWorkflowTool(DerivAUI inst, AlfrescoClient ac) {
    	instance = inst;
    	aClient = ac;
        initComponents();
    }

    private void initComponents() {

        ButtonGroup = new javax.swing.ButtonGroup();
        openOntologyLabel = new javax.swing.JLabel();
        fromLocalRadioButton = new javax.swing.JRadioButton();
        browseButton = new javax.swing.JButton();
        LocalTF = new javax.swing.JTextField();
        fromServerRadioButton = new javax.swing.JRadioButton();
        WFComboBox = new WorkflowComboBox("", aClient);
        cancelButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        fromURIRadioButton = new javax.swing.JRadioButton();
        URITF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        openOntologyLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        openOntologyLabel.setText("Open Workflow");

        fromLocalRadioButton.setText("From Local File System (Not Available)");
        fromLocalRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fromLocalAction(evt);
			}
		});

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				browseAction(evt);
			}
		});

        fromServerRadioButton.setText("Available in Project");
        fromServerRadioButton.setSelected(true);
        fromServerRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fromLocalAction(evt);
			}
		});

        cancelButton.setText("Close");
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

        fromURIRadioButton.setText("From URI");
        fromURIRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fromLocalAction(evt);
			}
		});

        URITF.setText("http://");
        
        ButtonGroup.add(fromServerRadioButton);
        ButtonGroup.add(fromLocalRadioButton);
        ButtonGroup.add(fromURIRadioButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(WFComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromServerRadioButton)
                    .addComponent(fromLocalRadioButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(URITF, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
                    .addComponent(fromURIRadioButton)
                    .addComponent(openOntologyLabel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(browseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LocalTF, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openOntologyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromURIRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(URITF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromLocalRadioButton)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseButton)
                    .addComponent(LocalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromServerRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WFComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(submitButton))
                .addContainerGap())
        );

	    LocalTF.setEnabled(false);
	    WFComboBox.setEnabled(true);
	    browseButton.setEnabled(false);
	    fromLocalRadioButton.setEnabled(false);
        
        pack();
        setLocationRelativeTo(null);
    }
    
	public void fromLocalAction(java.awt.event.ActionEvent evt){
//	    LocalTF.setEnabled(true);
//	    OntologyComboBox.setEnabled(false);
//	    URITF.setEnabled(false);
//	    browseButton.setEnabled(true);
	}
	
	public void fromServerAction(java.awt.event.ActionEvent evt){
	    LocalTF.setEnabled(false);
	    WFComboBox.setEnabled(true);
	    URITF.setEnabled(false);
	    browseButton.setEnabled(false);
	}
	
	public void fromURIAction(java.awt.event.ActionEvent evt){
//	    LocalTF.setEnabled(false);
//	    OntologyComboBox.setEnabled(false);
//	    URITF.setEnabled(true);
//	    browseButton.setEnabled(false);
	}

	public void submitAction(java.awt.event.ActionEvent evt){
//		setCursor(new Cursor(Cursor.WAIT_CURSOR));
//		try {
//		IndividualComboBox.Individual Ontology = (IndividualComboBox.Individual)WFComboBox.getSelectedItem();
//		
//		instance.setSelectedOntology(Ontology.getURI());
//		instance.filterByWDO();
//		setVisible(false);
//		
//		} catch (Throwable e) {
//      e.printStackTrace();
//      String errMsg = "Error opening ontology: " + e.toString();
//      JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
//		  
//		} finally {
//		  setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//		}
	}
    
	public void cancelAction(java.awt.event.ActionEvent evt){
		dispose();
	}
    
	public void browseAction(java.awt.event.ActionEvent evt){
		//Create a file chooser
		fc = new JFileChooser(oFile);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int returnVal = fc.showOpenDialog(OpenWorkflowTool.this);

		String file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) 
			file = fc.getSelectedFile().getAbsolutePath();

		LocalTF.setText(file);
		oFile = fc.getSelectedFile();
	}
    
    // Variables declaration - do not modify
	private DerivAUI instance;
	private AlfrescoClient aClient;
	
    private javax.swing.ButtonGroup ButtonGroup;
    private javax.swing.JTextField LocalTF;
    private WorkflowComboBox WFComboBox;
    private javax.swing.JTextField URITF;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton fromServerRadioButton;
    private javax.swing.JRadioButton fromLocalRadioButton;
    private javax.swing.JRadioButton fromURIRadioButton;
    private javax.swing.JLabel openOntologyLabel;
    private javax.swing.JButton submitButton;
    
	private JFileChooser fc;
	private File oFile;
    // End of variables declaration

}

