package webapp.demo5_rpc;

import org.noear.solonclient.annotation.XClient;

@XClient("demo:/demo5/test/")
public interface rockapi {
    Object test1(Integer a);
    Object test2();
    Object test3();
    Object test4();
    Object test5();
}