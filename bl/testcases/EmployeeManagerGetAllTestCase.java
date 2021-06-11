import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
class EmployeeManagerGetAllTestCase
{
public static void main(String gg[])
{
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
List<EmployeeInterface> employees;
employees=employeeManager.getAll(EmployeeInterface.NAME);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeInterface employee:employees)
{
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation : "+employee.getDesignation().getCode());
System.out.println("Date of Birth : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Basic Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Is Indian : "+employee.isIndian());
System.out.println("Gender : "+employee.getGender());
System.out.println("Pan Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
System.out.println("---------------------------------------------------");
}
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
