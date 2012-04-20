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

package edu.utep.cybershare.DerivAUI;

import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.utep.cybershare.DerivA.*;
import edu.utep.cybershare.DerivA.util.AlfrescoClient;
import edu.utep.cybershare.DerivAUI.components.*;
import edu.utep.cybershare.DerivAUI.tools.*;

public class DerivAUI extends javax.swing.JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	public static DerivAUI instance;
	private int MODE;
	private int ASSERT_MODE = 1;
	private int DERIVATE_MODE = 2;
	private int DOC_DERIVATE_MODE = 3;
	private AlfrescoClient aClient;

	// Variables declaration - do not modify
	private Cursor waiting, normal;
	private JFileChooser fc;
	//private File oFile;
	private ImageIcon check, uncheck;
	private ImageIcon SourceIcon, ConclusionIcon, IAIcon, IRIcon, AntecedentIcon;

	//Combo Boxes
	private AgentComboBox agentComboBox;
	private formatComboBox conclusionFormatComboBox;
	private typeComboBox conclusionTypeComboBox;
	private InferenceRulesComboBox inferenceRuleComboBox;

	//Panels
	private javax.swing.JPanel mainPanel;
	private javax.swing.JPanel AntecedentPanel;
	private javax.swing.JPanel ConclusionPanel;
	private javax.swing.JPanel IAgentPanel;
	private javax.swing.JPanel IRulePanel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel conclusionFromURLTab;
	private javax.swing.JPanel conclusionFromServerTab;
	private javax.swing.JPanel conclusionFromLocalTab;
	private javax.swing.JPanel SourcesPanel;
	private javax.swing.JTabbedPane conclusionFromTab;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JTabbedPane Tabs;
	//	private javax.swing.JTabbedPane jTabbedPane1;

	//Labels
	private javax.swing.JLabel AntecedentLabel;
	private javax.swing.JLabel InferenceAgentLabel;
	private javax.swing.JLabel InferenceRuleLabel;
	private javax.swing.JLabel conclusionLabel;
	private javax.swing.JLabel ProjectLabel;
	private javax.swing.JLabel ServerLabel;
	private javax.swing.JLabel selectedProjectLabel;
	private javax.swing.JLabel selectedServerLabel;
	private javax.swing.JLabel availableAntecedentLabel;
	private javax.swing.JLabel fileFormatLabel;
	private javax.swing.JLabel fileTypeLabel;
	private javax.swing.JLabel currentlySelectedAntecedentLabel;
	private javax.swing.JLabel assertArtifactModeLabel;
	private javax.swing.JLabel selectedOntologyLabel;
	private javax.swing.JLabel OntologyLabel;
	private javax.swing.JLabel DerivateArtifactModeLabel;
	private javax.swing.JLabel DocumentDerivationModeLabel;
	private javax.swing.JLabel ModeLabel;
	private javax.swing.JLabel SourcesLabel;
	private javax.swing.JLabel availableSourcesLabel;
	private javax.swing.JLabel currentlySelectedSourcesLabel;
	private javax.swing.JLabel localFileSystemLabel;
	private javax.swing.JLabel fromServerLabel;
	private javax.swing.JLabel fromURLLabel;

	//Buttons
	private javax.swing.JButton conclusionBrowserButton;
	private javax.swing.JButton AssertButton;
	private javax.swing.JButton addAntecedentButton;
	private javax.swing.JButton removeAntecedentButton;
	private javax.swing.JButton derivateButton;
	private javax.swing.JButton assertArtifactModeButton;
	private javax.swing.JButton derivateArtifactModeButton;
	private javax.swing.JButton docArtifactModeButton;
	private javax.swing.JButton removeSourceButton;
	private javax.swing.JButton addSourceButton;

	//Menu Items
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenu ToolsMenu;
	private javax.swing.JMenu HelpMenu;
	private javax.swing.JMenuItem loadSAWItem;
	private javax.swing.JMenuItem loadWDOItem;
	private javax.swing.JMenuItem logInItem;
	private javax.swing.JMenuItem aboutItem;
	private javax.swing.JMenuItem CloseItem;
	private javax.swing.JPopupMenu.Separator jSeparator3;
	private javax.swing.JMenuItem tripleStoreAggregater;
	private javax.swing.JMenuItem addSourceTool;
	private javax.swing.JMenuItem addAgentTool;

	//Lists
	private IndividualList antecedentList;
	private IndividualList currentlySelectedAntecedentList;
	private IndividualList conclusionList;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> General_PMLJ_Nodes;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> Project_PMLJ_Nodes;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> CurrentlySelectedAntecedentsVector;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> CurrentlySelectedSourcesVector;
	private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> SourcesPML_Nodes;
	private IndividualList SourcesList;
	private IndividualList currentlySelectedSourcesList;

	//Extras
	private javax.swing.JProgressBar ProgressBar;
	private javax.swing.JCheckBox antecedentsByProjectCheckBox;
	private javax.swing.JCheckBox IncludeUserCheckBox;
	private javax.swing.JTextField conclusionBrowserTF;
	private javax.swing.JTextField fromURLTF;

	//Strings
	private String selectedServerSTR;
	private String selectedProjectSTR;
	private String selectedOntologySTR = null;
	private String username, password;

	//tools
	private AddAgentTool AAT;
	private AddSourceTool AST;
	private about about;

	private Task task;

	// End of variables declaration


	/** Creates new form DerivAGUI */
	public DerivAUI() {
		serverLogin();
		
//		initTripleStoreComponents();
	}

	public void setSelectedOntology(String sel){
		selectedOntologySTR = sel;
	}

	private void initTripleStoreComponents(){

		waiting = new Cursor(Cursor.WAIT_CURSOR);
		setCursor(waiting);

		ProgressBar.setString("Loading Components");
		ProgressBar.setStringPainted(true);

		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}

	private void initComponents() {


		conclusionList = new IndividualList();
		antecedentList = new IndividualList();
		SourcesList = new IndividualList();
		currentlySelectedSourcesList = new IndividualList();
		currentlySelectedAntecedentList = new IndividualList();
		conclusionFormatComboBox = new formatComboBox(aClient);
		conclusionTypeComboBox = new typeComboBox(aClient);
		agentComboBox = new AgentComboBox(aClient);
		inferenceRuleComboBox = new InferenceRulesComboBox(aClient);

		mainPanel = new javax.swing.JPanel();
		ServerLabel = new javax.swing.JLabel();
		selectedServerLabel = new javax.swing.JLabel();
		ProjectLabel = new javax.swing.JLabel();
		selectedProjectLabel = new javax.swing.JLabel();
		OntologyLabel = new javax.swing.JLabel();
		selectedOntologyLabel = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		ModeLabel = new javax.swing.JLabel();
		assertArtifactModeButton = new javax.swing.JButton();
		derivateArtifactModeButton = new javax.swing.JButton();
		assertArtifactModeLabel = new javax.swing.JLabel();
		DerivateArtifactModeLabel = new javax.swing.JLabel();
		DocumentDerivationModeLabel = new javax.swing.JLabel();
		docArtifactModeButton = new javax.swing.JButton();
		Tabs = new javax.swing.JTabbedPane();
		SourcesPanel = new javax.swing.JPanel();
		SourcesLabel = new javax.swing.JLabel();
		availableSourcesLabel = new javax.swing.JLabel();
		currentlySelectedSourcesLabel = new javax.swing.JLabel();
		jScrollPane4 = new javax.swing.JScrollPane();
		jScrollPane5 = new javax.swing.JScrollPane();
		addSourceButton = new javax.swing.JButton();
		removeSourceButton = new javax.swing.JButton();
		IncludeUserCheckBox = new javax.swing.JCheckBox();
		ConclusionPanel = new javax.swing.JPanel();
		conclusionLabel = new javax.swing.JLabel();
		fileFormatLabel = new javax.swing.JLabel();
		fileTypeLabel = new javax.swing.JLabel();
		AssertButton = new javax.swing.JButton();
		conclusionFromTab = new javax.swing.JTabbedPane();
		conclusionFromLocalTab = new javax.swing.JPanel();
		localFileSystemLabel = new javax.swing.JLabel();
		conclusionBrowserButton = new javax.swing.JButton();
		conclusionBrowserTF = new javax.swing.JTextField();
		conclusionFromServerTab = new javax.swing.JPanel();
		fromServerLabel = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		conclusionFromURLTab = new javax.swing.JPanel();
		fromURLLabel = new javax.swing.JLabel();
		fromURLTF = new javax.swing.JTextField();
		IAgentPanel = new javax.swing.JPanel();
		InferenceAgentLabel = new javax.swing.JLabel();
		IRulePanel = new javax.swing.JPanel();
		InferenceRuleLabel = new javax.swing.JLabel();
		AntecedentPanel = new javax.swing.JPanel();
		AntecedentLabel = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		antecedentsByProjectCheckBox = new javax.swing.JCheckBox();
		jScrollPane3 = new javax.swing.JScrollPane();
		addAntecedentButton = new javax.swing.JButton();
		removeAntecedentButton = new javax.swing.JButton();
		availableAntecedentLabel = new javax.swing.JLabel();
		currentlySelectedAntecedentLabel = new javax.swing.JLabel();
		derivateButton = new javax.swing.JButton();
		ProgressBar = new javax.swing.JProgressBar();
		jMenuBar1 = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		logInItem = new javax.swing.JMenuItem();
		loadWDOItem = new javax.swing.JMenuItem();
		loadSAWItem = new javax.swing.JMenuItem();
		jSeparator3 = new javax.swing.JPopupMenu.Separator();
		CloseItem = new javax.swing.JMenuItem();
		ToolsMenu = new javax.swing.JMenu();
		tripleStoreAggregater = new javax.swing.JMenuItem();
		HelpMenu = new javax.swing.JMenu();
		aboutItem = new javax.swing.JMenuItem();
		addSourceTool = new javax.swing.JMenuItem();
		addAgentTool = new javax.swing.JMenuItem();

		check = new javax.swing.ImageIcon(getClass().getResource("images/tick.png"));
		uncheck = new javax.swing.ImageIcon(getClass().getResource("images/checkbox_unchecked.png"));

		SourceIcon = uncheck;
		ConclusionIcon = uncheck;
		IAIcon = uncheck;
		IRIcon = uncheck; 
		AntecedentIcon = uncheck;

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ServerLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
		ServerLabel.setText("Server:");

		selectedServerLabel.setText("default");

		ProjectLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
		ProjectLabel.setText("Project:");

		selectedProjectLabel.setText("default");

		OntologyLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
		OntologyLabel.setText("Ontology:");

		selectedOntologyLabel.setText("default");

		ModeLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		ModeLabel.setText("Select Mode");

		assertArtifactModeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/assert.png"))); // NOI18N
		assertArtifactModeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assertModeAction(evt);
			}
		});

		derivateArtifactModeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/makeDerivation.png"))); // NOI18N
		derivateArtifactModeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				derivateModeAction(evt);
			}
		});

		docArtifactModeButton.setEnabled(false);
		docArtifactModeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/docDerivatio.png"))); // NOI18N
		docArtifactModeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				docDerivateModeAction(evt);
			}
		});

		assertArtifactModeLabel.setText("Assert Artifact");

		DerivateArtifactModeLabel.setText("Derivate Artifact");

		DocumentDerivationModeLabel.setText("Document Derivation");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(ModeLabel)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addContainerGap()
										.addComponent(assertArtifactModeButton)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(derivateArtifactModeButton)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(docArtifactModeButton)))
										.addContainerGap())
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGap(56, 56, 56)
												.addComponent(assertArtifactModeLabel)
												.addGap(97, 97, 97)
												.addComponent(DerivateArtifactModeLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
												.addComponent(DocumentDerivationModeLabel)
												.addGap(37, 37, 37))
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(ModeLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(assertArtifactModeButton)
								.addComponent(derivateArtifactModeButton)
								.addComponent(docArtifactModeButton))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(assertArtifactModeLabel)
										.addComponent(DerivateArtifactModeLabel)
										.addComponent(DocumentDerivationModeLabel))
										.addContainerGap())
		);

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(
				mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup()
						.addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(mainPanelLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(ServerLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(selectedServerLabel)
										.addGap(18, 18, 18)
										.addComponent(ProjectLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(selectedProjectLabel)
										.addGap(18, 18, 18)
										.addComponent(OntologyLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(selectedOntologyLabel))
										.addGroup(mainPanelLayout.createSequentialGroup()
												.addGap(36, 36, 36)
												.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addContainerGap(45, Short.MAX_VALUE))
		);
		mainPanelLayout.setVerticalGroup(
				mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup()
						.addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(ServerLabel)
								.addComponent(selectedServerLabel)
								.addComponent(ProjectLabel)
								.addComponent(selectedProjectLabel)
								.addComponent(selectedOntologyLabel)
								.addComponent(OntologyLabel))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		SourcesLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		SourcesLabel.setText("Select All Sources");

		availableSourcesLabel.setText("Available Sources");

		currentlySelectedSourcesLabel.setText("Currently Selected Sources");

		jScrollPane4.setViewportView(SourcesList);

		jScrollPane5.setViewportView(currentlySelectedSourcesList);

		addSourceButton.setFont(new java.awt.Font("Tahoma", 1, 14));
		addSourceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/001_01.gif"))); // NOI18N
		addSourceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSourceAction(evt);
			}
		});

		removeSourceButton.setFont(new java.awt.Font("Tahoma", 1, 14));
		removeSourceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/001_02.gif"))); // NOI18N
		removeSourceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeSourceAction(evt);
			}
		});

		IncludeUserCheckBox.setSelected(false);
		IncludeUserCheckBox.setText("Include Session User");

		javax.swing.GroupLayout SourcesPanelLayout = new javax.swing.GroupLayout(SourcesPanel);
		SourcesPanel.setLayout(SourcesPanelLayout);
		SourcesPanelLayout.setHorizontalGroup(
				SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(SourcesPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(SourcesLabel)
								.addComponent(availableSourcesLabel)
								.addGroup(SourcesPanelLayout.createSequentialGroup()
										.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(removeSourceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(addSourceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(6, 6, 6)
												.addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(IncludeUserCheckBox))
												.addContainerGap(17, Short.MAX_VALUE))
												.addGroup(SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(SourcesPanelLayout.createSequentialGroup()
																.addGap(316, 316, 316)
																.addComponent(currentlySelectedSourcesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap(101, Short.MAX_VALUE)))
		);
		SourcesPanelLayout.setVerticalGroup(
				SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(SourcesPanelLayout.createSequentialGroup()
						.addGap(88, 88, 88)
						.addComponent(addSourceButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(removeSourceButton)
						.addContainerGap(108, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SourcesPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(SourcesLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(availableSourcesLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
										.addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(IncludeUserCheckBox)
										.addGap(36, 36, 36))
										.addGroup(SourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(SourcesPanelLayout.createSequentialGroup()
														.addGap(34, 34, 34)
														.addComponent(currentlySelectedSourcesLabel)
														.addContainerGap(225, Short.MAX_VALUE)))
		);

		Tabs.addTab("Sources", SourceIcon, SourcesPanel);

		conclusionLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		conclusionLabel.setText("Select Conclusion Artifact");

		fileFormatLabel.setText("File Type:");

		fileTypeLabel.setText("File Format:");

		AssertButton.setText("Assert Conclusion");
		AssertButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assertAction(evt);
			}
		});

		localFileSystemLabel.setText("Select Conclusion From Local File System");

		conclusionBrowserButton.setText("Browse");
		conclusionBrowserButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				browseAction(evt);
			}
		});

		conclusionBrowserTF.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				conclusionBrowserTFActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout conclusionFromLocalTabLayout = new javax.swing.GroupLayout(conclusionFromLocalTab);
		conclusionFromLocalTab.setLayout(conclusionFromLocalTabLayout);
		conclusionFromLocalTabLayout.setHorizontalGroup(
				conclusionFromLocalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(conclusionFromLocalTabLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(conclusionFromLocalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(localFileSystemLabel)
								.addGroup(conclusionFromLocalTabLayout.createSequentialGroup()
										.addComponent(conclusionBrowserButton)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(conclusionBrowserTF, javax.swing.GroupLayout.PREFERRED_SIZE, 450, Short.MAX_VALUE)))
										.addContainerGap(285, Short.MAX_VALUE))
		);
		conclusionFromLocalTabLayout.setVerticalGroup(
				conclusionFromLocalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(conclusionFromLocalTabLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(localFileSystemLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(conclusionFromLocalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(conclusionBrowserButton)
								.addComponent(conclusionBrowserTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(67, Short.MAX_VALUE))
		);

		conclusionFromTab.addTab("From Local File System", conclusionFromLocalTab);

		fromServerLabel.setText("Select Conclusion From Server");
		conclusionList.setEnabled(false);
		jScrollPane1.setViewportView(conclusionList);

		javax.swing.GroupLayout conclusionFromServerTabLayout = new javax.swing.GroupLayout(conclusionFromServerTab);
		conclusionFromServerTab.setLayout(conclusionFromServerTabLayout);
		conclusionFromServerTabLayout.setHorizontalGroup(
				conclusionFromServerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(conclusionFromServerTabLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(conclusionFromServerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(fromServerLabel))
								.addContainerGap(256, Short.MAX_VALUE))
		);
		conclusionFromServerTabLayout.setVerticalGroup(
				conclusionFromServerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(conclusionFromServerTabLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(fromServerLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
						.addContainerGap())
		);

		conclusionFromTab.addTab("From CI-Server", conclusionFromServerTab);

		fromURLLabel.setText("Select Conclusion From URL");

		fromURLTF.setText("http://");

		javax.swing.GroupLayout conclusionFromURLTabLayout = new javax.swing.GroupLayout(conclusionFromURLTab);
		conclusionFromURLTab.setLayout(conclusionFromURLTabLayout);
		conclusionFromURLTabLayout.setHorizontalGroup(
				conclusionFromURLTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(conclusionFromURLTabLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(conclusionFromURLTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(fromURLTF, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
								.addComponent(fromURLLabel))
								.addContainerGap())
		);
		conclusionFromURLTabLayout.setVerticalGroup(
				conclusionFromURLTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(conclusionFromURLTabLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(fromURLLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(fromURLTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(70, Short.MAX_VALUE))
		);

		conclusionFromTab.addTab("From URL", conclusionFromURLTab);

		javax.swing.GroupLayout ConclusionPanelLayout = new javax.swing.GroupLayout(ConclusionPanel);
		ConclusionPanel.setLayout(ConclusionPanelLayout);
		ConclusionPanelLayout.setHorizontalGroup(
				ConclusionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(ConclusionPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(ConclusionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(conclusionLabel)
								.addGroup(ConclusionPanelLayout.createSequentialGroup()
										.addComponent(fileFormatLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(conclusionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(ConclusionPanelLayout.createSequentialGroup()
												.addComponent(fileTypeLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(conclusionFormatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(ConclusionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(AssertButton)
														.addComponent(conclusionFromTab, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addContainerGap(22, Short.MAX_VALUE))
		);
		ConclusionPanelLayout.setVerticalGroup(
				ConclusionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(ConclusionPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(conclusionLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(ConclusionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(fileFormatLabel)
								.addComponent(conclusionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(ConclusionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(fileTypeLabel)
										.addComponent(conclusionFormatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(conclusionFromTab, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(AssertButton)
										.addContainerGap())
		);

		Tabs.addTab("Conclusion", ConclusionIcon, ConclusionPanel);

		InferenceAgentLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		InferenceAgentLabel.setText("Select an Inference Agent");

		javax.swing.GroupLayout IAgentPanelLayout = new javax.swing.GroupLayout(IAgentPanel);
		IAgentPanel.setLayout(IAgentPanelLayout);
		IAgentPanelLayout.setHorizontalGroup(
				IAgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(IAgentPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(IAgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(agentComboBox, 0, 557, Short.MAX_VALUE)
								.addComponent(InferenceAgentLabel))
								.addContainerGap())
		);
		IAgentPanelLayout.setVerticalGroup(
				IAgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(IAgentPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(InferenceAgentLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(agentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(221, Short.MAX_VALUE))
		);

		Tabs.addTab("Inference Agent", IAIcon, IAgentPanel);
		InferenceRuleLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		InferenceRuleLabel.setText("Select Inference Rule for Inference Agent");

		javax.swing.GroupLayout IRulePanelLayout = new javax.swing.GroupLayout(IRulePanel);
		IRulePanel.setLayout(IRulePanelLayout);
		IRulePanelLayout.setHorizontalGroup(
				IRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(IRulePanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(IRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(inferenceRuleComboBox, 0, 557, Short.MAX_VALUE)
								.addComponent(InferenceRuleLabel))
								.addContainerGap())
		);
		IRulePanelLayout.setVerticalGroup(
				IRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(IRulePanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(InferenceRuleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(inferenceRuleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(221, Short.MAX_VALUE))
		);

		Tabs.addTab("Inference Rule", IRIcon, IRulePanel);

		AntecedentLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		AntecedentLabel.setText("Select All Antecedents that Derive the Selected Conclusion");
		jScrollPane2.setViewportView(antecedentList);

		antecedentsByProjectCheckBox.setSelected(true);
		antecedentsByProjectCheckBox.setText("Artifacts by Project ONLY");
		antecedentsByProjectCheckBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateAntecedentList(evt);
			}
		});
		jScrollPane3.setViewportView(currentlySelectedAntecedentList);

		addAntecedentButton.setFont(new java.awt.Font("Tahoma", 1, 14));
		addAntecedentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/001_01.gif"))); // NOI18N
		addAntecedentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAntecedentAction(evt);
			}
		});

		removeAntecedentButton.setFont(new java.awt.Font("Tahoma", 1, 14));
		removeAntecedentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/001_02.gif"))); // NOI18N
		removeAntecedentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAntecedentAction(evt);
			}
		});

		availableAntecedentLabel.setText("Available Artifacts");

		currentlySelectedAntecedentLabel.setText("Currently Selected Artifacts");
		jScrollPane3.setViewportView(currentlySelectedAntecedentList);

		derivateButton.setText("Derivate");
		derivateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				derivateAction(evt);
			}
		});

		javax.swing.GroupLayout AntecedentPanelLayout = new javax.swing.GroupLayout(AntecedentPanel);
		AntecedentPanel.setLayout(AntecedentPanelLayout);
		AntecedentPanelLayout.setHorizontalGroup(
				AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AntecedentPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(AntecedentLabel)
								.addGroup(AntecedentPanelLayout.createSequentialGroup()
										.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(AntecedentPanelLayout.createSequentialGroup()
														.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(availableAntecedentLabel)
																.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addComponent(removeAntecedentButton, 0, 0, Short.MAX_VALUE)
																		.addComponent(addAntecedentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addComponent(antecedentsByProjectCheckBox))
																		.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(AntecedentPanelLayout.createSequentialGroup()
																						.addGap(10, 10, 10)
																						.addComponent(currentlySelectedAntecedentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(AntecedentPanelLayout.createSequentialGroup()
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(derivateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))))
																										.addGap(13, 13, 13))
		);
		AntecedentPanelLayout.setVerticalGroup(
				AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AntecedentPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(AntecedentLabel)
						.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(AntecedentPanelLayout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(availableAntecedentLabel)
												.addComponent(currentlySelectedAntecedentLabel))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(AntecedentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(jScrollPane3)
														.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(antecedentsByProjectCheckBox))
														.addGroup(AntecedentPanelLayout.createSequentialGroup()
																.addGap(64, 64, 64)
																.addComponent(addAntecedentButton)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(removeAntecedentButton)))
																.addGap(1, 1, 1)
																.addComponent(derivateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(39, 39, 39))
		);

		Tabs.addTab("Derived From", AntecedentIcon, AntecedentPanel);

		fileMenu.setText("File");

		logInItem.setText("Log In");
		//		fileMenu.add(logInItem);

		loadWDOItem.setText("Load WDO");
		loadWDOItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openOntologyToolAction(evt);
			}
		});
		fileMenu.add(loadWDOItem);

		loadSAWItem.setText("Load SAW");
		fileMenu.add(loadSAWItem).setEnabled(false);
		fileMenu.add(jSeparator3);

		CloseItem.setText("Close");
		CloseItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeAction(evt);
			}
		});
		fileMenu.add(CloseItem);

		jMenuBar1.add(fileMenu);

		ToolsMenu.setText("Tools");

		tripleStoreAggregater.setText("Triple Store Aggregater");
		tripleStoreAggregater.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aggregateToolAction(evt);
			}
		});
		ToolsMenu.add(tripleStoreAggregater).setEnabled(false);

		addSourceTool.setText("Add New Source");
		addSourceTool.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSourceToolAction(evt);
			}
		});
		ToolsMenu.add(addSourceTool);

		addAgentTool.setText("Add New Inference Agent");
		addAgentTool.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAgentToolAction(evt);
			}
		});
		ToolsMenu.add(addAgentTool);

		jMenuBar1.add(ToolsMenu);

		HelpMenu.setText("Help");

		aboutItem.setText("About");
		HelpMenu.add(aboutItem);
		aboutItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutAction(evt);
			}
		});

		jMenuBar1.add(HelpMenu);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(mainPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(ProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(Tabs, javax.swing.GroupLayout.Alignment.LEADING))
								.addContainerGap(10, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(1, 1, 1)
						.addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
		);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		MODE = ASSERT_MODE;

		Tabs.setEnabledAt(0, true);
		Tabs.setEnabledAt(1, true);
		Tabs.setEnabledAt(2, false);
		Tabs.setEnabledAt(3, false);
		Tabs.setEnabledAt(4, false);

		conclusionList.setEnabled(false);
		conclusionBrowserButton.setEnabled(true);
		AssertButton.setEnabled(true);
	}

	public void aboutAction(java.awt.event.ActionEvent evt){
		if(about == null){
			about = new about();
		}
		about.setVisible(true);
	}

	public void assertModeAction(java.awt.event.ActionEvent evt){
		MODE = ASSERT_MODE;

		Tabs.setEnabledAt(0, true);
		Tabs.setEnabledAt(1, true);
		Tabs.setEnabledAt(2, false);
		Tabs.setEnabledAt(3, false);
		Tabs.setEnabledAt(4, false);

		conclusionList.setEnabled(false);
		conclusionBrowserButton.setEnabled(true);
		AssertButton.setEnabled(true);

		if(Tabs.getSelectedIndex() > 1)
			Tabs.setSelectedIndex(0);
	}

	public void derivateModeAction(java.awt.event.ActionEvent evt){
		MODE = DERIVATE_MODE;

		Tabs.setEnabledAt(0, false);
		Tabs.setEnabledAt(1, true);
		Tabs.setEnabledAt(2, true);
		Tabs.setEnabledAt(3, true);
		Tabs.setEnabledAt(4, true);

		conclusionList.setEnabled(false);
		conclusionBrowserButton.setEnabled(true);
		AssertButton.setEnabled(false);

		if(Tabs.getSelectedIndex() == 0)
			Tabs.setSelectedIndex(1);
	}

	public void docDerivateModeAction(java.awt.event.ActionEvent evt){
		MODE = DOC_DERIVATE_MODE;

		Tabs.setEnabledAt(0, false);
		Tabs.setEnabledAt(1, true);
		Tabs.setEnabledAt(2, true);
		Tabs.setEnabledAt(3, true);
		Tabs.setEnabledAt(4, true);

		conclusionList.setEnabled(true);
		conclusionBrowserButton.setEnabled(false);
		AssertButton.setEnabled(false);

		if(Tabs.getSelectedIndex() == 0)
			Tabs.setSelectedIndex(1);

	}

	public void updateAntecedentList(java.awt.event.ActionEvent evt){

		if(!antecedentsByProjectCheckBox.isSelected()){
			if(CurrentlySelectedAntecedentsVector != null)
				CurrentlySelectedAntecedentsVector.clear();
			if(General_PMLJ_Nodes == null)
				General_PMLJ_Nodes = new PMLJList("ALL_PROJECTS_$!!$!", aClient).getPMLList();
			antecedentList.setModel(General_PMLJ_Nodes);		
			antecedentList.repaint();
		}else{
			refreshAntecedentUI();
		}
	}

	public void refreshAntecedentUI(){
		if(CurrentlySelectedAntecedentsVector != null)
			CurrentlySelectedAntecedentsVector.clear();
		Project_PMLJ_Nodes = new PMLJList(selectedProjectSTR, aClient).getPMLList();

		if(MODE == DOC_DERIVATE_MODE)
			conclusionList.setModel(Project_PMLJ_Nodes);

		antecedentList.setModel(Project_PMLJ_Nodes);		
		antecedentList.repaint();
	}

	public void refreshSourcesUI(){
		if(CurrentlySelectedSourcesVector != null)
			CurrentlySelectedSourcesVector.clear();
		SourcesPML_Nodes = new SourcesList(aClient).getSourceList();
		SourcesList.setModel(SourcesPML_Nodes);
		SourcesList.repaint();
	}

	public void refreshInferenceAgentUI(){
		agentComboBox.queryAgents();
		agentComboBox.repaint();
	}
	
	public void filterByWDO(){

		System.out.println("filtering by: " + selectedOntologySTR);

		conclusionTypeComboBox.setOntology(selectedOntologySTR);
		conclusionTypeComboBox.queryAgents();

		inferenceRuleComboBox.setOntology(selectedOntologySTR);
		inferenceRuleComboBox.queryAgents();

		conclusionTypeComboBox.repaint();
		inferenceRuleComboBox.repaint();
	}

	public void assertAction(java.awt.event.ActionEvent evt){
	  waiting = new Cursor(Cursor.WAIT_CURSOR);
	  setCursor(waiting);
	  try {
	    AssertionMaker AM = new AssertionMaker(aClient);
	    AM.setUseSessionUser(IncludeUserCheckBox.isSelected());
	    AM.setSources(CurrentlySelectedSourcesVector);

	    if(conclusionBrowserTF.getText().length() > 0){
	      String filePath = conclusionBrowserTF.getText();
	      AM.setDataFilePath(filePath, false);
	      AM.setFile(new File(filePath));
	      
	    } else if(fromURLTF.getText().length() > 0){
	      AM.setDataFilePath(fromURLTF.getText(), true);
	    }

	    IndividualComboBox.Individual docTypeInd = (IndividualComboBox.Individual) conclusionTypeComboBox.getSelectedItem();
	    AM.setDocumentTypeURI(docTypeInd.getURI());

	    IndividualComboBox.Individual docFormatInd = (IndividualComboBox.Individual) conclusionFormatComboBox.getSelectedItem();
	    AM.setDocumentFormatURI(docFormatInd.getURI());

	    AM.setCIProjectName(selectedProjectSTR);
	    AM.setCIServerPath(selectedServerSTR);

	    AM.setUsername(username);
	    AM.setUserPassword(password);

	    AM.generateAssertation();
	    refreshAntecedentUI();

	  } catch (Throwable e) {
	    e.printStackTrace();
	    String errMsg = "Assertion Error: " + e.toString();
	    JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.ERROR_MESSAGE);

	  } finally {
	    setCursor(normal);
	  }
	}

	public void closeAction(java.awt.event.ActionEvent evt){
		System.exit(0);
	}

	public void addSourceToolAction(java.awt.event.ActionEvent evt){
		if(AST == null){
			AST = new AddSourceTool(instance, selectedProjectSTR, aClient);
			AST.addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosed(WindowEvent e) {
          AST = null;
        }
			  
      });
		}
		AST.setVisible(true);
	}

	public void addAgentToolAction(java.awt.event.ActionEvent evt){
		if(AAT == null){
			if(aClient == null) {
				AAT = new AddAgentTool(instance, selectedServerSTR, selectedProjectSTR, username, password);
			} else {
				AAT = new AddAgentTool(instance, selectedProjectSTR, aClient);
			}
	     AAT.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosed(WindowEvent e) {
	          AAT = null;
	        }
	        
	      });
		}
		AAT.setVisible(true);
	}

	public void aggregateToolAction(java.awt.event.ActionEvent evt){
		new AggregaterTool().setVisible(true);
	}

	public void openOntologyToolAction(java.awt.event.ActionEvent evt){
		new OpenOntologyTool(instance, aClient).setVisible(true);
	}

	public void addSourceAction(java.awt.event.ActionEvent evt){
		Object CSS = SourcesList.getSelectedValue();
		if(CurrentlySelectedSourcesVector != null){
			if(!CurrentlySelectedSourcesVector.contains(CSS)){
				CurrentlySelectedSourcesVector.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSS);
			}
		}else{
			CurrentlySelectedSourcesVector = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();
			CurrentlySelectedSourcesVector.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSS);
		}

		SourceIcon = check;

		currentlySelectedSourcesList.setModel(CurrentlySelectedSourcesVector);
		currentlySelectedSourcesList.repaint();

	}

	public void removeSourceAction(java.awt.event.ActionEvent evt){
		Object CSS = currentlySelectedSourcesList.getSelectedValue();
		if(CurrentlySelectedSourcesVector != null){
			if(CurrentlySelectedSourcesVector.contains(CSS)){
				CurrentlySelectedSourcesVector.remove(CSS);
			}
		}
		currentlySelectedSourcesList.setModel(CurrentlySelectedSourcesVector);
		currentlySelectedSourcesList.repaint();
	}

	public void addAntecedentAction(java.awt.event.ActionEvent evt){
		Object CSA = antecedentList.getSelectedValue();
		if(CurrentlySelectedAntecedentsVector != null){
			if(!CurrentlySelectedAntecedentsVector.contains(CSA)){
				CurrentlySelectedAntecedentsVector.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSA);
			}
		}else{
			CurrentlySelectedAntecedentsVector = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();
			CurrentlySelectedAntecedentsVector.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSA);
		}

		currentlySelectedAntecedentList.setModel(CurrentlySelectedAntecedentsVector);
		currentlySelectedAntecedentList.repaint();

	}

	public void removeAntecedentAction(java.awt.event.ActionEvent evt){
		Object CSA = currentlySelectedAntecedentList.getSelectedValue();
		if(CurrentlySelectedAntecedentsVector != null){
			if(CurrentlySelectedAntecedentsVector.contains(CSA)){
				CurrentlySelectedAntecedentsVector.remove(CSA);
			}
		}
		currentlySelectedAntecedentList.setModel(CurrentlySelectedAntecedentsVector);
		currentlySelectedAntecedentList.repaint();
	}

	public void derivateAction(java.awt.event.ActionEvent evt){

	  waiting = new Cursor(Cursor.WAIT_CURSOR);
	  setCursor(waiting);
	  try {
	    DerivationMaker DM = new DerivationMaker(aClient);

	    //set conclusion information
	    DM.setFile(new File(conclusionBrowserTF.getText()));

	    IndividualComboBox.Individual docFormatInd = (IndividualComboBox.Individual) conclusionFormatComboBox.getSelectedItem();
	    DM.setConclusionFormatURI(docFormatInd.getURI());

	    IndividualComboBox.Individual docTypeInd = (IndividualComboBox.Individual) conclusionTypeComboBox.getSelectedItem();
	    DM.setConclusionTypeURI(docTypeInd.getURI());

	    //set derivation information
	    IndividualComboBox.Individual agentInd = (IndividualComboBox.Individual) agentComboBox.getSelectedItem();
	    DM.setAgentURI(agentInd.getURI());

	    IndividualComboBox.Individual IRInd = (IndividualComboBox.Individual) inferenceRuleComboBox.getSelectedItem();
	    DM.setInferenceRuleURI(IRInd.getURI());

	    //set antecedents
	    DM.setAntecedentURIs(CurrentlySelectedAntecedentsVector);

	    DM.setUsername(username);
	    DM.setUserPassword(password);
	    DM.setCIProjectName(selectedProjectSTR);
	    DM.setCIServerPath(selectedServerSTR);

	    DM.generateDerivation();

	    refreshAntecedentUI();
	    
	  } catch (Throwable e) {
	    e.printStackTrace();
	    String errMsg = "Derivation Error: " + e.toString();
	    JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.ERROR_MESSAGE);

	  } finally {
	    setCursor(normal);
	  }

	}

	public void browseAction(java.awt.event.ActionEvent evt){
		//Create a file chooser
		fc = new JFileChooser(conclusionBrowserTF.getText());
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int returnVal = fc.showOpenDialog(DerivAUI.this);

		String file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		  file = fc.getSelectedFile().getAbsolutePath();
		  conclusionBrowserTF.setText(file);
		}

	}

	// not sure what this is supposed to do?
	private void conclusionBrowserTFActionPerformed(java.awt.event.ActionEvent evt) {
		if(conclusionBrowserTF.getText().length() > 0)
			conclusionFromURLTab.setEnabled(false);
		else
			conclusionFromURLTab.setEnabled(false);
	}

	public boolean serverLogin() {

		int intents = 0;
		
		do{
			if(aClient == null)
				aClient = new AlfrescoClient(this);
			intents++;
		} while(!aClient.isLoggedIn() && intents < 3);
		
		if(intents < 3){
			JOptionPane.showMessageDialog(null, "Too many failed login attempts, derivA will close now.");
			System.exit(0);
		}
		
		return true;
		
	}
	
	public void setCredentials(String UN, String PS, String server, String project){
		initComponents();
		
		username = UN;
		password = PS;
		selectedServerSTR = server;
		selectedServerLabel.setText(server);
		selectedProjectSTR = project;
		selectedProjectLabel.setText(project);
		
		initTripleStoreComponents();
	}
	
	public static void main(String args[]) {

		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}


		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				instance = new DerivAUI();
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			ProgressBar.setValue(progress);
		} 
	}

	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() {

			ProgressBar.setString("Loading Sources");
			SourcesPML_Nodes = new SourcesList(aClient).getSourceList();
			SourcesList.setModel(SourcesPML_Nodes);
			setProgress(15);

			ProgressBar.setString("Loading Data Formats");
			conclusionFormatComboBox.queryFormats();
			setProgress(25);
			
			ProgressBar.setString("Loading Data Types");
			conclusionTypeComboBox.queryAgents();
			setProgress(35);
			
			ProgressBar.setString("Loading Inference Agents");
			agentComboBox.queryAgents();
			setProgress(50);

			ProgressBar.setString("Loading Inference Rules");
			inferenceRuleComboBox.queryAgents();
			setProgress(65);

			ProgressBar.setString("Loading Antecedents");
			setProgress(79);

			Project_PMLJ_Nodes = new PMLJList(selectedProjectSTR, aClient).getPMLList();
			antecedentList.setModel(Project_PMLJ_Nodes);
			ProgressBar.setString("Loading Conclusions");
			setProgress(89);

			conclusionList.setModel(Project_PMLJ_Nodes);
			conclusionList.setEnabled(false);
			setProgress(100);
			return null;
		}

		/* (non-Javadoc)
		 * @see javax.swing.SwingWorker#done()
		 */
		@Override
		public void done() {
			setProgress(0);
			setCursor(normal); //turn off the wait cursor
			ProgressBar.setStringPainted(false);
		}
	}

}
