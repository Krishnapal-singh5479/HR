import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
class GetDesignationTestCase
{
public static void main(String gg[])
{
try
{
DesignationManager dm=DesignationManager.getDesignationManager();
List<DesignationInterface> designations=dm.getDesignations();
for(DesignationInterface designation:designations)
{
System.out.printf("Code %d, Title %s\n",designation.getCode(),designation.getTitle());
}
}catch(BLException blException)
{
if(blException.hasGenericException()) System.out.println(blException.getGenericException());
}
}
}
/*
Don't forget to copy designation.data to bl\testcases folder
*/