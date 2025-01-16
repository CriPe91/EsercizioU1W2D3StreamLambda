import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class GestioneProdotti {

    private static List<Product> productList = new ArrayList<Product>();
    private static List<Order> orderList = new ArrayList<Order>();
    private static List<Customer> customerList = new ArrayList<Customer>();


    public static void main(String[] args) {

        Logger log = LoggerFactory.getLogger(GestioneProdotti.class);

        createProductList();
        createCustomerList();
        createOrderList();

//        System.out.println("Products");
//        productList.forEach(ele -> System.out.println(ele));
//        System.out.println("Customer");
//        customerList.forEach(System.out::println);
//        System.out.println("Order");
//        orderList.forEach(System.out::println);


        // ESERCIZIO 1
        System.out.println("Prodotti uguale a Books e maggiori di 100: ");
        BooksFiltered().forEach(System.out::println);

        // ESERCIZIO 2
        System.out.println("Ordini con prodotti Baby: ");
        BabyOrder().forEach(System.out::println);

        // Esercizio 3
        System.out.println("--- Esercizio 3 ---");
        getBoys().forEach(System.out::println);

        // Esercizio 4
        System.out.println("--- Esercizio 4 ---");
        getTierProducts().forEach(System.out::println);

        //ESERCIZIO 1 GIORNO 2
        System.out.println("Raggruppare gli Ordini per Cliente");
        StampaOrdini();

        //ESERCIZIO 2 GIORNO 2
        System.out.println("Totale Vendite");
        TotaleVendite().forEach((customer,total)->{
            System.out.println("Cliente: " + customer + " " + "Totale :" + total);
        });

        //ESERCIZIO 3 GIORNO 2
        System.out.println("Acquisto piu Costoso: ");
        AcquistoPiuCostoso().forEach(System.out::println);

        //ESERCIZIO 4 GIORNO 2
        System.out.println("Prezzo Medio per Ordine: ");
        System.out.println(MediaPrezzoOrdine());

        //ESERCIZIO 5 GIORNO 2
        System.out.println("Prodotti e Somma Prezzo per Categoria: ");
        System.out.println(CategoriaETotale());
    }
         // ESERCIZIO 1
    public static List<Product> BooksFiltered(){
           List<Product> listaFiltrata = productList.stream().filter(product -> product.getCategory().equals("Books") && product.getPrice()>100).toList();
           return listaFiltrata;
    }

        //ESERCIZIO 2
    public static List<Order> BabyOrder(){
        List<Order> ordineFiltrato = orderList.stream()
                .filter(order -> order.getProducts()
                        .stream().anyMatch(product -> product.getCategory().equals("Baby"))).toList();
        return ordineFiltrato;
    }

    //ESERCIZIO 3

    public static List<Product> getBoys() {
        List<Product> listaBoys = productList.stream()
                .filter(p -> p.getCategory().equals("Boys"))
                .map(product -> {
                    product.setPrice(product.getPrice() * 0.90);
                    return product;
                }).toList();
        return listaBoys;
    }

    //ESERCIZIO 4

    public static List<Product> getTierProducts() {
        List<Order> filterOrder = orderList.stream()
                .filter(order -> order.getCustomer().getTier() == 2
                        && order.getOrderDate().isBefore(LocalDate.parse("2025-01-20"))
                        && order.getOrderDate().isAfter(LocalDate.parse("2025-01-01")))
                .toList();


        List<Product> products = new ArrayList<>();
        for (Order order : filterOrder) {
            products.addAll(order.getProducts());
        }
        return products;
    }


    //ESERCIZIO 1 GIORNO 2

    public static Map<Customer,List<Order>> RaggruppaOrdini(){
        Map<Customer,List<Order>> mappaOrdini = orderList.stream().
                collect(Collectors.groupingBy(Order::getCustomer));
        return mappaOrdini;

    }
    public static void StampaOrdini(){
       RaggruppaOrdini().forEach((key,value)-> System.out.println("Raggruppa Ordini: " + key + " "+ value));
    }

    //ESERCIZIO 2 GIORNO 2

    public static Map<Customer,Double> TotaleVendite(){
        return orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,Collectors.summingDouble(Order::priceTotal)));


    }

    //ESERCIZIO 3 GIORNO 2

    public static List<Product> AcquistoPiuCostoso(){
       return productList.stream()
               .sorted(Comparator.comparing(Product::getPrice).reversed())
               .limit(3).toList();

    }

    //ESERCIZIO 4 GIORNO 2

    public static double MediaPrezzoOrdine(){
        return orderList.stream()
                .mapToDouble(Order::priceTotal)
                .average() // calcola media
                .orElse(0.0); // eccezione
    }

    //ESERCIZIO 5 GIORNO 2

public static Map<String,Double> CategoriaETotale(){
        return productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory,Collectors.summingDouble(Product::getPrice)));
           // groupingBy ---> raggruppa per ;
    }



        public static void createProductList(){

        Product p1 = new Product(1,1000,"smartphone","iphone");
     Product p2 = new Product(2, 127.15, "Books", "ABC");
     Product p3 = new Product(3, 5.8, "Baby", "pannolini");
     Product p4 = new Product(4, 31, "Books", "Il Signore Degli Anelli");
     Product p5 = new Product(5, 100, "Boys", "Spiderman");
     Product p6 = new Product(6, 2, "Baby", "Ciuccio");

     productList.addAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
 }

 public static void createCustomerList(){

     Customer c1 = new Customer(1, "Aldo Baglio", 1);
     Customer c2 = new Customer(2, "Giovanni Storti", 2);
     Customer c3 = new Customer(3, "Giacomo Poretti", 3);
     Customer c4 = new Customer(4, "Marina Massironi", 2);

customerList.addAll(Arrays.asList(c1,c2,c3,c4));
 }

 public static void createOrderList(){

     Order o1 = new Order(1, customerList.get(0));
     Order o2 = new Order(2, customerList.get(1));
     Order o3 = new Order(3, customerList.get(2));
     Order o4 = new Order(4, customerList.get(3));
     Order o5 = new Order(3, customerList.get(2));


     Product p1 = productList.get(0);
     Product p2 = productList.get(1);
     Product p3 = productList.get(2);
     Product p4 = productList.get(3);
     Product p5 = productList.get(4);
     Product p6 = productList.get(5);

     o1.addProduct(p1);
     o1.addProduct(p3);
     o1.addProduct(p5);

     o2.addProduct(p1);
     o2.addProduct(p4);

     o3.addProduct(p2);
     o3.addProduct(p4);
     o3.addProduct(p3);
     o3.addProduct(p6);

     o4.addProduct(p2);
     o4.addProduct(p6);

     o5.addProduct(p1);
     o5.addProduct(p2);
     o5.addProduct(p4);

     orderList.addAll(Arrays.asList(o1,o2,o3,o4,o5));
 }

}