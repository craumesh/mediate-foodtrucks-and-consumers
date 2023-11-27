package com.fooding.payment.db;

public class MailThread extends Thread {
	private String email;
    private int detail_id;
    private int mid;
    private String productName;

    public MailThread(String email, int detail_id, int mid, String productName) {
    	this.productName = productName;
        this.email = email;
        this.detail_id = detail_id;
        this.mid = mid;
    }
    public MailThread(String email, int detail_id, int mid) {
    	this.email = email;
    	this.detail_id = detail_id;
    	this.mid = mid;
    }

    @Override
    public void run() {
        // 메일 전송
        MailDAO mailDAO = new MailDAOImpl();
        if (productName != null) {
            mailDAO.sendRefundEmail(email, detail_id, mid, productName);
        } else {
            mailDAO.sendAllRefundEmail(email, detail_id, mid);
        }
    }

}
