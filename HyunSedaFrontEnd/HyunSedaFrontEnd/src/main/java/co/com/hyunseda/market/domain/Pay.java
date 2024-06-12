/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author ACER
 */
public class Pay {
    @JsonProperty("payId")
    private int payId;
    
    @JsonProperty("numAccount")
    private int numAcount;

  
    public Pay() {
    }

    public Pay(int payId, int numAcount) {
        this.payId = payId;
        this.numAcount = numAcount;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getNumAcount() {
        return numAcount;
    }

    public void setNumAcount(int numAcount) {
        this.numAcount = numAcount;
    }
    
    
}
