package com.example.payment;
/*interface för testning för en klass som inte finns ännu*/
public interface PaymentService {
    PaymentApiResponse charge (String apikey,double amount);
}
