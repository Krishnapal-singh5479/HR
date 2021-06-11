import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
import java.math.*;
class EmployeeManagerGetByAadharCardNumberTestCase
{
public static void main(String gg[])
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
String AadharCardNumber=Keyboard.getString("Enter a aadhar card Number : ");
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
EmployeeInterface employee;
employee=new Employee();
employee=employeeManager.getByAadharCardNumber(AadharCardNumber);
String vEmployeeId=employee.getEmployeeId();
String vName=employee.getName();
DesignationInterface vDesignation=employee.getDesignation();
String vTitle=vDesignation.getTitle();
boolean vIsIndian=employee.isIndian();
String vGender=employee.getGender();
String vDateOfBirth=simpleDateFormat.format(employee.getDateOfBirth());
BigDecimal vBasicSalary=employee.getBasicSalary();
String vPanNumber=employee.getPANNumber();
System.out.println("Employee Id : "+vEmployeeId);
System.out.println("Name : "+vName);
System.out.println("Designation : "+vTitle);
System.out.println("Is employee is Indian : "+vIsIndian);
System.out.println("Gender : "+vGender);
System.out.println("Basic Salary : "+vBasicSalary);
System.out.println("Date of Birth : "+vDateOfBirth);
System.out.println("Pan Number : "+vPanNumber);
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
