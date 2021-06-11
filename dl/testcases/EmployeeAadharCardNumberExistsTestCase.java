import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
public class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
String aadharCardNumber=Keyboard.getString("Enter aadhar card number : ");
boolean employee;
employee=new EmployeeDAO().aadharCardNumberExists(aadharCardNumber); 
System.out.println("Aadhar card number exists : "+employee);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
