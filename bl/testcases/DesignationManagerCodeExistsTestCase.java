import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class DesignationManagerCodeExistsTestCase
{
public static void main(String gg[])
{
int code=Keyboard.getInt("Enter designation code : ");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
boolean vCode=designationManager.codeExists(code);
System.out.println("Is Designation code exists : "+vCode);
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}