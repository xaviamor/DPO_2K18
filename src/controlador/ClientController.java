package controlador;

import model.ProjectManager;
import network.ServerCommunication;
import views.AuthenticationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase es el controlador, que se encarga de modificar la vista a partir de los datos del modelo
 * Created by Marc on 13/3/18.
 */
public class ClientController implements ActionListener{

    private AuthenticationView authenticationView;      //Vista de autenticación
    private ProjectManager projectManager;              //Modelo
    //private ServerCommunication serverCommunication;

    /**
     * Constructor del controlador que se encarga de poner las condiciones de inicio a partir de la vista y
     * el modelo recibidos por parámetros
     * @param authenticationView vista de autenticación
     * @param projectManager     modelo
     */
    public ClientController(AuthenticationView authenticationView, ProjectManager projectManager) {

        this.authenticationView = authenticationView;
        this.projectManager = projectManager;
        //this.serverCommunication = serverCommunication;
    }

    /**
     * Se encarga de gestionar los procesos que se llevan a cabo cuando una acción tiene lugar, en este
     * caso, cuando se apreta un botón
     * @param e accion
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("SIGNIN")){         //Si se quiere iniciar sesión

                projectManager.getUsuari().setNom("entrar");    //Al nombre del usuario se le asigna la palabra entrar
                                                                //siempre para saber que se inicia sesión (servidor)

                projectManager.getUsuari().setCorreu(authenticationView.getNom());  //Al correo se le asigna el nombre o
                                                                                    //correo introducido

                projectManager.getUsuari().setPassword(authenticationView.getPassword());   //A la contraseña se le asigna
                                                                                            //la contraseña introducida

            ServerCommunication serverCommunication = new ServerCommunication(projectManager);

            serverCommunication.startConnection();
        }
        if (e.getActionCommand().equals("SIGNUP")) {       //Si se quiere registrarse

            if (authenticationView.getContrasenya().length() < 8) {     //Contraseña tiene que tener como mínimo 8 carácteres

                authenticationView.shortPasswordError();                //Pop Up de error
                authenticationView.setConfirmacio("");                  //Limpia el text field de la contraseña
                authenticationView.setContrasenya("");                  //Limpia el text field de la confirmación

            } else if (!authenticationView.checkString(authenticationView.getContrasenya())){   //Contraseña tiene que contener como mínimo una mayúscula, una minúscula y un número

                authenticationView.numberUpperLowerCasePassword();      //Pop Up de error
                authenticationView.setContrasenya("");
                authenticationView.setConfirmacio("");

            } else if (!authenticationView.getContrasenya().equals(authenticationView.getConfirmacio())){   //Contraseña y confirmación tienen que ser iguales

                authenticationView.passwordsDontMatch();                //Pop Up de error
                authenticationView.setConfirmacio("");
                authenticationView.setContrasenya("");

            } else if (!authenticationView.validate(authenticationView.getCorreu())){       //Correo tiene qu estar en el formato correcto

                authenticationView.badEmailFormat();                    //Pop Up de error
                authenticationView.setCorreo("");                       //Limpia el text field del correo

            }else{

                projectManager.getUsuari().setNom(authenticationView.getUsername());    //Al nombre se le asigna el nombre introducido

                projectManager.getUsuari().setCorreu(authenticationView.getCorreu());   //Al correo se le asigna el correo introducido

                projectManager.getUsuari().setPassword(authenticationView.getContrasenya());    //A la contraseña se le asigna la contraseña introducida

                ServerCommunication serverCommunication = new ServerCommunication(projectManager);

                serverCommunication.startConnection();

            }
        }
        if (e.getActionCommand().equals("LOGOUT")){ //Si se quiere salir del programa
            System.exit(1);                  //Se sale del programa con código 1
        }
    }
}
