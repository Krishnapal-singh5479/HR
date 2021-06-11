import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
public class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=Keyboard.getString("Enter employee id. : ");
boolean employee;
employee=new EmployeeDAO().employeeIdExists(employeeId); 
System.out.println("Employee id. exists : "+employee);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
