// This file is part of KeY - Integrated Deductive Software Design 
//
// Copyright (C) 2001-2011 Universitaet Karlsruhe (TH), Germany 
//                         Universitaet Koblenz-Landau, Germany
//                         Chalmers University of Technology, Sweden
// Copyright (C) 2011-2013 Karlsruhe Institute of Technology, Germany 
//                         Technical University Darmstadt, Germany
//                         Chalmers University of Technology, Sweden
//
// The KeY system is protected by the GNU General 
// Public License. See LICENSE.TXT for details.
// 

/* This file has been generated by Stubmaker (de.uka.ilkd.stubmaker)
 * Date: Wed May 14 11:55:40 CEST 2008
 */
package javax.swing;

public class JLabel extends javax.swing.JComponent implements javax.swing.SwingConstants, javax.accessibility.Accessible {
    protected java.awt.Component labelFor;

    public JLabel(java.lang.String arg0, javax.swing.Icon arg1, int arg2);

    public JLabel(java.lang.String arg0, int arg1);

    public JLabel(java.lang.String arg0);

    public JLabel(javax.swing.Icon arg0, int arg1);

    public JLabel(javax.swing.Icon arg0);

    public JLabel();

    public javax.swing.plaf.LabelUI getUI();

    public void setUI(javax.swing.plaf.LabelUI arg0);

    public void updateUI();

    public java.lang.String getUIClassID();

    public java.lang.String getText();

    public void setText(java.lang.String arg0);

    public javax.swing.Icon getIcon();

    public void setIcon(javax.swing.Icon arg0);

    public javax.swing.Icon getDisabledIcon();

    public void setDisabledIcon(javax.swing.Icon arg0);

    public void setDisplayedMnemonic(int arg0);

    public void setDisplayedMnemonic(char arg0);

    public int getDisplayedMnemonic();

    public void setDisplayedMnemonicIndex(int arg0) throws java.lang.IllegalArgumentException;

    public int getDisplayedMnemonicIndex();

    protected int checkHorizontalKey(int arg0, java.lang.String arg1);

    protected int checkVerticalKey(int arg0, java.lang.String arg1);

    public int getIconTextGap();

    public void setIconTextGap(int arg0);

    public int getVerticalAlignment();

    public void setVerticalAlignment(int arg0);

    public int getHorizontalAlignment();

    public void setHorizontalAlignment(int arg0);

    public int getVerticalTextPosition();

    public void setVerticalTextPosition(int arg0);

    public int getHorizontalTextPosition();

    public void setHorizontalTextPosition(int arg0);

    public boolean imageUpdate(java.awt.Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5);

    protected java.lang.String paramString();

    public java.awt.Component getLabelFor();

    public void setLabelFor(java.awt.Component arg0);

    public javax.accessibility.AccessibleContext getAccessibleContext();
}
