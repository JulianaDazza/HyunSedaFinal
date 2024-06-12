package co.com.hyunseda.user.domain.entity;

/**
 * @ Enumeración que va a guardar todos los roles del servicio
 */
public enum ERole {
    SUPER_ADMIN,/**<! Persona con mayores privilegios*/
    ADMIN,/**<! Persona con grandes privilegios*/
    EDITOR,/**<! Persona con menores privilegios*/
    INVITED/**<! Persona sin privilegios, solo compra*/
}
