/*
 *
 * Copyright 2017-2018 549477611@qq.com(xiaoyu)
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 *
 */

package pers.platform.demo.order.respository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.platform.demo.order.model.Order;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoyu
 */
@Repository
public interface OrderRepo extends CrudRepository<Order, Serializable> {

    /**
     * 保存订单
     *
     * @param order 订单对象
     * @return rows
     */
    @Query(value = " INSERT INTO `order` (create_time,`number`,status,product_id,total_amount,`count`,user_id) " +
            " values ( :#{#order.createTime},:#{#order.number},:#{#order.status},:#{#order.productId},:#{#order.totalAmount},:#{#order.count},:#{#order.userId})" ,nativeQuery = true)
    //int add(Date createTime, String number, Integer status, String productId, BigDecimal totalAmount,Integer count,String userId);
    @Modifying
    int add(@Param("order") Order order);

    /**
     * 更新订单
     *
     * @param order 订单对象
     * @return rows
     */
    @Query(value = "update `order` set status = :#{#order.status} , total_amount=:#{#order.totalAmount} where `number`=:#{#order.number}",nativeQuery = true)
    @Modifying
    int update(@Param("order") Order order);

    /**
     * 获取所有的订单
     *
     * @return List<Order>
     */
    List<Order> findAll();
}
