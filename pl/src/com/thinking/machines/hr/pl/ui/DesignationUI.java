package com.thinking.machines.hr.pl.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.ListSelectionModel;
import javax.swing.table.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;

public class DesignationUI extends JFrame implements ListSelectionListener,DocumentListener
{
private JLabel titleLabel;
private JLabel searchLabel;
private JTextField searchTextField;
private JLabel foundAndNotFoundLabel;
private JButton crossButton;
private JTable table;
private JScrollPane jsp;
private DesignationModel model;
private JLabel designationLabel;
private JLabel designationDisplayLabel;
private JTextField designationTextField;
private JButton aButton;
private JButton eButton;
private JButton cButton;
private JButton dButton;
private JButton pButton;
private Container container;
private ImageIcon crossIcon;
private FileNameExtensionFilter f1;
int a=1;
int flag=0;
public DesignationUI()
{
try
{
model=new DesignationModel();
}catch(Exception e)
{
e.printStackTrace();
}
setTitle("HR Automation System");
titleLabel=new JLabel("Designation Master");
Font titleFont=new Font("Verdana",Font.BOLD,24);
titleLabel.setFont(titleFont);
searchLabel=new JLabel("Search");
Font searchFont=new Font("Verdana",Font.BOLD,22);
searchLabel.setFont(searchFont);
searchTextField=new JTextField();
searchTextField.getDocument().addDocumentListener(this);
foundAndNotFoundLabel=new JLabel("Not Found");
Font fnfFont=new Font("Times New Roman",Font.BOLD,12);
foundAndNotFoundLabel.setFont(fnfFont);
foundAndNotFoundLabel.setForeground(Color.RED);
crossButton=new JButton(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\cross_Icon.png"));
crossButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource()==crossButton)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
}
});
table=new JTable(model);
JTableHeader tableHeader=table.getTableHeader();
Font tableHeaderFont=new Font("Verdana",Font.BOLD,14);
tableHeader.setFont(tableHeaderFont);
tableHeader.setResizingAllowed(false);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
ListSelectionModel selectedRow=table.getSelectionModel();
selectedRow.addListSelectionListener(this);
table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
TableColumnModel columnModel=table.getColumnModel();
columnModel.getColumn(0).setPreferredWidth(150);
columnModel.getColumn(1).setPreferredWidth(290+140);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
table.setRowHeight(30);
Border blackline=BorderFactory.createLineBorder(Color.black);
aButton=new JButton(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\add_Icon.png"));
eButton=new JButton(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\edit_Icon.png"));
cButton=new JButton(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\cancel_Icon.png"));
dButton=new JButton(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\delete_Icon.png"));
pButton=new JButton(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\pdf_Icon.png"));
designationLabel=new JLabel("Designation");
Font designationFont=new Font("Verdana",Font.BOLD,18);
designationLabel.setFont(designationFont);
designationDisplayLabel=new JLabel("");
Font designationDisplayFont=new Font("Verdana",Font.BOLD,16);
designationLabel.setFont(designationDisplayFont);
designationLabel.setForeground(Color.black);
designationTextField=new JTextField();
int lm=10;
int tm=0;
titleLabel.setBounds(lm+210,tm+10,300,50);
foundAndNotFoundLabel.setBounds(lm+10+150+30+210+15+20+85,tm+10+40+5,300+100,30);
searchLabel.setBounds(lm+30+30,tm+10+40+10+10+10,200+100,30);
searchTextField.setBounds(lm+10+150+30,tm+10+40+10+10+10,300+100,30);
crossButton.setBounds(lm+10+150+30+50+100+100+100+50,tm+10+40+10+10+10,60,30);
jsp.setBounds(lm+30+30,tm+10+40+10+10+10+50,500+100,150);
designationLabel.setBounds(lm+30+30+50+50+30+20,tm+10+40+10+10+10+50+150+20,150,50);
designationTextField.setBounds(lm+30+30+50+100+100+25+30+5,tm+10+40+10+10+10+50+150+40,150,20);
designationTextField.setVisible(false);
designationDisplayLabel.setBounds(lm+30+30+50+100+100+25+30+5,tm+10+40+10+10+10+50+150+22,150,50);
aButton.setBounds(lm+30+30+30+50+50,tm+10+40+10+10+10+50+100+100+50+20,60,60);
eButton.setBounds(lm+30+30+30+50+50+10+10+50,tm+10+40+10+10+10+50+100+100+50+20,60,60);
cButton.setBounds(lm+30+30+30+50+50+10+20+100+10,tm+10+40+10+10+10+50+100+100+50+20,60,60);
dButton.setBounds(lm+30+30+30+50+50+10+30+150+10+10,tm+10+40+10+10+10+50+100+100+50+20,60,60);
pButton.setBounds(lm+30+30+30+50+50+10+40+200+10+20,tm+10+40+10+10+10+50+100+100+50+20,60,60);
JPanel buttonPanel=new JPanel();
buttonPanel.add(aButton);
buttonPanel.add(eButton);
buttonPanel.add(cButton);
buttonPanel.add(dButton);
buttonPanel.add(pButton);
buttonPanel.setBorder(blackline);
buttonPanel.setBounds(lm+30+30+30+50+20,tm+10+40+10+10+10+50+100+100+50,400,100);
JPanel detailsPanel=new JPanel();
detailsPanel.add(designationLabel);
detailsPanel.add(designationDisplayLabel);
detailsPanel.add(designationTextField);
detailsPanel.setBorder(blackline);
detailsPanel.setBounds(lm+30+30,tm+10+40+10+10+10+50+100+60,300+200+100,250);
JPanel fullPanel=new JPanel();
fullPanel.add(detailsPanel);
fullPanel.add(buttonPanel);
container=getContentPane();
container.setLayout(null);
container.add(titleLabel);
container.add(foundAndNotFoundLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(crossButton);
container.add(jsp);
container.add(designationLabel);
container.add(designationDisplayLabel);
container.add(designationTextField);
container.add(aButton);
container.add(eButton);
container.add(cButton);
container.add(dButton);
container.add(pButton);
container.add(buttonPanel);
container.add(detailsPanel);
container.add(fullPanel);
container.setBackground(Color.white);

if(model.getRowCount()==0)
{
eButton.setEnabled(false);
dButton.setEnabled(false);
pButton.setEnabled(false);
searchTextField.setEnabled(false);
crossButton.setEnabled(false);
table.setEnabled(false);
}
if(model.getRowCount()!=0)
{
cButton.setEnabled(false);
}
aButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource()==aButton)
{
if(a==1)
{
foundAndNotFoundLabel.setVisible(false);
designationDisplayLabel.setVisible(false);
designationTextField.setVisible(true);
designationTextField.requestFocus();
searchTextField.setEnabled(false);
crossButton.setEnabled(false);
table.setEnabled(false);
eButton.setEnabled(false);
dButton.setEnabled(false);
pButton.setEnabled(false);
aButton.setIcon(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\save_Icon.png"));
cButton.setEnabled(true);
a=0;
}
else
{
try
{
DesignationInterface designation=new Designation();
designation.setTitle(designationTextField.getText());
model.add(designation);
int rowIndex=model.indexOf(designation);
table.setRowSelectionInterval(0,rowIndex);
table.scrollRectToVisible(table.getCellRect(rowIndex,0,true));
designationDisplayLabel.setVisible(true);
designationTextField.setText("");
designationTextField.setVisible(false);
searchTextField.setEnabled(true);
crossButton.setEnabled(true);
table.setEnabled(true);
dButton.setEnabled(true);
pButton.setEnabled(true);
eButton.setEnabled(true);
aButton.setIcon(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\add_Icon.png"));
cButton.setEnabled(false);
a=1;
}catch(ModelException me)
{
JOptionPane.showMessageDialog(container,me.getMessage());
a=1;
}
}
}
}
});
cButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource()==cButton)
{
searchTextField.setEnabled(true);
crossButton.setEnabled(true);
table.setEnabled(true);
designationDisplayLabel.setVisible(true);
designationDisplayLabel.setForeground(Color.black);
designationTextField.setVisible(false);
aButton.setEnabled(true);
eButton.setEnabled(true);
dButton.setEnabled(true);
pButton.setEnabled(true);
aButton.setIcon(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\add_Icon.png"));
eButton.setIcon(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\edit_Icon.png"));
cButton.setEnabled(false);
a=1;
}
}
});
eButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource()==eButton)
{
if(table.getSelectionModel().isSelectionEmpty()) JOptionPane.showMessageDialog(container,"Plese Select Any Row");
else
{
if(a==1)
{
foundAndNotFoundLabel.setEnabled(false);
designationDisplayLabel.setVisible(false);
designationTextField.setVisible(true);
designationTextField.requestFocus();
searchTextField.setEnabled(false);
crossButton.setEnabled(false);
table.setEnabled(true);
aButton.setEnabled(false);
dButton.setEnabled(false);
pButton.setEnabled(false);
eButton.setEnabled(true);
eButton.setIcon(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\update_Icon.png"));
cButton.setEnabled(true);
a=0;
}
else
{
try
{
DesignationInterface di=new Designation();
int index=table.getSelectedRow();
di=model.getDesignationAt(index);
di.setTitle(designationTextField.getText());
model.update(di);
int rowIndex=model.indexOf(di);
table.setRowSelectionInterval(0,rowIndex);
table.scrollRectToVisible(table.getCellRect(rowIndex,0,true));
JOptionPane.showMessageDialog(container,"Designation Updated...");
designationDisplayLabel.setVisible(true);
designationTextField.setText("");
designationTextField.setVisible(false);
searchTextField.setEnabled(true);
crossButton.setEnabled(true);
table.setEnabled(true);
aButton.setEnabled(true);
dButton.setEnabled(true);
pButton.setEnabled(true);
eButton.setEnabled(true);
eButton.setIcon(new ImageIcon("C:\\javaeg\\hr\\pl\\classes\\images\\edit_Icon.png"));
cButton.setEnabled(false);
a=1;
}catch(ModelException me)
{
JOptionPane.showMessageDialog(container,me.getMessage());
a=1;
}
}
}
}
}
});
dButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource()==dButton)
{
int index=table.getSelectedRow();
String titleDelete=null;
if(table.getSelectionModel().isSelectionEmpty())
{
JOptionPane.showMessageDialog(container,"Plese Select Any Row");
}
else
{
table.setEnabled(false);
searchTextField.setEnabled(true);
crossButton.setEnabled(true);
aButton.setEnabled(false);
eButton.setEnabled(false);
cButton.setEnabled(true);
pButton.setEnabled(false); 
try
{
titleDelete=model.getDesignationAt(index).getTitle();
}catch(ModelException modelException)
{
System.out.println(modelException);
}
int result=JOptionPane.showConfirmDialog(null,"Sure! You want to delete "+titleDelete,"Confirm Dialog",JOptionPane.YES_NO_OPTION);
if(result==JOptionPane.YES_OPTION) 
{
try
{
model.delete(model.getDesignationAt(index).getCode());
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(container,modelException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
}
JOptionPane.showMessageDialog(null,"Designation deleted","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
table.setRowSelectionInterval(0,0);
table.setEnabled(true);
searchTextField.setEnabled(true);
crossButton.setEnabled(true);
aButton.setEnabled(true);
eButton.setEnabled(true);
cButton.setEnabled(false);
dButton.setEnabled(true);
pButton.setEnabled(true); 
designationLabel.setVisible(true);
foundAndNotFoundLabel.setEnabled(false);
}
if(result==JOptionPane.NO_OPTION) 
{
jsp.setEnabled(true);
table.setEnabled(true);
searchTextField.setEnabled(true);
crossButton.setEnabled(true);
aButton.setEnabled(true);
eButton.setEnabled(true);
cButton.setEnabled(false);
dButton.setEnabled(true);
pButton.setEnabled(true); 
designationLabel.setVisible(true);
foundAndNotFoundLabel.setEnabled(false);
table.setRowSelectionInterval(0,0);
}
}
}
}
});

pButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
JFileChooser fileChooser=new JFileChooser();
int status=fileChooser.showSaveDialog(null);
if(status==JFileChooser.APPROVE_OPTION)
{
File file=fileChooser.getSelectedFile();
if(!(new File(file.getParent()).isDirectory()))
{
JOptionPane.showMessageDialog(null,"Invalid path");
return;
}
if(file.exists())
{
int confirm=JOptionPane.showConfirmDialog(null,file.getName()+" already exists! Would you like to overwrite it?","File already exists",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
if(confirm!=JOptionPane.YES_OPTION) return;
}
String filename=file.getPath();
int i=filename.lastIndexOf(".");
if(i==(-1))
{
filename=filename+".pdf";
file=new File(file.getPath()+".pdf");
fileChooser.setSelectedFile(file);
}
else
{

}
fileChooser.setAcceptAllFileFilterUsed(false);
f1=new FileNameExtensionFilter("PDF file","pdf");
fileChooser.addChoosableFileFilter(f1);
model.exportToPdf(file);
JOptionPane.showMessageDialog(null,"PDF created in "+file.getPath(),"Information",JOptionPane.INFORMATION_MESSAGE);
}
}
});

int width=800;
int height=600;
setSize(width,height);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(width/2),(d.height/2)-(height/2));
setDefaultCloseOperation(EXIT_ON_CLOSE);
}

public void valueChanged(ListSelectionEvent lse)
{
ListSelectionModel m=(ListSelectionModel) lse.getSource();
int rowSelected=m.getMinSelectionIndex();
try
{
designationDisplayLabel.setText(model.getDesignationAt(rowSelected).getTitle());
}catch(ModelException modelException)
{
System.out.println(modelException.getMessage());
a=1;
}
}

public void changedUpdate(DocumentEvent de)
{
foundAndNotFoundLabel.setVisible(false);
searchDesignation();
}
public void removeUpdate(DocumentEvent de)
{
foundAndNotFoundLabel.setVisible(false);
searchDesignation();
}
public void insertUpdate(DocumentEvent de)
{
foundAndNotFoundLabel.setVisible(false);
searchDesignation();
}
public void searchDesignation()
{
DesignationInterface designationInterface=new Designation();
try
{
designationInterface=model.getDesignation(searchTextField.getText(),false,true);
int index=model.indexOf(designationInterface);
table.setRowSelectionInterval(index,index);
table.scrollRectToVisible(new Rectangle(table.getCellRect(index,0,true)));
}catch(ModelException modelException)
{
table.clearSelection();
foundAndNotFoundLabel.setVisible(true);
}
}
}