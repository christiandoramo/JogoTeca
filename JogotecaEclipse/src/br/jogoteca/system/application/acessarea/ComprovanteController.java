package br.jogoteca.system.application.acessarea;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import br.jogoteca.system.models.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ComprovanteController extends AcessAreaController implements Initializable {
    @FXML
    protected Text cpf;

    @FXML
    protected Text dados;

    @FXML
    protected Text idPedido;

    @FXML
    protected Text idVenda;

    @FXML
    protected Text metodoPagamento;

    @FXML
    protected Text momento;

    @FXML
    protected Text nomeComprador;

    @FXML
    protected Text nomeJogo;

    @FXML
    protected Text valor;

    @FXML
    protected VBox compravanteDeCompra;

    private List<String> linhasComprovante;

    @FXML
    void imprimirComprovante(ActionEvent event) throws IOException {
        String filePath = "comprovante.txt";
        FileWriter writer = new FileWriter(filePath);
        for (String linha : linhasComprovante) {
            writer.write(linha + "\n");
        }
        writer.close();

    }

    @FXML
    void sairParaMeusJogos(ActionEvent event) throws IOException {
        irParaMeusJogos(event);
        linhasComprovante = null;
    }

    @FXML
    void sairParaMeusPedidos(ActionEvent event) throws IOException {
        irParaMeusPedidos(event);
        linhasComprovante = null;
    }

    void carregarComprovante(Venda venda) {
        String _idvenda = Integer.toString(venda.getId());
        String _idpedido = Integer.toString(venda.getPedido().getId());
        String _metodo = venda.getPedido().getMetodo().name();
        String _momento = venda.getMomento().toString();
        List<String> _dados = venda.getDadosBancarios();
        String _cpf = venda.getPedido().getUser().getCPF();
        String _nomeComprador = venda.getPedido().getUser().getNome();
        String _total = String.format("%.2f", venda.getPedido().totalValue());
        List<String> _itens = venda.getPedido().getItens().stream().map(x -> x.getGame().getName()).collect(Collectors.toList());
        cpf.setText("CPF do Comprador: " + _cpf);
        idPedido.setText("id do Pedido: " + _idpedido);
        idVenda.setText("id da Venda: " + _idvenda);
        metodoPagamento.setText("Forma de Pagamento: " + _metodo);
        momento.setText("Momento da Compra: " + _momento);
        String d = _dados.stream()
                .reduce((s1, s2) -> s1 + " | " + s2)
                .orElse("");
        dados.setText("Dados Bancários: " + d);
        String i = _itens.stream()
                .reduce((s1, s2) -> s1 + " | " + s2)
                .orElse("");
        nomeJogo.setText(i);
        nomeComprador.setText("Nome do Comprador: " + _nomeComprador);
        valor.setText("Valor da Compra: " + _total);
        linhasComprovante = Arrays.asList(idVenda.getText(),
                idPedido.getText(), metodoPagamento.getText(), momento.getText(),
                dados.getText(), cpf.getText(), nomeComprador.getText(),
                valor.getText(), nomeJogo.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarComprovante(vendaAtual);
    }
}
