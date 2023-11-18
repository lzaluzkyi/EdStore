
import entity.Category;
import entity.Item;
import entity.Order;
import entity.User;
import service.ItemService;
import service.OrderService;
import service.impl.ItemServiceImpl;
import service.impl.OrderServiceImpl;
import utils.DAOUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        EntityManager entityManager = DAOUtils.getEntityManager();
        ItemService itemService = new ItemServiceImpl();
        OrderService orderService = new OrderServiceImpl();

//        Item item = new Item();
//        item.setName("Test nameqwe121233");
//        item.setCreditAvailable(true);
//        item.setDescription("Its very good itemqw22e1123");
//        BigDecimal price = new BigDecimal(135.4).setScale(2 , RoundingMode.HALF_EVEN);
//        item.setPrice(price);
//        item.setStockCount(55);
//        item.setCategory(new Category(1L , "second Category" , null));
//        itemService.create(item);
//
        User user = new User();
        user.setId(1L);

        Item item = itemService.findById(1L);
        System.out.println(item.getName());
        Category category = item.getCategory();

        List<Item> items = category.getItems();
        Order buy = orderService.buy(user, items);
        System.out.println(buy.toString());
        List<Order> all = orderService.getAll();
        System.out.println(all.toString());


    }

}
