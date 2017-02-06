package ejtang.sizebook;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import java.util.Date;

/**
 * Created by ejtang on 2017-01-28.
 */

public class Person {
    private String name;
    private Date date;
    private float neck, bust,
            chest, waist,
            hip, inseam;
    private String comment;

    public Person(String name) {
        this.name = name;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getNeck() {
        return neck;
    }

    public void setNeck(Float neck) {
        this.neck = neck;
    }

    public Float getBust() {
        return bust;
    }

    public void setBust(Float bust) {
        this.bust = bust;
    }

    public Float getChest() {
        return chest;
    }

    public void setChest(Float chest) {
        this.chest = chest;
    }

    public Float getWaist() {
        return waist;
    }

    public void setWaist(Float waist) {
        this.waist = waist;
    }

    public Float getHip() {
        return hip;
    }

    public void setHip(Float hip) {
        this.hip = hip;
    }

    public Float getInseam() {
        return inseam;
    }

    public void setInseam(Float inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString(){
        SpannableString ss = new SpannableString(this.name);
        ss.setSpan(new RelativeSizeSpan(2f),0,5,0);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),0,5,0);
        return ss + "\n" + this.date;
    }
}

