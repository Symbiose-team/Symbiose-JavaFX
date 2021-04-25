package symbiose.GestionTerrains.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class paiement {
   /* public static void main() throws StripeException {
          Stripe.apiKey="sk_test_51ITS8FLNTPBfKfeUzGNbDz0DPDyrmYXvB5JI2fXilniC1RpcdW8pM2ggmukff3MpFTIMtMJIpCilRwdZsbO9tP4X00si2vPzsH";
          Map<String,Object> cusomerParameter= new HashMap<String, Object>() ;
               cusomerParameter.put("email","cheima08sassi@gmail.tn");
               Customer newcostumer = Customer.create(cusomerParameter);
              System.out.println("fsf");

     }*/
    public static void main(String[] args) {
        Stripe.apiKey = "sk_test_51ITS8FLNTPBfKfeUzGNbDz0DPDyrmYXvB5JI2fXilniC1RpcdW8pM2ggmukff3MpFTIMtMJIpCilRwdZsbO9tP4X00si2vPzsH";

        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("description", "Example description");
        customerMap.put("email", "esprit@gmail.com");
        customerMap.put("payment_method", "pm_card_visa"); // obtained via Stripe.js

        try {
            Customer customer = Customer.create(customerMap);
           /* System.out.println(customer);
            System.out.println(customer.getId());
           */
           Customer a =Customer.retrieve("cus_JJnoy0NQe2v2i3");
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(a));
         /*   Map<String, Object> Card = new HashMap<String, Object>();
             Card.put("number","424242424242");
            Card.put("exp_month","8");
            Card.put("exp_year","2022");
            Card.put("cvv","524");
            Map<String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card",Card);
            Token token=new Token.create(tokenParam);
            Map<String, Object> source = new HashMap<String, Object>();
            Card.put("source",token.getId());
            a.getSources().create(source);*/

        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

}
