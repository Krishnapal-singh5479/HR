import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
class EmployeeManagerGetCountTestCase
{
public static void main(String gg[])
{
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
int count;
count=employeeManager.getCount();
System.out.print("Number of Employee added is : "+count);
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}