package Controller;

import java.util.Scanner;

public class EntradaUsuario {
    public static void main(String[] args) {
        SistemaMetodos metodos = implementarMetodos();
        metodos.adicionarUsuarioPadrao();
        System.out.println("""
            ooooo       .o8                       ooooooooo
            `888'      "888                      d""\"""\""8'
             888   .oooo888   .ooooo.   .oooo.         .8'
             888  d88' `888  d88' `88b `P  )88b       .8'
             888  888   888  888ooo888  .oP"888      .8'
             888  888   888  888    .o d8(  888     .8'
            o888o `Y8bod88P" `Y8bod8P' `Y888""8o   .8'
                    
            """);
        login();
    }
    public static Scanner implementarScanner() {
        return new Scanner(System.in);
    }
    public static SistemaMetodos implementarMetodos() {
        return new SistemaMetodos();
    }
    public static void login() {
        Scanner leitor = implementarScanner();
        SistemaMetodos metodos = implementarMetodos();
        String emailLogin, senhaLogin;
        System.out.println("""
            Executando login
            ----------------


            Digite seu E-mail:
            """);
        emailLogin = leitor.next();
        System.out.println("Digite sua senha:");
        senhaLogin = leitor.next();
        if (metodos.validarEmailLogin(emailLogin)) {
            if (metodos.validarSenhaLogin(senhaLogin)) {
                System.out.println("Login efetuado com sucesso!");
            }
        }
    }
}
