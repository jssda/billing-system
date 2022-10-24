package alipay.mapper;

import alipay.bean.PaymentOrderDetails;
import alipay.bean.PaymentOrderDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderDetailsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int countByExample(PaymentOrderDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int deleteByExample(PaymentOrderDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int insert(PaymentOrderDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int insertSelective(PaymentOrderDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    List<PaymentOrderDetails> selectByExample(PaymentOrderDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    PaymentOrderDetails selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int updateByExampleSelective(@Param("record") PaymentOrderDetails record, @Param("example") PaymentOrderDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int updateByExample(@Param("record") PaymentOrderDetails record, @Param("example") PaymentOrderDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int updateByPrimaryKeySelective(PaymentOrderDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_order_details
     *
     * @mbggenerated Sat Oct 22 21:13:55 GMT+08:00 2022
     */
    int updateByPrimaryKey(PaymentOrderDetails record);
}