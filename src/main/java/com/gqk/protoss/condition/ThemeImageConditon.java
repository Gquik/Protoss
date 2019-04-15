package com.gqk.protoss.condition;

import java.io.Serializable;
import java.util.List;

public class ThemeImageConditon implements Serializable{

    private static final long serialVersionUID = -5809782578272943999L;

    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
