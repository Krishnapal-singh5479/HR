import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
public class EmployeePANNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
String panNumber=Keyboard.getString("Enter pan number : ");
boolean employee;
employee=new EmployeeDAO().panNumberExists(panNumber); 
System.out.println("PAN number exists : "+employee);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
