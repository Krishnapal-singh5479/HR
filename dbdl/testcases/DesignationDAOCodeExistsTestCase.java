import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
public class DesignationDAOCodeExistsTestCase
{
public static void main(String gg[])
{
try
{
int vCode=Integer.parseInt(gg[0]);
boolean code;
code=new DesignationDAO().codeExists(vCode);
System.out.println("Is code exists : "+code);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}