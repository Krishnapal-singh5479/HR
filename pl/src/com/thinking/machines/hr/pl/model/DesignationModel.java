package com.thinking.machines.hr.pl.model;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Date.*;
import java.text.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.common.*;
import java.io.FileOutputStream.*;
public class DesignationModel extends AbstractTableModel
{
private String title[];
private java.util.List<DesignationInterface> list;
private DesignationManager dm;
public DesignationModel()
{
populateDataStructures();
}
private void populateDataStructures()
{
dm=DesignationManager.getDesignationManager();
try
{
list=dm.getDesignations(DesignationInterface.TITLE);
}catch(BLException blException)
{
java.util.List<String> l=blException.getExceptions();
for(int i=0;i<l.size();i++)
{
String g=l.get(i);
JOptionPane.showMessageDialog(null,g);
//throw new ModelException(g);
}
}
title=new String[2];
title[0]="S.No.";
title[1]="Designation";
}

public int getRowCount()
{
return list.size();
}
public int getColumnCount()
{
return 2;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return (rowIndex+1);
Object obj=list.get(rowIndex).getTitle();
return obj;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;
else
{
return String.class;
}
}
public DesignationInterface getDesignationAt(int e) throws ModelException
{
if(e<0 || e>list.size()) throw new ModelException(" ");
return list.get(e);
}
public void add(DesignationInterface designation) throws ModelException
{
try
{
dm.add(designation);
}catch(BLException be)
{
java.util.List<String> l=be.getExceptions();
for(int i=0;i<l.size();i++)
{
String g=l.get(i);
throw new ModelException(g);
}
}
populateDataStructures();
fireTableDataChanged();
}
public DesignationInterface getDesignation(String text, boolean caseSensitive, boolean partialSearch) throws ModelException
{
int found=0;
int e=0;
if(caseSensitive==false)
{
while(e<list.size())
{
String d=list.get(e).getTitle().toLowerCase();
if(text.equalsIgnoreCase(d) || d.startsWith(text.toLowerCase()))
{
found=1;
break;
}
e++;
}
}
else
{
while(e<list.size())
{
String d=list.get(e).getTitle();
if(text.equals(d) || d.startsWith(text))
{
found=1;
break;
}
e++;
}
}
if(found==0)
{
throw new ModelException("Invalid Designation Entered");
}
DesignationInterface clone=new Designation();
POJOCopier.copy(clone,list.get(e));
return clone;
}
public int indexOf(DesignationInterface di)
{
int i;
String title=di.getTitle();
boolean found=false;
for(i=0;i<list.size();i++)
{
if(title.equalsIgnoreCase(list.get(i).getTitle()))
{
found=true;
break;
}
}
return i;
}
public void update(DesignationInterface designation) throws ModelException
{
try
{
dm.update(designation);
}catch(BLException be)
{
java.util.List<String> l=be.getExceptions();
for(int i=0;i<l.size();i++)
{
String g=l.get(i);
throw new ModelException(g);
}
}
populateDataStructures();
fireTableDataChanged();
}

public void delete(int code) throws ModelException
{
try
{
dm.delete(code);
list=dm.getDesignations();
fireTableDataChanged();
}catch(BLException blException)
{
java.util.List<String> l=blException.getExceptions();
for(int i=0;i<l.size();i++)
{
String g=l.get(i);
throw new ModelException(g);
}
}
}

public void exportToPdf(File selectedFile)
{
try
{
DesignationInterface designation;
String title="";
Font titleFont;
Font dataFont;
titleFont=new Font(Font.FontFamily.HELVETICA,15,Font.BOLD,BaseColor.RED);
dataFont=new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
PdfPTable table1=null;
int size=list.size();
int pageSize=25;
int noOfPages;
int sn=0;
int cp=0;
Document document=new Document();
int result=(size)%(pageSize);
if(result!=0)
{
noOfPages=(size/pageSize)+1;
}
else
{
noOfPages=(size/pageSize);
}//if else ends here
boolean newPage=true;
int i=0;
PdfWriter.getInstance(document,new FileOutputStream(selectedFile));
document.open();
while(i<size)
{
if(newPage==true)
{
cp++;
Font font =new Font(Font.FontFamily.HELVETICA,20,Font.BOLD,BaseColor.BLUE);
PdfPTable table=new PdfPTable(2);
//table.setWidthPercentage(100);
table.setWidths(new int[]{2,24});
table.setTotalWidth(527);
table.setLockedWidth(true);
table.getDefaultCell().setFixedHeight(40);
Image img=Image.getInstance("C:\\javaeg\\hr\\pl\\classes\\images\\pdf_logo.jpg");
PdfPCell cell1=new PdfPCell(img,true);
cell1.setRowspan(2);
PdfPCell cell2=new PdfPCell(new Paragraph("Ajay Enterprise",font));
cell2.setRowspan(2);
cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
PdfPCell cell3=new PdfPCell(new Paragraph(" "));
cell3.setColspan(2);
PdfPCell cell4=new PdfPCell(new Paragraph("List Of Designations",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK) ));
cell4.setColspan(2);
cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell5=new PdfPCell(new Paragraph("Page"+cp+"/"+noOfPages));
cell5.setColspan(2);
cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
cell1.setBorder(PdfPCell.NO_BORDER);
cell2.setBorder(PdfPCell.NO_BORDER);
cell3.setBorder(PdfPCell.NO_BORDER);
cell4.setBorder(PdfPCell.NO_BORDER);
cell5.setBorder(PdfPCell.NO_BORDER);
table.addCell(cell1);
table.addCell(cell2);
table.addCell(cell3);
table.addCell(cell4);
table.addCell(cell5);
document.add(table);
document.add(new Paragraph(" "));
document.add(new Paragraph(" "));
table1=new PdfPTable(2);
table1.setWidthPercentage(100);
table1.setTotalWidth(288);
table1.setLockedWidth(true);
table1.setWidths(new float[]{1,2});
PdfPCell id=new PdfPCell(new Paragraph("S.No.",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK)));
PdfPCell designationTitle=new PdfPCell(new Paragraph("Designation",new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK)));
id.setHorizontalAlignment(Element.ALIGN_RIGHT);
designationTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
table1.addCell(id);
table1.addCell(designationTitle);
newPage=false;
}
//extract ith object from ds
designation=list.get(i);
sn++;
title=designation.getTitle();
PdfPCell snCell=new PdfPCell(new Paragraph(" "+sn));
snCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
PdfPCell titleCell=new PdfPCell(new Paragraph(title,dataFont));
titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
table1.addCell(snCell);
table1.addCell(titleCell);
//footer
if((sn%pageSize)==0||sn==size)
{
document.add(table1);
document.add(new Paragraph(""));
document.add(new Paragraph(""));
document.add(new Paragraph(""));
document.add(new Paragraph("Software by:- Krishnapal Singh Kushwah",new Font(Font.FontFamily.HELVETICA,15,Font.BOLD,BaseColor.BLACK)));
if(sn<size)
{
document.newPage();
newPage=true;
}
}
i++;
}//while loop ktm
document.close();
}catch(Exception e)
{

}
}
}