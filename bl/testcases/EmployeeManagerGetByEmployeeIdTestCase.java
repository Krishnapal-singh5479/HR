import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
import java.math.*;
class EmployeeManagerGetByEmployeeIdTestCase
{
public static void main(String gg[])
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
String EmployeeId=Keyboard.getString("Enter Employee Id : ");
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
EmployeeInterface employee;
employee=new Employee();
employee=employeeManager.getByEmployeeId(EmployeeId);
String vName=employee.getName();
DesignationInterface vDesignation=employee.getDesignation();
String vTitle=vDesignation.getTitle();
boolean vIsIndian=employee.isIndian();
String vGender=employee.getGender();
String vDateOfBirth=simpleDateFormat.format(employee.getDateOfBirth());
BigDecimal vBasicSalary=employee.getBasicSalary();
String vPanNumber=employee.getPANNumber();
String vAadharCardNumber=employee.getAadharCardNumber();
System.out.println("Name : "+vName);
System.out.println("Designation : "+vTitle);
System.out.println("Is employee is Indian : "+vIsIndian);
System.out.println("Gender : "+vGender);
System.out.println("Basic Salary of the employee : "+vBasicSalary);
System.out.println("Date of Birth of the employee : "+vDateOfBirth);
System.out.println("Pan Number of the employee : "+vPanNumber);
System.out.println("Aadhar Card Number of the employee : "+vAadharCardNumber);
}catch(BLException b)
{
List<String> list=b.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
System.out.println(g);
}
}
}
}
