/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author dnoble
 */
@XStreamAlias("Root")
public class Root {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<Datos> listTable;

    public List<Datos> getListTable() {
        return listTable;
    }

    public void setListTable(List<Datos> listTable) {
        this.listTable = listTable;
    }

}
