package com.example.final_project_group_12;

import java.util.ArrayList;
import java.util.List;

public class RouteList {

    public static List<Routes> routeArrayList = new ArrayList<>();

    public static void addRoute(Routes r){

        routeArrayList.add(r);

    }

    public static void removeRoute(Routes r){

        routeArrayList.remove(r);
    }

    public static void searchRoute(String routeName){


    }
}
