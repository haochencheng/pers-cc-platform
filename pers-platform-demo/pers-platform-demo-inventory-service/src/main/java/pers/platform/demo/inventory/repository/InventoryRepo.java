package pers.platform.demo.inventory.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.platform.demo.inventory.model.Inventory;

import java.io.Serializable;

@Repository
public interface InventoryRepo extends CrudRepository<Inventory, Serializable>  {


    /**
     * 库存扣减
     *
     * @param inventory 实体对象
     * @return rows
     */
    @Query(value = "update inventory set total_inventory =:#{#inventory.totalInventory}," +
            " lock_inventory= :#{#inventory.lockInventory} " +
            " where product_id =:#{#inventory.productId}  and  total_inventory >0  ",nativeQuery = true)
    @Modifying
    int decrease(@Param("inventory") Inventory inventory);


    /**
     * 库存扣减confirm
     *
     * @param inventory 实体对象
     * @return rows
     */
    @Query(value = "update inventory set " +
            " lock_inventory= :#{#inventory.lockInventory} " +
            " where product_id =:#{#inventory.productId}  and lock_inventory >0 ",nativeQuery = true)
    @Modifying
    int confirm(@Param("inventory") Inventory inventory);


    /**
     * 库存扣减 cancel
     *
     * @param inventory 实体对象
     * @return rows
     */
    @Query(value = "update inventory set total_inventory =:#{#inventory.totalInventory}," +
            " lock_inventory= :#{#inventory.lockInventory} " +
            " where product_id =:#{#inventory.productId}  and  total_inventory >0  ",nativeQuery = true)
    @Modifying
    int cancel(@Param("inventory") Inventory inventory);

    /**
     * 根据商品id找到库存信息
     *
     * @param productId 商品id
     * @return Inventory
     */
    Inventory findByProductId(String productId);

}
