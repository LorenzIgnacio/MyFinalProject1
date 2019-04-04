package com.ignacio.lorenz.prrcmobile;
import com.google.gson.annotations.SerializedName;

public class ClassListItems {

    public String subject;
    public int final_action_date;
    public String statuscode_id;
    public String creator;

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public int getDate(){
        return final_action_date;
    }

    public void setDate(int final_action_date){
        this.final_action_date = final_action_date;
    }


    public String getStatus(){
        return statuscode_id;
    }

    public void setStatus(String statuscode_id){
        this.statuscode_id = statuscode_id;
    }

    public String getCreator(){
        return creator;
    }

    public void setCreator(String creator){
        this.creator = creator;
    }


    /*@SerializedName("subject")
    public String subject;
    @SerializedName("status")
    public String statuscode_id;
    @SerializedName("final date")
    public String final_action_date;
    @SerializedName("creator")
    public String creator;*/


}
