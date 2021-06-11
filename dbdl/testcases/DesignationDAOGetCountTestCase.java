import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
public class DesignationDAOGetCountTestCase
{
public static void main(String gg[])
{
try
{
System.out.println("Number of designations : "+new DesignationDAO().getCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}