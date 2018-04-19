package com.zeromirai.classassistant.common.model;

/**
 * Created by initialize on 2018/4/16.
 */

public class Classmate extends BaseUser{

    private String uid;
    private String realName;

    private String QQNumber;
    private String emailAddress;
    private String phoneNumber;
    private String password;

    private String sno;
    private String snoPassword;

    /*课表*/
    private CurriculumPic curriculum = new CurriculumPic();

    @Override
    public String toString(){
        return null;
    }
}
