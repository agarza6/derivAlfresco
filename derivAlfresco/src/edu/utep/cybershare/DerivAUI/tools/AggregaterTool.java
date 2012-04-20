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


/**
 * aggregaterTool.java
 * Created on Mar 15, 2011, 11:08:20 AM
 * @author Antonio Garza
 */

public class AggregaterTool extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	/** Creates new form aggregaterTool */
    public AggregaterTool() {
        initComponents();
    }

    private void initComponents() {

        enterURILabel = new javax.swing.JLabel();
        URITF = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        enterURILabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        enterURILabel.setText("Enter Full URI");

        URITF.setText("http://");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(URITF, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(enterURILabel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enterURILabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(URITF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(submitButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }
    
	public void submitAction(java.awt.event.ActionEvent evt){
//		setCursor(new Cursor(Cursor.WAIT_CURSOR));
//		String URI = URITF.getText();
//		
//		RDFAggregater_Service Service = new RDFAggregater_Service();
//		RDFAggregater proxy = Service.getRDFAggregaterHttpPort();
//		String result = "";
//		int intents = 0;
//		while(!result.equalsIgnoreCase("SUCCESS") && intents < 3){
//			result = proxy.addDocumentAt(URI, "generic@generic.com");
//			intents++;
//		}
//
//		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//		
//		if(result.equalsIgnoreCase("SUCCESS")){
//			JOptionPane.showMessageDialog(null, "Upload Successful");
//		}else{
//			JOptionPane.showMessageDialog(null, "Aggregation Failed");
//		}
		
	}
	
	public void cancelAction(java.awt.event.ActionEvent evt){
		dispose();
	}

    // Variables declaration - do not modify
    private javax.swing.JTextField URITF;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel enterURILabel;
    private javax.swing.JButton submitButton;
    // End of variables declaration

}
