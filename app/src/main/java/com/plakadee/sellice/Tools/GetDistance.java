package com.plakadee.sellice.Tools;

public class GetDistance {



    public static double GetDistance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;

        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        //if (unit == "K") {
        //  dist = dist * 1.609344;
        // else if (unit == "N") {
        //dist = dist * 0.8684;
        //}
        return (dist);
    }


    public static final double PI = 3.14159265;
    public static final double deg2radians = PI / 180.0;


    public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {

        double lat1 = latitude1 * deg2radians;
        double lat2 = latitude2 * deg2radians;
        double lon1 = longitude1 * deg2radians;
        double lon2 = longitude2 * deg2radians;
        // Williams gives two formulae;
        // this is the more accurate for close distances.
        // In practice, the two differed only in the 8th or 9th place, for
        // separations as small as 1 degree.
        double radd = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((lat1 - lat2) / 2),
                2.0)
                + Math.cos(lat1)
                * Math.cos(lat2)
                * Math.pow(Math.sin((lon1 - lon2) / 2), 2.0)));

        return radd;
    }


    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts decimal degrees to radians : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts radians to decimal degrees : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}

