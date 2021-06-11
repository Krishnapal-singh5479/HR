import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
class EmployeeManagerEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
String employeeId=Keyboard.getString("Enter Employee Id : ");
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
boolean vEmployeeId=employeeManager.employeeIdExists(employeeId);
System.out.println("Is Employee exists : "+vEmployeeId);
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
