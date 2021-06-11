import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class DesignationManagerGetByTitleTestCase
{
public static void main(String gg[])
{
String title=Keyboard.getString("Enter a title to get code : ");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation;
designation=new Designation();
designation=designationManager.getByTitle(title);
int code=designation.getCode();
System.out.println("Designation title : "+title+" is with code as : "+code);
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
