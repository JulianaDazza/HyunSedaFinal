package co.com.hyunseda.item.presentation.rest;

import co.com.hyunseda.item.domain.entity.Item;
import co.com.hyunseda.item.domain.entity.Product;
import co.com.hyunseda.item.domain.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Servicios web de Items
 *
 * @author wpantoja, ahurtado
 *
 */
@RestController
@RequestMapping("items")
public class ItemController {
    @Autowired
    private IItemService itemService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}/amount/{amount}")
    public Item findById(@PathVariable Long id, @PathVariable Integer amount) {

        return itemService.findById(id, amount);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Product create(@RequestBody Product product) {
        return itemService.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product producto, @PathVariable Long id) {
        return itemService.update(producto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
