import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
public class DesignationDAOTitleExistsTestCase
{
public static void main(String gg[])
{
try
{
String vTitle=gg[0];
boolean title;
title=new DesignationDAO().titleExists(vTitle);
System.out.println("Is title exists : "+title);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}