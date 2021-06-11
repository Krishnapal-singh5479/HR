import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
class EmployeeManagerAadharCardExistsTestCase
{
public static void main(String gg[])
{
String aadharCard=Keyboard.getString("Enter aadhar card Number : ");
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
boolean vAadharCardNumber=employeeManager.aadharCardNumberExists(aadharCard);
System.out.println("Is Aadhar Card Number exists : "+vAadharCardNumber);
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
