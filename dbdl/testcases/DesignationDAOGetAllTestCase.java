import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
public class DesignationDAOGetAllTestCase
{
public static void main(String gg[])
{
try
{
List<DesignationDTOInterface> designations;
designations=new DesignationDAO().getAll();
for(DesignationDTOInterface designation:designations)
{
System.out.print("Code : "+designation.getCode());
System.out.println(", Title : "+designation.getTitle());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}