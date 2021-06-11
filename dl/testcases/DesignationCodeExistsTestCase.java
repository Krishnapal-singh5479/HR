import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
public class DesignationCodeExistsTestCase
{
public static void main(String gg[])
{
try
{
int designationCode=Keyboard.getInt("Enter designation code : ");
boolean designation;
designation=new DesignationDAO().codeExists(designationCode); 
System.out.println("Designation code exists : "+designation);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
