package com.kozluck.EmployeesApp.domain.utils;

import java.util.Set;

public class Ids {
    public static int getNewId(Set<Integer> keys){
        if(keys.isEmpty()) return 0;
        else {
            Integer integer = keys.stream().max((Integer::compareTo)).get();
            return integer+1;
        }
    }

}
