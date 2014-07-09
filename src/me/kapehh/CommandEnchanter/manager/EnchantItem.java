package me.kapehh.CommandEnchanter.manager;

import java.util.List;

/**
 * Created by Karen on 09.07.2014.
 */
public class EnchantItem {
    private String name;
    private List<EnchantSet> allowsets;
    private int eid;
    private int maxlevel;

    public EnchantItem() {

    }

    public EnchantItem(String name, List<EnchantSet> allowsets, int eid, int maxlevel) {
        this.name = name;
        this.allowsets = allowsets;
        this.eid = eid;
        this.maxlevel = maxlevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnchantSet> getAllowsets() {
        return allowsets;
    }

    public void setAllowsets(List<EnchantSet> allowsets) {
        this.allowsets = allowsets;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getMaxlevel() {
        return maxlevel;
    }

    public void setMaxlevel(int maxlevel) {
        this.maxlevel = maxlevel;
    }
}
