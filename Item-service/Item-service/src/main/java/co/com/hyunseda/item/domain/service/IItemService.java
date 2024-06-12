package co.com.hyunseda.item.domain.service;

import co.com.hyunseda.item.domain.entity.Item;
import co.com.hyunseda.item.domain.entity.Product;

import java.util.List;

public interface IItemService {
    /**
     * Busca y retorna todos los ítems existentes
     * @return Lista de Items
     */
    public List<Item> findAll();
    /**
     * Busca y retorna un Item por su identificador único.
     * @param id El identificador único de la Item a buscar
     * @param amount Cantidad de productos
     * @return La Item encontrada, o un valor vacío si no se encuentra ningún Item con el ID proporcionado
     */
    public Item findById(Long id, Integer amount);
    /**
     * Crea un nueva Item.
     * @param producto La Item a ser creada
     * @return La Item creada
     */
    public Product create(Product producto);
    /**
     * Actualiza un Item existente.
     * @param producto La Item con los datos actualizados
     * @param id El identificador único de la Item a actualizar
     * @return La Item actualizada
     */

    public Product update(Product producto, Long id);

    /**
     * Elimina un Item por su identificador único.
     * @param id El identificador único de la Item a eliminar
     */
    public void delete(Long id);
}
