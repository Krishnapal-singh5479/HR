import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
class DesignationManagerGetCountTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
int count;
count=designationManager.getCount();
System.out.println("Number of Designation added is : "+count);
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}
