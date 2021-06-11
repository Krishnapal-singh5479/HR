import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class DesignationManagerGetByCodeTestCase
{
public static void main(String gg[])
{
int code=Keyboard.getInt("Enter a code to get title : ");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation;
designation=new Designation();
designation=designationManager.getByCode(code);
String title=designation.getTitle();
System.out.println("Designation code : "+code+" is with title as : "+title);
}catch(BLException blException)
{
List<String> list=blException.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
System.out.println(g);
}
}
}
}
