import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
public class DesignationGetCountTestCase
{
public static void main(String gg[])
{
try
{
int designation;
designation=new DesignationDAO().getCount();
System.out.println("Number of designation : "+designation);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}