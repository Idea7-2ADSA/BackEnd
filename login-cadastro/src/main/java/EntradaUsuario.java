import java.util.Objects;
import java.util.Scanner;

public class EntradaUsuario {
    public static void main(String[] args) {
        SistemaMetodos metodos = implementarMetodos();
        metodos.adicionarUsuarioPadrao();
        Scanner leitor = implementarScanner();
        Integer numero;
        do {
            System.out.println("""
                ooooo       .o8                       ooooooooo
                `888'      "888                      d""\"""\""8'
                 888   .oooo888   .ooooo.   .oooo.         .8'
                 888  d88' `888  d88' `88b `P  )88b       .8'
                 888  888   888  888ooo888  .oP"888      .8'
                 888  888   888  888    .o d8(  888     .8'
                o888o `Y8bod88P" `Y8bod8P' `Y888""8o   .8'
                        
                Escolha qual deseja efetuar:
                1 - Cadastro
                2 - Login
                """);
             numero = leitor.nextInt();
        }while (numero != 1 && numero != 2);

        if (numero == 1) {
            cadastro();
        } else{
            login();
        }
    }
    public static Scanner implementarScanner() {
        return new Scanner(System.in);
    }
    public static SistemaMetodos implementarMetodos() {
        return new SistemaMetodos();
    }
    public static void cadastro() {
        Scanner leitor = implementarScanner();
        SistemaMetodos metodos = implementarMetodos();
        String nomeCadastro, cnpjCadastro, emailCadastro, senhaCadastro, confirmarSenhaCadastro;
        System.out.println("Executando cadastro: ");
        System.out.println("--------------------");
        System.out.println("Digite seu Nome:");
        nomeCadastro = leitor.next();
        System.out.println("Digite seu CNPJ:");
        cnpjCadastro = leitor.next();
        System.out.println("Digite seu E-mail");
        emailCadastro = leitor.next();
        System.out.println("Digite sua Senha:");
        senhaCadastro = leitor.next();
        System.out.println("Confirme a sua senha:");
        confirmarSenhaCadastro = leitor.next();
        metodos.cadastrarUsuario(nomeCadastro, cnpjCadastro, emailCadastro, senhaCadastro, confirmarSenhaCadastro);
        login();
    }

    public static void login() {
        Scanner leitor = implementarScanner();
        SistemaMetodos metodos = implementarMetodos();
        String emailLogin, senhaLogin;
        System.out.println("Executando login: ");
        System.out.println("--------------------");
        System.out.println("Digite seu E-mail:");
        emailLogin = leitor.next();
        System.out.println("Digite sua senha:");
        senhaLogin = leitor.next();
        boolean existeEmail = metodos.validarEmailLogin(emailLogin);
        boolean existeSenha = metodos.validarSenhaLogin(senhaLogin);
        if (existeEmail && existeSenha) {
            System.out.println("Login efetuado com sucesso!");
        }else {
            System.out.println("E-mail ou senha inválidos!");
        }
    }
}
