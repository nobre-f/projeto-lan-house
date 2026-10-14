import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ControleDeCarga {

    public static ArrayList<Plataforma> obterCatalogoPlanilha() {
        ArrayList<Plataforma> catalogo = new ArrayList<>();
        String urlApi = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ962C7U1IpG4kLQVtAGGrmtcNOuT8Mo1IKqrvFRF9GY3hciZCUuLZt_x6PRwukrtM7tvlQiUzPcbTn/pub?output=csv";

        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlApi)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String[] linhas = response.body().split("\n");
                
                // Limita a leitura às primeiras 10 linhas para exibição no menu de catálogo
                int limite = Math.min(linhas.length, 11); 

                for (int i = 1; i < limite; i++) {
                    String textoLinha = linhas[i].trim();
                    if (!textoLinha.isEmpty()) {
                        String[] colunas = textoLinha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                        
                        if (colunas.length >= 3) {
                            String brand  = colunas[0].replace("\uFEFF", "").replace("\"", "").trim(); 
                            String model = colunas[1].replace("\"", "").trim(); 
                            String priceRange  = colunas[2].replace("\"", "").trim(); 

                            // Regra de precificação baseada na categoria da planilha
                            int preco = priceRange.equalsIgnoreCase("High") ? 15 : 10;

                            catalogo.add(new Plataforma("Notebook (" + brand + ")", model, preco));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no controle de carga: " + e.getMessage());
        }
        return catalogo;
    }
}
