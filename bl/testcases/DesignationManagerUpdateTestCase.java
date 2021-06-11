import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
class DesignationManagerUpdateTestCase
{
public static void main(String gg[])
{
int code=Keyboard.getInt("Enter a designation code to update : ");
String title=Keyboard.getString("Enter a designation title to update : ");
try
{
DesignationManager designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation;
designation=new Designation();
designation.setCode(code);
designation.setTitle(title);
designationManager.update(designation);
System.out.println("Designation Updated.....");
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