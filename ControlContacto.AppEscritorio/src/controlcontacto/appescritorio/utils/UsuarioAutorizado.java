package controlcontacto.appescritorio.utils;

public class UsuarioAutorizado {
     private static int id;
    private static String login;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UsuarioAutorizado.id = id;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        UsuarioAutorizado.login = login;
    }
}
