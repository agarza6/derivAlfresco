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

import javax.swing.*;

import edu.utep.cybershare.DerivA.util.AlfrescoClient;
import edu.utep.cybershare.DerivAUI.DerivAUI;
import edu.utep.cybershare.DerivAUI.components.IndividualList;
import edu.utep.cybershare.DerivAUI.components.SourcesList;
import edu.utep.cybershare.DerivAUI.components.IndividualList.Individual;

import java.awt.Cursor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings("serial")
public class AddSourceTool extends JFrame {

  //Interface Components
  public DerivAUI instance;
  private AlfrescoClient aClient;

  private javax.swing.JLabel AKALabel;
  private javax.swing.JLabel AvailPeopleLabel;
  private javax.swing.JLabel FoafMainLabel;
  private javax.swing.JLabel PersonalImageLinkLabel;
  private javax.swing.JLabel PhoneNoLabel;
  private javax.swing.JLabel emailLabel;
  private javax.swing.JLabel familyNameLabel;
  private javax.swing.JLabel firstNameLabel;
  private javax.swing.JLabel newSouceLabel;
  private javax.swing.JLabel peopleIKnowLabel;
  private javax.swing.JLabel personalHomepageLabel;
  private javax.swing.JLabel projectHomepageLabel;
  private javax.swing.JLabel schoolHomepageLabel;
  private javax.swing.JLabel shortNameLabel;
  private javax.swing.JLabel someoneElseEmailLabel;
  private javax.swing.JLabel someoneElseLabel;
  private javax.swing.JLabel someoneElseNameLabel;
  private javax.swing.JLabel someoneElseURLLabel;
  private javax.swing.JLabel workplaceHomepageLabel;

  private javax.swing.JTextField AKATF;
  private javax.swing.JTextField ImageLinkTF;
  private javax.swing.JTextField emailTF;
  private javax.swing.JTextField familyNameTF;
  private javax.swing.JTextField firstNameTF;
  private javax.swing.JTextField personalHomepageTF;
  private javax.swing.JTextField phoneTF;
  private javax.swing.JTextField projectHomepageTF;
  private javax.swing.JTextField schoolHomepageTF;
  private javax.swing.JTextField shortNameTF;
  private javax.swing.JTextField someoneElseEmailTF;
  private javax.swing.JTextField someoneElseNameTF;
  private javax.swing.JTextField someoneElseURLTF;
  private javax.swing.JTextField workplaceHomepageTF;

  private javax.swing.JCheckBox FOAFCheckBox;

  private javax.swing.JPanel FriendsIKnowPane;
  private javax.swing.JPanel PersonalInfoPane;

  private javax.swing.JButton addButton;
  private javax.swing.JButton addSomeoneElse;
  private javax.swing.JButton cancelButton;
  private javax.swing.JButton removeButton;
  private javax.swing.JButton submitButton;

  private IndividualList availPeopleList;
  private IndividualList peopleIKnowList;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JScrollPane availPeoplePane;
  private javax.swing.JScrollPane peopleIKnowPane;

  private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> availPeopleVector;
  private Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual> peopleIKnowVector;

  //Model variables
  private String shortName, fullName, URL, project, lname, fname;
  //	private String uploaderName, uploaderPass, server;
  private String email, title, depiction, phone, homepage, workpage, projectpage, schoolpage, sha1Sum;
  static String pmlp_url, OrgURL, memberOfURI,memberOfName;

  // End of variables declaration

  /** Creates new form addSourceTool */
  public AddSourceTool(DerivAUI inst, String proj, AlfrescoClient ac) {
    instance = inst;		
    aClient = ac;
    project = proj;
    initComponents();
  }

  private void initComponents() {

    jTabbedPane1 = new javax.swing.JTabbedPane();
    PersonalInfoPane = new javax.swing.JPanel();
    newSouceLabel = new javax.swing.JLabel();
    shortNameLabel = new javax.swing.JLabel();
    firstNameLabel = new javax.swing.JLabel();
    shortNameTF = new javax.swing.JTextField();
    firstNameTF = new javax.swing.JTextField();
    FOAFCheckBox = new javax.swing.JCheckBox();
    emailLabel = new javax.swing.JLabel();
    AKALabel = new javax.swing.JLabel();
    emailTF = new javax.swing.JTextField();
    familyNameLabel = new javax.swing.JLabel();
    familyNameTF = new javax.swing.JTextField();
    personalHomepageLabel = new javax.swing.JLabel();
    PersonalImageLinkLabel = new javax.swing.JLabel();
    PhoneNoLabel = new javax.swing.JLabel();
    workplaceHomepageLabel = new javax.swing.JLabel();
    projectHomepageLabel = new javax.swing.JLabel();
    schoolHomepageLabel = new javax.swing.JLabel();
    AKATF = new javax.swing.JTextField();
    phoneTF = new javax.swing.JTextField();
    ImageLinkTF = new javax.swing.JTextField();
    personalHomepageTF = new javax.swing.JTextField();
    workplaceHomepageTF = new javax.swing.JTextField();
    projectHomepageTF = new javax.swing.JTextField();
    schoolHomepageTF = new javax.swing.JTextField();
    FriendsIKnowPane = new javax.swing.JPanel();
    FoafMainLabel = new javax.swing.JLabel();
    AvailPeopleLabel = new javax.swing.JLabel();
    availPeoplePane = new javax.swing.JScrollPane();
    availPeopleList = new IndividualList();
    addButton = new javax.swing.JButton();
    removeButton = new javax.swing.JButton();
    peopleIKnowPane = new javax.swing.JScrollPane();
    peopleIKnowList = new IndividualList();
    peopleIKnowLabel = new javax.swing.JLabel();
    someoneElseLabel = new javax.swing.JLabel();
    someoneElseNameLabel = new javax.swing.JLabel();
    someoneElseEmailLabel = new javax.swing.JLabel();
    someoneElseURLLabel = new javax.swing.JLabel();
    someoneElseNameTF = new javax.swing.JTextField();
    someoneElseEmailTF = new javax.swing.JTextField();
    someoneElseURLTF = new javax.swing.JTextField();
    addSomeoneElse = new javax.swing.JButton();
    submitButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();

    availPeopleVector = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();
    peopleIKnowVector = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();

    newSouceLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    newSouceLabel.setText("Add New Source: Person");

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    newSouceLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
    newSouceLabel.setText("Add New Source: Person");

    shortNameLabel.setText("Short Name (No spaces):");
    shortNameTF.setEnabled(false);

    firstNameLabel.setText("First Name:");

    FOAFCheckBox.setText("Create FOAF (Friend of a Friend)");
    FOAFCheckBox.setSelected(false);

    emailLabel.setText("e-mail:");

    AKALabel.setText("Also Known As: ");

    familyNameLabel.setText("Last Name: ");

    personalHomepageLabel.setText("Personal Homepage: ");

    PersonalImageLinkLabel.setText("Personal Image Link: ");

    PhoneNoLabel.setText("Office Phone #:");

    workplaceHomepageLabel.setText("Workplace Homepage: ");

    projectHomepageLabel.setText("Work Project Homepage:");

    schoolHomepageLabel.setText("School Homepage:");


    submitButton.setText("Submit");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        submitAction(evt);
      }
    });

    cancelButton.setText("Close");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelAction(evt);
      }
    });

    javax.swing.GroupLayout PersonalInfoPaneLayout = new javax.swing.GroupLayout(PersonalInfoPane);
    PersonalInfoPane.setLayout(PersonalInfoPaneLayout);
    PersonalInfoPaneLayout.setHorizontalGroup(
        PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(newSouceLabel)
                .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                    .addComponent(schoolHomepageLabel)
                    .addGap(18, 18, 18)
                    .addComponent(schoolHomepageTF))
                    .addComponent(FOAFCheckBox)
                    .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                        .addComponent(projectHomepageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projectHomepageTF))
                        .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                            .addComponent(workplaceHomepageLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(workplaceHomepageTF))
                            .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                .addComponent(personalHomepageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personalHomepageTF))
                                .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                    .addComponent(PersonalImageLinkLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ImageLinkTF))
                                    .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                        .addComponent(PhoneNoLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(phoneTF))
                                        .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                            .addComponent(emailLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(emailTF))
                                            .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                                .addComponent(AKALabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(AKATF))
                                                .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                                    .addComponent(familyNameLabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(familyNameTF))
                                                    .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                                        .addComponent(firstNameLabel)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(firstNameTF))
                                                        .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
                                                            .addComponent(shortNameLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(shortNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    PersonalInfoPaneLayout.setVerticalGroup(
        PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(PersonalInfoPaneLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(newSouceLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(shortNameLabel)
                .addComponent(shortNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(4, 4, 4)
                    .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(familyNameLabel)
                        .addComponent(familyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(FOAFCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AKALabel)
                            .addComponent(AKATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PhoneNoLabel)
                                    .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(PersonalImageLinkLabel)
                                        .addComponent(ImageLinkTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(personalHomepageLabel)
                                            .addComponent(personalHomepageTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(workplaceHomepageLabel)
                                                .addComponent(workplaceHomepageTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(projectHomepageLabel)
                                                    .addComponent(projectHomepageTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(PersonalInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(schoolHomepageLabel)
                                                        .addComponent(schoolHomepageTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addContainerGap())
        );

    jTabbedPane1.addTab("Personal Information", PersonalInfoPane);

    FoafMainLabel.setText("Add people to your friends list");

    AvailPeopleLabel.setText("Available People");

    availPeoplePane.setViewportView(availPeopleList);
    availPeopleVector = new SourcesList(aClient).getSourceList();
    availPeopleList.setModel(availPeopleVector);

    peopleIKnowPane.setViewportView(peopleIKnowList);

    addButton.setFont(new java.awt.Font("Tahoma", 1, 14));
    addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/001_01.gif"))); // NOI18N
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addAction(evt);
      }
    });

    removeButton.setFont(new java.awt.Font("Tahoma", 1, 14));
    removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/001_02.gif"))); // NOI18N
    removeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        removeAction(evt);
      }
    });

    peopleIKnowPane.setViewportView(peopleIKnowList);
    peopleIKnowLabel.setText("People You Know");

    someoneElseLabel.setText("Add Someone You Know");
    someoneElseNameLabel.setText("Name:");
    someoneElseEmailLabel.setText("Email:");
    someoneElseEmailTF.setEnabled(false);
    someoneElseURLLabel.setText("FOAF URL:");

    addSomeoneElse.setText("Add");
    addSomeoneElse.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addSomeoneElseAction(evt);
      }
    });

    javax.swing.GroupLayout FriendsIKnowPaneLayout = new javax.swing.GroupLayout(FriendsIKnowPane);
    FriendsIKnowPane.setLayout(FriendsIKnowPaneLayout);
    FriendsIKnowPaneLayout.setHorizontalGroup(
        FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(FriendsIKnowPaneLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FriendsIKnowPaneLayout.createSequentialGroup()
                    .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(FoafMainLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AvailPeopleLabel, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(availPeoplePane, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(addButton)
                                .addComponent(removeButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(peopleIKnowLabel)
                                    .addComponent(peopleIKnowPane, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(someoneElseLabel)
                                    .addGroup(FriendsIKnowPaneLayout.createSequentialGroup()
                                        .addComponent(someoneElseNameLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(someoneElseNameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                                        .addGroup(FriendsIKnowPaneLayout.createSequentialGroup()
                                            .addComponent(someoneElseEmailLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(someoneElseEmailTF, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
                                            .addGroup(FriendsIKnowPaneLayout.createSequentialGroup()
                                                .addComponent(someoneElseURLLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(someoneElseURLTF, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                                                .addComponent(addSomeoneElse, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addContainerGap())
        );
    FriendsIKnowPaneLayout.setVerticalGroup(
        FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FriendsIKnowPaneLayout.createSequentialGroup()
            .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FriendsIKnowPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(FoafMainLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(AvailPeopleLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(availPeoplePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FriendsIKnowPaneLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(peopleIKnowLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FriendsIKnowPaneLayout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeButton))
                                .addComponent(peopleIKnowPane, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addComponent(someoneElseLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(someoneElseNameLabel)
                                    .addComponent(someoneElseNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(someoneElseEmailLabel)
                                        .addComponent(someoneElseEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(FriendsIKnowPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(someoneElseURLLabel)
                                            .addComponent(someoneElseURLTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addSomeoneElse)
                                            .addGap(40, 40, 40))
        );

    jTabbedPane1.addTab("Friends I Know", FriendsIKnowPane);

    submitButton.setText("Submit");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(323, Short.MAX_VALUE)
            .addComponent(submitButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(cancelButton)
            .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cancelButton)
                .addComponent(submitButton))
                .addContainerGap())
        );

    pack();
    setLocationRelativeTo(null);
  }

  public void addAction(java.awt.event.ActionEvent evt){
    Object CSA = availPeopleList.getSelectedValue();
    if(peopleIKnowVector != null){
      if(!peopleIKnowVector.contains(CSA)){
        peopleIKnowVector.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSA);
      }
    }else{
      peopleIKnowVector = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();
      peopleIKnowVector.add((edu.utep.cybershare.DerivAUI.components.IndividualList.Individual) CSA);
    }

    peopleIKnowList.setModel(peopleIKnowVector);
    peopleIKnowList.repaint();

  }

  public void addSomeoneElseAction(java.awt.event.ActionEvent evt){
    Individual someoneElse = peopleIKnowList.new Individual(someoneElseURLTF.getText(),someoneElseNameTF.getText(),someoneElseURLTF.getText());
    if(peopleIKnowVector != null){
      peopleIKnowVector.add(someoneElse);
    }else{
      peopleIKnowVector = new Vector<edu.utep.cybershare.DerivAUI.components.IndividualList.Individual>();
      peopleIKnowVector.add(someoneElse);
    }

    someoneElseURLTF.setText("");
    someoneElseNameTF.setText("");
    peopleIKnowList.setModel(peopleIKnowVector);
    peopleIKnowList.repaint();

  }

  public void removeAction(java.awt.event.ActionEvent evt){
    Object CSA = peopleIKnowList.getSelectedValue();
    if(peopleIKnowVector != null){
      if(peopleIKnowVector.contains(CSA)){
        peopleIKnowVector.remove(CSA);
      }
    }
    peopleIKnowList.setModel(peopleIKnowVector);
    peopleIKnowList.repaint();
  }

  public void submitAction(java.awt.event.ActionEvent evt){

    setCursor(new Cursor(Cursor.WAIT_CURSOR));
    try {

      fname = firstNameTF.getText();
      lname = familyNameTF.getText();

      shortName = lname + "_" + fname;
      shortName = shortName.replaceAll("[*<>\\[\\]\\+\",]", "-");
      shortName = shortName.replaceAll(" ", "_");

      fullName = fname + "_" + lname;

      String pmlP = "";

      //		aClient = new AlfrescoClient();
      //		aClient.logIn(uploaderName, uploaderPass, server);
      String node_pmlp_url = aClient.createNode(project, shortName + ".owl");

      if(!FOAFCheckBox.isSelected()){
        pmlP = getPML(aClient.getBaseUrl() + node_pmlp_url);
      }else{
        pmlP = getPML_FOAF(aClient.getBaseUrl() + node_pmlp_url);
      }

      try{
        // Create temp file with content to add to the node.
        FileWriter fstream = new FileWriter(shortName + ".owl");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(pmlP);

        out.close();
      }catch (Exception e){
        System.err.println("Error: " + e.getMessage());
      }

      File file = new File(shortName + ".owl");
      file.deleteOnExit();

      aClient.addContentToNode(node_pmlp_url, file);

      //Aggregate to a Triple Store HERE
      aClient.crawlProject(project);

      instance.refreshSourcesUI();
    } catch (Throwable e) {
      e.printStackTrace();
      String errMsg = "Error adding source: " + e.toString();
      JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.ERROR_MESSAGE);

    } finally {
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
  }

  public void cancelAction(java.awt.event.ActionEvent evt){
    dispose();
  }

  private String getPML(String pmlp_url){

    String pmlp_uri = pmlp_url.trim() + "#" + shortName;
    pmlp_uri.replaceAll("(\\r|\\n)", "");

    String pmlP = "<rdf:RDF" + '\n';
    pmlP += '\t' + "xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'" + '\n';
    pmlP += '\t' + "xmlns:pmlp='http://inference-web.org/2.0/pml-provenance.owl#'" + '\n';
    pmlP += '\t' + "xmlns:owl='http://www.w3.org/2002/07/owl#'" + '\n';
    pmlP += '\t' + "xmlns:xsd='http://www.w3.org/2001/XMLSchema#'" + '\n';
    pmlP += '\t' + "xmlns:rdfs='http://www.w3.org/2000/01/rdf-schema#'>" + '\n';
    pmlP += '\t' + "<pmlp:Person rdf:about='" + pmlp_uri + "'>" + '\n';
    pmlP += '\t' + "<pmlp:hasName rdf:datatype='http://www.w3.org/2001/XMLSchema#string'>" + fullName + "</pmlp:hasName>" + '\n';
    pmlP += '\t' + "</pmlp:Person>" + '\n';
    pmlP += "</rdf:RDF>";

    return pmlP;
  }

  private String getPML_FOAF(String pmlp_url){

    String pmlp_uri = pmlp_url.trim() + "#" + shortName;
    pmlp_uri.replaceAll("(\\r|\\n)", "");

    email = emailTF.getText();
    depiction = ImageLinkTF.getText();
    phone = phoneTF.getText();
    homepage = personalHomepageTF.getText();
    workpage = workplaceHomepageTF.getText();
    projectpage = projectHomepageTF.getText();
    schoolpage = schoolHomepageTF.getText();

    String pml_foaf = "<rdf:RDF" + '\n';
    //Imports
    pml_foaf += '\t' + "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + '\n';
    pml_foaf += '\t' + "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + '\n';
    pml_foaf += '\t' + "xmlns:pmlp=\"http://inference-web.org/2.0/pml-provenance.owl#\"" + '\n';
    pml_foaf += '\t' + "xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + '\n';
    pml_foaf += '\t' + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + '\n';
    pml_foaf += '\t' + "xmlns:foaf=\"http://xmlns.com/foaf/0.1/\"" + '\n';
    pml_foaf += '\t' + "xmlns:admin=\"http://webns.net/mvcb/\">" + '\n';
    //Foaf/PML declarations
    pml_foaf += '\t' + "<foaf:Person rdf:about=\"" + pmlp_uri + "\">" + '\n';
    pml_foaf += "\t\t" + "<rdf:type rdf:resource=\"http://inference-web.org/2.0/pml-provenance.owl#Person\"/>" + '\n';
    pml_foaf += "\t\t" + "<foaf:primaryTopic rdf:resource=\"#me\"/>" + '\n';
    pml_foaf += "\t\t" + "<admin:generatorAgent rdf:resource=\"http://trust.cs.utep.edu/derivA/\"/>" + '\n';
    pml_foaf += "\t\t" + "<admin:errorReportsTo rdf:resource=\"mailto:agarza6@miners.utep.edu\"/>" + '\n';
    //PML Info
    pml_foaf += "\t\t" + "<pmlp:hasName rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">" + fullName + "</pmlp:hasName>" + '\n';
    if(memberOfURI != null && !memberOfURI.isEmpty())
      pml_foaf += "\t\t" + "<pmlp:isMemberOf rdf:resource=\"" + memberOfURI + "\"/>" + '\n';

    //FOAF Info

    //get sha1 for email
    try {
      sha1Sum = SHA1(email);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }


    pml_foaf += "\t\t" + "<foaf:name>" + fullName + "</foaf:name>" + '\n';

    if(title != null && !title.isEmpty())
      pml_foaf += "\t\t" + "<foaf:title>" + title + "</foaf:title>" + '\n';
    pml_foaf += "\t\t" + "<foaf:mbox_sha1sum>" + sha1Sum + "</foaf:mbox_sha1sum>" + '\n';
    if(depiction != null && !depiction.isEmpty())
      pml_foaf += "\t\t" + "<foaf:depiction rdf:resource=\"" + depiction + "\"/>" + '\n';
    if(phone != null && !phone.isEmpty())
      pml_foaf += "\t\t" + "<foaf:phone rdf:resource=\"tel:" + phone + "\"/>" + '\n';
    if(homepage != null && !homepage.isEmpty())
      pml_foaf += "\t\t" + "<foaf:homepage rdf:resource=\"" + homepage + "\"/>" + '\n';
    if(workpage != null && !workpage.isEmpty())
      pml_foaf += "\t\t" + "<foaf:workplaceHomepage rdf:resource=\"" + workpage  + "\"/>" + '\n';
    if(projectpage != null && !projectpage.isEmpty())
      pml_foaf += "\t\t" + "<foaf:workInfoHomepage rdf:resource=\"" + projectpage + "\"/>" + '\n';
    if(schoolpage != null && !schoolpage.isEmpty())
      pml_foaf += "\t\t" + "<foaf:schoolHomepage rdf:resource=\"" + schoolpage + "\"/>" + '\n';
    //Knows
    pml_foaf += "\t\t" + "<foaf:knows>" + '\n';

    if(peopleIKnowVector != null){
      for(Iterator<Individual> iter = peopleIKnowVector.iterator(); iter.hasNext();){
        edu.utep.cybershare.DerivAUI.components.IndividualList.Individual ind = (Individual) iter.next();
        pml_foaf += "\t\t\t" + "<foaf:Person>" + '\n';
        pml_foaf += "\t\t\t\t" + "<foaf:name>" + ind.getName() + "</foaf:name>\n";
        pml_foaf += "\t\t\t\t" + "<foaf:seeAlso>" + ind.getURI() + "</foaf:seeAlso>\n";
        pml_foaf += "\t\t\t" + "</foaf:Person>" + '\n';
      }
    }
    pml_foaf += "\t\t" + "</foaf:knows>" + '\n';

    //End File
    pml_foaf += '\t' + "</foaf:Person>" + '\n';
    pml_foaf += "</rdf:RDF>" + '\n';

    return pml_foaf;
  }

  private static String convertToHex(byte[] data) { 
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < data.length; i++) { 
      int halfbyte = (data[i] >>> 4) & 0x0F;
      int two_halfs = 0;
      do { 
        if ((0 <= halfbyte) && (halfbyte <= 9)) 
          buf.append((char) ('0' + halfbyte));
        else 
          buf.append((char) ('a' + (halfbyte - 10)));
        halfbyte = data[i] & 0x0F;
      } while(two_halfs++ < 1);
    } 
    return buf.toString();
  } 

  public static String SHA1(String text) 
      throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
    MessageDigest md;
    md = MessageDigest.getInstance("SHA-1");
    byte[] sha1hash = new byte[40];
    md.update(text.getBytes("iso-8859-1"), 0, text.length());
    sha1hash = md.digest();
    return convertToHex(sha1hash);
  } 

}
