import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
class EmployeeManagerPanNumberExistsTestCase
{
public static void main(String gg[])
{
String panNumber=Keyboard.getString("Enter PAN Number : ");
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
boolean vPanNumber=employeeManager.panNumberExists(panNumber);
System.out.println("Is Pan Number exists : "+vPanNumber);
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
