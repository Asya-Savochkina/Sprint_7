package testdata;
import org.apache.commons.lang3.RandomStringUtils;
import objects.NewOrder;

public class OrderTestData {
    public static final String FIRST_NAME = RandomStringUtils.randomAlphanumeric(6);
    public static final String LAST_NAME = RandomStringUtils.randomAlphanumeric(5);
    public static final String ADDRESS = "Mendeleeva st. 4 136";
    public static final String METRO_STATION = "4";
    public static final String PHONE = "+7 999 888 77 66";
    public static final int RENT_TIME = 3;
    public static final String DELIVERY_DATE = "2022-10-31";
    public static final String COMMENT = "Позвонить за час";


    public static NewOrder getOrderRequestAllField(){
        NewOrder newOrder = new NewOrder();
        newOrder.setFirstName(FIRST_NAME);
        newOrder.setLastName(LAST_NAME);
        newOrder.setAddress(ADDRESS);
        newOrder.setMetroStation(METRO_STATION);
        newOrder.setPhone(PHONE);
        newOrder.setRentTime(RENT_TIME);
        newOrder.setDeliveryDate(DELIVERY_DATE);
        newOrder.setComment(COMMENT);
        return newOrder;
    }
}
