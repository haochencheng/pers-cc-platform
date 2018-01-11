package pers.platform.inventory.repository;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.platform.inventory.entity.Inventory;

import java.io.Serializable;

@Repository
public interface InventoryRepo extends CrudRepository<Inventory, Serializable>  {


    /**
     * 库存扣减
     *
     * @param inventory 实体对象
     * @return rows
     */
    @SQLUpdate(sql = "update inventory set total_inventory =#{totalInventory}," +
            " lock_inventory= #{lockInventory} " +
            " where product_id =#{productId}  and  total_inventory >0  ")
    int decrease(Inventory inventory);


    /**
     * 库存扣减confirm
     *
     * @param inventory 实体对象
     * @return rows
     */
    @SQLUpdate(sql = "update inventory set " +
            " lock_inventory= #{lockInventory} " +
            " where product_id =#{productId}  and lock_inventory >0 ")
    int confirm(Inventory inventory);


    /**
     * 库存扣减 cancel
     *
     * @param inventory 实体对象
     * @return rows
     */
    @SQLUpdate(sql = "update inventory set total_inventory =#{totalInventory}," +
            " lock_inventory= #{lockInventory} " +
            " where product_id =#{productId}  and lock_inventory >0 ")
    int cancel(Inventory inventory);

    /**
     * 根据商品id找到库存信息
     *
     * @param productId 商品id
     * @return Inventory
     */
    Inventory findByProductId(String productId);

}
