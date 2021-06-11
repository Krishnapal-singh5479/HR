package com.thinking.machines.hr.bl.interfaces;
public interface DesignationInterface extends java.io.Serializable,Comparable<DesignationInterface>
{
public enum ATTRIBUTE{CODE,TITLE};
final public ATTRIBUTE CODE=ATTRIBUTE.CODE;
final public ATTRIBUTE TITLE=ATTRIBUTE.TITLE;
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}