package com.fooding.payment.db;

public interface MailDAO {
    void sendRefundEmail(String email, int detail_id, int mid, String productName);
    
    
    void sendAllRefundEmail(String email, int detail_id, int mid);

}
