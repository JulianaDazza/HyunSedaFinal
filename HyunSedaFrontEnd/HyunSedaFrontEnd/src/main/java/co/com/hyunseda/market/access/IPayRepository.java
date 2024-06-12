/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Pay;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IPayRepository {
    /**
     * @brief Guarda un producto.
     * @param newPay El nuevo pago a guardar.
     * @return Verdadero si el pago se guardó correctamente, de lo contrario falso.
     */
    boolean save(Pay newPay);
    
    /**
     * @brief Obtiene una lista de todos los pagos.
     * @return Una lista que contiene todos los pagos.
     */
    List<Pay> list();
    
    /**
     * @brief Obtiene un pago
     * @param id
     * @return Un pago con la id ingresada
     */
    Pay findById(int id);
    
    /**
     * @brief Obtiene un pago
     * @param account
     * @return  Un pago comparando la cuenta ingresada
     */
    Pay findByACcount(int account);
}
