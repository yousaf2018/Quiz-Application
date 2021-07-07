package com.example.quizapplication.Modals;

public class Questions {
    private String desc;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String ans;
    private  String userAns;
    public Questions(){

    }

    public Questions(String desc, String opt1, String opt2, String opt3, String opt4, String ans, String userAns) {
        this.desc = desc;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = ans;

    }

    public String getUserAns() {
        return userAns;
    }

    public void setUserAns(String userAns) {
        this.userAns = userAns;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }


    @Override
    public String toString(){
        return "Description: "+desc+"Option 1:"+opt1+"Option 2:"+opt2+"Option 3:"+opt3+"Option 4:"+opt4+"Answer:"+ans;
    }

}
