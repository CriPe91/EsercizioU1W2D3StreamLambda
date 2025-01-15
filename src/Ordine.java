import java.util.List;

public class Ordine {
    List<Prodotto> ListaProdotti;

    public Ordine(List<Prodotto> ListaProdotti){
        this.ListaProdotti = ListaProdotti;
    }
    public List<Prodotto> GetProdotti(){
        return ListaProdotti;
    }
}
