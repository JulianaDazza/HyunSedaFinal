/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.IPayRepository;
import co.com.hyunseda.market.access.PayRestRepository;
import co.com.hyunseda.market.domain.Pay;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class PayService {
    IPayRepository payR;
    private List<Pay> payList;

    public PayService(IPayRepository payR) {
        this.payR = payR;
        this.payList = new ArrayList<>();
    }

    public PayService() {
        this.payR = new PayRestRepository();
    }
    
    
  
    
    public boolean savePay(Pay prmPay)
    {
        payList.add(prmPay);
        return payR.save(prmPay);
    }
    
    public Pay findPayById(int id)
    {
        return payR.findById(id);
    }
     public Pay findPayByAccount(int account)
    {
        return payR.findByACcount(account);
    }
    
    public List<Pay> findAllPay(){
        return payR.list();
    }
    
}
