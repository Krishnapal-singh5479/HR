import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
class GetEmployeesCountWithDesignationTestCase
{
public static void main(String gg[])
{
int code=Keyboard.getInt("Enter a code : ");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
int count;
count=designationManager.getEmployeesCountWithDesignation(code);
System.out.println("Employee with designation code "+code+" is "+count);
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}