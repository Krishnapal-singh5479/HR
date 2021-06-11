import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.common.*;
class DesignationDeleteTestCase
{
public static void main(String gg[])
{
try
{
int code=Keyboard.getInt("Enter designation code to delete : ");
new DesignationDAO().delete(code);
System.out.println("Designation with code : "+code+" deleted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
