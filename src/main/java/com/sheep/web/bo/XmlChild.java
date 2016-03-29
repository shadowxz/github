package com.sheep.web.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="child")
public class XmlChild implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int ca=2;
    private String cb="cb";
    
    @XmlElement
    public int getCa() {
        return ca;
    }
    public void setCa(int ca) {
        this.ca = ca;
    }
    
    @XmlElement
    public String getCb() {
        return cb;
    }
    public void setCb(String cb) {
        this.cb = cb;
    }
    
    
    
}
