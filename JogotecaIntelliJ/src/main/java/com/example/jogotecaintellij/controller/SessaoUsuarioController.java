package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.exception.CredenciaisIncorretasException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SessaoUsuarioController {
    private IGenericRepository<SessaoUsuario> sessaoRepository;
    private static SessaoUsuarioController instance;
    private static final long serialVersionUID = 1L;

    private SessaoUsuarioController() {
        // construtor privado para impedir criação de objetos fora da classe
        this.sessaoRepository = new GenericRepository<>("sessoes.dat");
        usuarioCorrente = null;
    }

    public static SessaoUsuarioController getInstance() {
        if (instance == null) {
            instance = new SessaoUsuarioController();
        }
        return instance;
    }


    private SessaoUsuario sessao;
    private LocalDateTime logon;
    // usar no construtor do controlador
    private LocalDateTime logoff;
    private Usuario usuarioCorrente;
    /*tela login -> tela do feed usuario
    tela login <- tela do feed usuario*/
    private ItemJogo itemCorrente;
    /*tela do feed do usuario -> tela do perfil do jogo
    tela do feed do usuario <- tela do perfil do jogo
    tela da wishlist -> tela do perfil do jogo
    tela da wishlist <- tela do perfil do jogo*/

    private List<ItemJogo> itensCorrentes;
    /*tela da wishlist -> tela do pedidopagamento
    tela da wishlist <- tela do pedidopagamento
    tela do perfil do Jogo -> tela do pedidopagamento
    tela do perfil do jogo <- tela do pedidopagamento*/
    private List<Pedido> pedidosCorrentes;
    /*tela meu pefil -> tela meus pedidos
    tela meu pefil <- tela meus pedidos*/
    private Pedido pedidoCorrente;
    /*tela meu pefil -> tela meus pedidos
    tela meu pefil <- tela meus pedidos*/
    private List<Venda> vendasCorrentes;
    /*tela meus pedidos -> tela Comprovante
    tela meus pedidos <- tela Comprovante*/
    private Venda vendaCorrente;
    /*tela meus pedidos -> tela Comprovante
    tela meus pedidos <- tela Comprovante
    tela pedidoPagamento -> tela Comprovante
    tela pedidoPagamento <- tela Comprovante*/

    public void setUsuarioCorrente(Usuario user) {
        usuarioCorrente = user;
    }

    public boolean usuarioLogado() {
        return usuarioCorrente != null;
    }

    public Usuario getUsuarioCorrente() {
        return usuarioCorrente;
    }

    public ItemJogo getItemCorrente() {
        return itemCorrente;
    }

    public void setItemCorrente(ItemJogo itemCorrente) {
        this.itemCorrente = itemCorrente;
    }

    public List<ItemJogo> getItensCorrentes() {

        return itensCorrentes;
    }

    public void setItensCorrentes(List<ItemJogo> itensCorrentes) {
        this.itensCorrentes = itensCorrentes;
    }

    public List<Pedido> getPedidosCorrentes() {
        return pedidosCorrentes;
    }

    public void setPedidosCorrentes(List<Pedido> pedidosCorrentes) {
        this.pedidosCorrentes = pedidosCorrentes;
    }

    public Pedido getPedidoCorrente() {
        return pedidoCorrente;
    }

    public void setPedidoCorrente(Pedido pedidoCorrente) {
        this.pedidoCorrente = pedidoCorrente;
    }

    public List<Venda> getVendasCorrentes() {
        return vendasCorrentes;
    }

    public void setVendasCorrentes(List<Venda> vendasCorrentes) {
        this.vendasCorrentes = vendasCorrentes;
    }

    public Venda getVendaCorrente() {
        return vendaCorrente;
    }

    public void setVendaCorrente(Venda vendaCorrente) {
        this.vendaCorrente = vendaCorrente;
    }

    public boolean logarUsuario(String login, String senha) throws CredenciaisIncorretasException {
        // validar usuário e senha, etc.
        UsuarioController uc = UsuarioController.getInstance();
        try {
            Usuario usuario = uc.searchUserByLogin2(login);

            // improvisando os logs, quando da exception aqui vai aponta usuario não registrado
            if (usuario != null)
                if (uc.checaLoginESenha2(login, senha)) {
                    setUsuarioCorrente(usuario);
                    sessao = new SessaoUsuario(getUsuarioCorrente());
                    sessaoRepository.insert(new SessaoUsuario(getUsuarioCorrente()));
                    // quando da exception aqui vai dar credenciais errada apos retornar false
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioLogado();
    }

    public List<Pedido> buscarListaPedidosUsuarioAtual() throws ElementsDoNotExistException {
        // função usada em meus pedidos
        PedidoController pc = PedidoController.getInstance();
        return pc.buscarTodos().stream().filter(x -> x.getUser().equals(usuarioCorrente)).collect(Collectors.toList());
    }

    public List<ItemJogo> buscarListaItensJogosCompradosUsuarioAtual() throws ElementsDoNotExistException {
        // função usada em meus jogos
        VendaController vc = VendaController.getInstance();
        List<Venda> compras = vc.buscarListaDeComprasDoUsuario(usuarioCorrente);
        return compras
                .stream()
                .filter(compra -> compra.getPedido().getUser().equals(usuarioCorrente))
                .flatMap(x -> x.getPedido().getItens().stream())
                .collect(Collectors.toList());
        // retorna uma lista List<ItemJogo> e não uma lista List<List<ItemJogo>>
    }

    public void deslogarUsuario() {
        // setar data do log e gerar o arquivo da sessao
        // como no codigo de imprimir o comprovante
        try {
            sessao.setLogoff();
            sessaoRepository.update(sessao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sessao = null;
        setUsuarioCorrente(null);
    }
}
