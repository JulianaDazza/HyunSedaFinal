package co.com.hyunseda.market.domain;

/**
 *
 * @author ACER
 */
public enum ERole {
    SUPER_ADMIN,/**<! Persona con mayores privilegios*/
    ADMIN,/**<! Persona con grandes privilegios*/
    EDITOR,/**<! Persona con menores privilegios*/
    INVITED/**<! Persona sin privilegios, solo compra*/
}
