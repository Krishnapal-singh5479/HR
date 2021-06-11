package com.thinking.machines.hr.dl.dao;
import java.util.*;
import java.io.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class DesignationDAO implements DesignationDAOInterface
{
public void add(DesignationDTOInterface designationDTO) throws DAOException
{
try
{
File file=new File(DESIGNATION_DATA_FILE);
int vCode=0;
String codeString="";
int count=0;
String countString="";
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes("0         ");
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes("0         ");
randomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(0);
int lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
String vTitle=designationDTO.getTitle().trim();
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle=randomAccessFile.readLine();
if(vTitle.equalsIgnoreCase(fTitle))
{
randomAccessFile.close();
throw new DAOException("Designatio : "+vTitle+" exists.");
}
}
vCode=lastGeneratedCode+1;
randomAccessFile.writeBytes(String.valueOf(vCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vTitle);
randomAccessFile.writeBytes("\n");
count++;
codeString=String.valueOf(vCode);
while(codeString.length()<10) codeString+=" ";
countString=String.valueOf(count);
while(countString.length()<10) countString+=" ";
randomAccessFile.seek(0);
randomAccessFile.writeBytes(codeString+"\n"+countString+"\n");
randomAccessFile.close();
designationDTO.setCode(vCode);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(DesignationDTOInterface designationDTO) throws DAOException
{
try
{
int vCode=designationDTO.getCode();
if(vCode<=0) throw new DAOException("Invalid designation code : "+vCode);
String vTitle=designationDTO.getTitle().trim();
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid designation code : "+vCode);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+vCode);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==vCode)
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+vCode);
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(vCode!=fCode && vTitle.equalsIgnoreCase(fTitle))
{
randomAccessFile.close();
throw new DAOException("Designation : "+vTitle+" exists.");
}
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(vCode!=fCode)
{
tmpRandomAccessFile.writeBytes(fCode+"\n"+fTitle+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(vCode+"\n"+vTitle+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(int code) throws DAOException
{
try
{
if(code<=0) throw new DAOException("Invalid designation code : "+code);
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid designation code : "+code);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+code);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle="";
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+code);
}
if(new EmployeeDAO().designationCodeExists(code))
{
randomAccessFile.close();
throw new DAOException("Employee exists with designation as "+fTitle);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
int count=Integer.parseInt(randomAccessFile.readLine().trim());
count--;
String countString=String.valueOf(count);
while(countString.length()<10) countString+=" ";
tmpRandomAccessFile.writeBytes(countString+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(fCode+"\n"+fTitle+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public int getCount() throws DAOException
{
try
{
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public List<DesignationDTOInterface> getAll() throws DAOException
{
List<DesignationDTOInterface> designations;
designations=new LinkedList<>();
try
{
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) return designations;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return designations;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
DesignationDTOInterface designationDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
designationDTO=new DesignationDTO();
designationDTO.setCode(Integer.parseInt(randomAccessFile.readLine()));
designationDTO.setTitle(randomAccessFile.readLine());
designations.add(designationDTO);
}
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
return designations;
}

public DesignationDTOInterface getByCode(int code) throws DAOException
{
try
{
if(code<=0) throw new DAOException("Invalid designation code : "+code);
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid designation code : "+code);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+code);
}
int lastGeneratedCode;
lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(code>lastGeneratedCode)
{
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+code);
}
randomAccessFile.readLine();
DesignationDTOInterface designationDTO;
int vCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
if(vCode==code)
{
designationDTO=new DesignationDTO();
designationDTO.setCode(vCode);
designationDTO.setTitle(randomAccessFile.readLine());
randomAccessFile.close();
return designationDTO;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
throw new DAOException("Invalid designation code : "+code);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public DesignationDTOInterface getByTitle(String title) throws DAOException
{
try
{
if(title==null || title.trim().length()==0) throw new DAOException("Invalid designation : "+title);
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid designation : "+title);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid designation : "+title);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
DesignationDTOInterface designationDTO;
String vTitle;
int vCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vTitle=randomAccessFile.readLine();
if(title.trim().equalsIgnoreCase(vTitle))
{
designationDTO=new DesignationDTO();
designationDTO.setCode(vCode);
designationDTO.setTitle(vTitle);
randomAccessFile.close();
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid designation : "+title);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean codeExists(int code) throws DAOException
{
try
{
if(code<=0) return false;
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
int lastGeneratedCode;
lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(code>lastGeneratedCode)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int vCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
if(vCode==code)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean titleExists(String title) throws DAOException
{
try
{
if(title==null || title.trim().length()==0) return false;
File file=new File(DESIGNATION_DATA_FILE);
if(!file.exists()) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String vTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
vTitle=randomAccessFile.readLine();
if(title.trim().equalsIgnoreCase(vTitle))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}