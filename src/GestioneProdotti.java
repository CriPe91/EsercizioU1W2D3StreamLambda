import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestioneProdotti {
    public static void main(String[] args) {

        List<Prodotto> ListaProdotti = List.of(
                new Prodotto("books",150.56),
                new Prodotto("baby",120.30),
                new Prodotto("boys",143.40),
                new Prodotto("books",130.50),
                new Prodotto("baby",132.54),
                new Prodotto("boys",157.45)
        );

        List<Ordine> ListaOrdini = List.of(
                new Ordine((List<Prodotto>) ListaProdotti.get(0)),
                new Ordine((List<Prodotto>) ListaProdotti.get(1)),
                new Ordine((List<Prodotto>) ListaProdotti.get(2)),
                new Ordine((List<Prodotto>) ListaProdotti.get(3)),
                new Ordine((List<Prodotto>) ListaProdotti.get(4)),
                new Ordine((List<Prodotto>) ListaProdotti.get(5))
        );

      List<Prodotto> PrimaLista = ListaProdotti.stream().filter(prodotto -> "books".equals(prodotto.category) && prodotto.price > 100).collect(Collectors.toList());
      PrimaLista.forEach(prodotto -> System.out.println(prodotto.category + " " + prodotto.price));

      List<Ordine> SecondaLista = ListaOrdini.stream().filter(ordine ->ordine.GetProdotti().stream().anyMatch(prodotto -> "baby".equals(prodotto.category)).collect(Collectors.toList());
    }



}