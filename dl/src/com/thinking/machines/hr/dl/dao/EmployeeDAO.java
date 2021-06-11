package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
import java.text.*;
import java.util.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
String vName=employeeDTO.getName().trim();
int vDesignationCode=employeeDTO.getDesignationCode();
Date vDateOfBirth=employeeDTO.getDateOfBirth();
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
boolean vIsIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();
String vPanNumber=employeeDTO.getPANNumber().trim();
String vAadharCardNumber=employeeDTO.getAadharCardNumber().trim();
boolean designationCodeExists=new DesignationDAO().codeExists(vDesignationCode);
if(designationCodeExists==false) throw new DAOException("Invalid designation code : "+vDesignationCode);
try
{
File file=new File(EMPLOYEE_DATA_FILE);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
String newEmployeeId;
int count=0;
int lastGeneratedEmployeeId=100000;
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
}
lastGeneratedEmployeeId=Integer.parseInt(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
if(count==0)
{
count++;
lastGeneratedEmployeeId++;
newEmployeeId="EMP"+(lastGeneratedEmployeeId);
randomAccessFile.writeBytes(newEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(newEmployeeId);
return;
}
String fPanNumber;
String fAadharCardNumber;
boolean foundPanNumber=false;
boolean foundAadharCardNumber=false;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=7;i++) randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(foundPanNumber==false && vPanNumber.equalsIgnoreCase(fPanNumber))
{
foundPanNumber=true;
}
if(foundAadharCardNumber==false && vAadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
foundAadharCardNumber=true;
}
if(foundPanNumber==true && foundAadharCardNumber==true) break;
}
if(foundPanNumber==true && foundAadharCardNumber==true)
{
randomAccessFile.close();
throw new DAOException("PAN Number : "+vPanNumber+" and Aadhar Card Number : "+vAadharCardNumber+ "exists");
}
if(foundPanNumber==false && foundAadharCardNumber==true)
{
randomAccessFile.close();
throw new DAOException("Aadhar Card Number : "+vAadharCardNumber+"exists");
}
if(foundPanNumber==true && foundAadharCardNumber==false)
{
randomAccessFile.close();
throw new DAOException("PAN Number : "+vPanNumber+"exists");
}
lastGeneratedEmployeeId++;
count++;
newEmployeeId="EMP"+lastGeneratedEmployeeId;
randomAccessFile.writeBytes(newEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(newEmployeeId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
String vEmployeeId=employeeDTO.getEmployeeId();
String vName=employeeDTO.getName();
int vDesignationCode=employeeDTO.getDesignationCode();
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
if(designationDAO.codeExists(vDesignationCode)==false)
{
throw new DAOException("Invalid designation code : "+vDesignationCode);
}
Date vDateOfBirth=employeeDTO.getDateOfBirth();
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
boolean vIsIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();
String vPANNumber=employeeDTO.getPANNumber();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
if(vEmployeeId.length()<=3)
{
throw new DAOException("Invalid employee id. : "+vEmployeeId);
}
int employeeIdNumericPart;
try
{
employeeIdNumericPart=Integer.parseInt(vEmployeeId.substring(3));
}catch(NumberFormatException numberFormatException)
{
throw new DAOException("Invalid employee id. : "+vEmployeeId);
}
if(employeeIdNumericPart<=100000)
{
throw new DAOException("Invalid employee id. : "+vEmployeeId);
}
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid employee id. : "+vEmployeeId);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id. : "+vEmployeeId);
}
int lastGeneratedId=Integer.parseInt(randomAccessFile.readLine().trim());
if(employeeIdNumericPart>lastGeneratedId)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id. : "+vEmployeeId);
}
randomAccessFile.readLine();
boolean foundEmployeeId=false;
boolean foundPANNumber=false;
boolean foundAadharCardNumber=false;
String fEmployeeId;
String fPANNumber;
String fAadharCardNumber;
int i;
long positionOfRecordToUpdate=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
if(foundEmployeeId==false)
{
positionOfRecordToUpdate=randomAccessFile.getFilePointer();
}
fEmployeeId=randomAccessFile.readLine();
for(i=1;i<=6;i++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(foundEmployeeId==false && fEmployeeId.equals(vEmployeeId))
{
foundEmployeeId=true;
}
if(foundPANNumber==false && fEmployeeId.equals(vEmployeeId)==false && fPANNumber.equalsIgnoreCase(vPANNumber))
{
foundPANNumber=true;
}
if(foundAadharCardNumber==false && fEmployeeId.equals(vEmployeeId)==false && fAadharCardNumber.equalsIgnoreCase(vAadharCardNumber))
{
foundAadharCardNumber=true;
}
if(foundEmployeeId && foundPANNumber && foundAadharCardNumber) break;
}
if(foundEmployeeId==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id. : "+vEmployeeId);
}
if(foundPANNumber && foundAadharCardNumber)
{
randomAccessFile.close();
throw new DAOException("PAN Number : "+vPANNumber+" and Aadhar Card Number : "+vAadharCardNumber+" exists.");
}
if(foundPANNumber && !foundAadharCardNumber)
{
throw new DAOException("PAN Number : "+vPANNumber+" exists.");
}
if(!foundPANNumber && foundAadharCardNumber)
{
throw new DAOException("Aadhar Card Number : "+vAadharCardNumber+" exists.");
}
randomAccessFile.seek(positionOfRecordToUpdate);
for(i=1;i<=9;i++) randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(positionOfRecordToUpdate);
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(vEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPANNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
long currentIndex=randomAccessFile.getFilePointer();
randomAccessFile.setLength(currentIndex);
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid employee Id. : "+employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
String fEmployeeId;
boolean found=false;
long pointerOfRecordToDelete=0;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
pointerOfRecordToDelete=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine();
for(i=1;i<=8;i++) randomAccessFile.readLine();
if(fEmployeeId.equals(employeeId))
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(pointerOfRecordToDelete);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
randomAccessFile.seek(0);
randomAccessFile.readLine();
count--;
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
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
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int count;
count=Integer.parseInt(randomAccessFile.readLine().trim());
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public List<EmployeeDTOInterface> getAll() throws DAOException
{
try
{
List<EmployeeDTOInterface> employees=new LinkedList<>();
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String vGender;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseExcption)
{
// no code required
}
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.isIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
vGender=randomAccessFile.readLine();
if(vGender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)
{
throw new DAOException("Invalid employee id : "+employeeId);
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id : "+employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equals(employeeId)==false)
{
for(i=1;i<=8;i++) randomAccessFile.readLine();
continue;
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseExcption)
{
// no code required
}
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.isIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
if(randomAccessFile.readLine().equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
randomAccessFile.close();
throw new DAOException("Invalid employee id : "+employeeId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)
{
throw new DAOException("Invalid pan number : "+panNumber);
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid pan number : "+panNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
BigDecimal fBasicSalary;
boolean fIsIndian;
String fGender;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
// nothing is required
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine());
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.isIndian(fIsIndian);
if(fGender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid pan number : "+panNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)
{
throw new DAOException("Invalid Aadhar card number : "+aadharCardNumber);
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Aadhar card number : "+aadharCardNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
BigDecimal fBasicSalary;
boolean fIsIndian;
String fGender;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
// nothing is required
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine());
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.isIndian(fIsIndian);
if(fGender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid adhar card number : "+aadharCardNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean employeeIdExists(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fEmployeeId;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equals(employeeId))
{
randomAccessFile.close();
return true;
}
for(i=1;i<=8;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean designationCodeExists(int designationCode) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int vDesignationCode;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
vDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(vDesignationCode==designationCode)
{
randomAccessFile.close();
return true;
}
for(i=1;i<=6;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean panNumberExists(String panNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fPANNumber;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=7;i++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
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

public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fAadharCardNumber;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=8;i++) randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
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