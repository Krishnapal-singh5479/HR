import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import com.thinking.machines.common.*;
class DesignationManagerAddTestCase
{
public static void main(String gg[])
{
String title=Keyboard.getString("Enter designation title : ");
try
{
DesignationInterface designation;
designation=new Designation();
designation.setTitle(title);
DesignationManager dm=DesignationManager.getDesignationManager();
dm.add(designation);
System.out.println("Designation : "+title+" add with code as "+designation.getCode());
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