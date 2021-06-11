import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class DesignationManagerTitleExistsTestCase
{
public static void main(String gg[])
{
String title=Keyboard.getString("Enter designation title : ");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
boolean vTitle=designationManager.titleExists(title);
System.out.println("Is Designation title exists : "+vTitle);
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}