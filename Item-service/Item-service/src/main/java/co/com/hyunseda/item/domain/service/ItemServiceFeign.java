package co.com.hyunseda.item.domain.service;

import co.com.hyunseda.item.domain.entity.Item;
import co.com.hyunseda.item.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion de IItemService. Servicios del crud de Items. Se utiliza una
 * dependencia Feign, que es una forma declarativa de comunicar microservicios
 *
 * @author wpantoja, ahurtado
 *
 */
@Service("serviceFeign")
public class ItemServiceFeign implements IItemService {
    @Autowired
    private IProductClientRest clientFeign;

    @Override
    public List<Item> findAll() {
        return clientFeign.list().stream().map(p ->  new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        return new Item(clientFeign.detail(id), amount);
    }

    @Override
    public Product create(Product product) {
        return clientFeign.create(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return clientFeign.update(product, id);
    }

    @Override
    public void delete(Long id) {
        clientFeign.delete(id);

    }
}
