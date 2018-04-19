package com.zeromirai.classassistant.common.model;

/**
 * Created by initialize on 2018/4/16.
 */

@Deprecated
public class Curriculum {

    public static final int MAX_WEEKNUM = 21;
    public static final int MAX_PERIODNUM = 3;

    public class CurriculumEntity{
        public CurriculumEntity(){ }
        public String className;
        public String classPosi;
        public String teacherName;
        public boolean[] weekStatus = new boolean[MAX_WEEKNUM];
        public String[] startWeek = new String[MAX_PERIODNUM];
        public String[] endtWeek = new String[MAX_PERIODNUM];
    }

    public Curriculum(){ }
}
