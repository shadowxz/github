package com.sheep.web.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xmlroot")
public class XmlRoot implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String a = "a";
    
    private Integer b = 1;

    private XmlChild xmlChild = new XmlChild();
    
    private List<XmlChild> xmlChildList = new ArrayList<XmlChild>();
    
    public XmlRoot(){
        xmlChildList.add(new XmlChild());
        xmlChildList.add(new XmlChild());
    }
    
    @XmlElement(name="pa")
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @XmlElement(name="pb")
    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @XmlElement(name="child")
    public XmlChild getXmlChild() {
        return xmlChild;
    }

    public void setXmlChild(XmlChild xmlChild) {
        this.xmlChild = xmlChild;
    }
    
    @XmlElementWrapper(name="childs")
    @XmlElement(name="childList")
    public List<XmlChild> getXmlChildList() {
        return xmlChildList;
    }

    public void setXmlChildList(List<XmlChild> xmlChildList) {
        this.xmlChildList = xmlChildList;
    }
    
}
