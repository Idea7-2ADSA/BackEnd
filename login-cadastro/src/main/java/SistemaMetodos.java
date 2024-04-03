import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class SistemaMetodos {
     static List<Usuario> listaUsuarios = new ArrayList<>();
    boolean validarNomeCadastro(String nomeCadastro) {
        return !Pattern.compile("\\d+").matcher(nomeCadastro).matches();
    }
    boolean validarCNPJ(String cnpjCadastro) {
       return Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$").matcher(cnpjCadastro).matches();
    }
    boolean validarEmail(String emailCadastro) {
        return Pattern.compile("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b").matcher(emailCadastro).matches();
    }
    boolean validarSenhaCadastro(String senha, String confirmarSenha) {
        return  senha.equals(confirmarSenha);
    }
    void cadastrarUsuario(String nome, String cnpj, String email, String senha, String confirmarSenha) {
        boolean nomeValido, cnpjValido, emailValido, senhaValida;
        nomeValido = validarNomeCadastro(nome);
        cnpjValido = validarCNPJ(cnpj);
        emailValido = validarEmail(email);
        senhaValida = validarSenhaCadastro(senha, confirmarSenha);
        if (nomeValido && cnpjValido && emailValido && senhaValida) {
            Usuario usuario = new Usuario();
            usuario.usuario(nome, email, cnpj, senha);
            listaUsuarios.add(usuario);
            System.out.println(String.format("""
                    
                    Cadastro realizado.
                    
                    """));
        }else {
            System.out.println("Falha ao cadastrar");
        }
    }
   protected void adicionarUsuarioPadrao() {
       Usuario usuario = new Usuario();
       usuario.usuario("Daniel", "daniel@gmail.com", "11.111.111/1111-11", "daniel123");
       listaUsuarios.add(usuario);
   }
    boolean validarEmailLogin (String emailLogin) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            return emailLogin.equals(listaUsuarios.get(i).getEmail());
        }
        return false;
    }
    boolean validarSenhaLogin(String senhaLogin) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            return senhaLogin.equals(listaUsuarios.get(i).getSenha());
        }
        return false;
    }
}