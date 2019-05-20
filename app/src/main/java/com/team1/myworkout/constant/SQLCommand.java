package com.team1.myworkout.constant;

/**
 * Created by Guancheng on 2017/12/1.
 */

public abstract class SQLCommand {

    public static String QUERY_1 = "select * from Plan";

    public static String QUERY_2 = "select M_id, M_name from Machine where M_stat  = '1';";

    public static String QUERY_3 = "SELECT Machine.M_id, COUNT(*) FROM Machine, Plan WHERE Machine.M_id = Plan.M_id AND YEAR = 2017 AND MM >= 01 AND MM <= 12 GROUP BY Machine.M_id";

    public static String QUERY_4 = "SELECT Machine.M_id, COUNT(*) FROM Machine, Plan WHERE Machine.M_id = Plan.M_id AND YEAR = 2018 AND MM >= 01 AND MM <= 12 GROUP BY Machine.M_id";

    public static String QUERY_5 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'arm'";

    public static String QUERY_6 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'leg'";

    public static String QUERY_7 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'hip'";

    public static String QUERY_8 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'chest'";

    public static String QUERY_9 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'cardio'";

    public static String QUERY_10 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'back'";

    public static String QUERY_11 = "select M_name, L_desc from Machine, Machine_Workout, Workout, Location where Machine.M_id=Machine_Workout.M_id and Machine_Workout.W_id=Workout.W_id and Location.L_id=Machine.L_id and Workout.W_cat= 'shoulder'";

    public static String QUERY_12 = "select M_name, L_desc from Machine, Location where Location.L_id=Machine.L_id";

    public static String QUERY_13 = "select M_id, M_name from Machine";

    public static String QUERY_14 = "select L_desc, count(*) from Machine, Plan, Location where Plan.M_id=Machine.M_id and Machine.L_id = Location.L_id group by L_desc";

    public static String QUERY_15 = "select W_cat, W_detail from Workout";

    public static String QUERY_16 = "select L_desc from Location";

    //public static int i = 1;

}
