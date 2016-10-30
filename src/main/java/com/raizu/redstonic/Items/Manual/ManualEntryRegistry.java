package com.raizu.redstonic.Items.Manual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ManualEntryRegistry {

    public static List<ManualEntry> entries = new ArrayList<ManualEntry>();
    public static List<ManualCategory> categories = new ArrayList<ManualCategory>();

    public static void addEntry(ManualEntry entry){
        entries.add(entry);
    }

    public static void addCategory(ManualCategory cat){
        categories.add(cat);
    }

    public static List<ManualCategory> getCategories() {
        return categories;
    }

    public static List<ManualEntry> getEntries() {
        return entries;
    }

    public static List getEntriesByCategory(ManualCategory cat){
        List ent = new ArrayList();
        for(ManualEntry e: entries){
            if(e.category==cat)ent.add(e);
        }
        for(ManualCategory ca : categories){
            if(ca.inCat && cat==ca.cat)ent.add(ca);
        }
        return ent;
    }

    public static void refreshData(){
        categories.clear();
        entries.clear();
        ManualData.initData();
    }
}
