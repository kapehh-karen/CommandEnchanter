package me.kapehh.CommandEnchanter.manager;

import java.util.List;

/**
 * Created by Karen on 09.07.2014.
 */
public class EnchantSet {
    private String name;
    private List<Integer> ids;

    public EnchantSet() {

    }

    public EnchantSet(String name, List<Integer> ids) {
        this.name = name;
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
