package controlador;

import config.Config;
import model.ProjectManager;
import network.ServerCommunication;
import views.AuthenticationView;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Esta clase es el controlador, que se encarga de modificar la vista a partir de los datos del modelo
 * Created by Marc on 13/3/18.
 */
public class ClientController implements ActionListener{

    private AuthenticationView authenticationView;//Vista de autenticación
    private MainView mainView;
    private ProjectManager projectManager;              //Modelo
    private Config data;
    //private ServerCommunication serverCommunication;

    /**
     * Constructor del controlador que se encarga de poner las condiciones de inicio a partir de la vista y
     * el modelo recibidos por parámetros
     * @param authenticationView vista de autenticación
     * @param projectManager     modelo
     */
    public ClientController(AuthenticationView authenticationView, MainView mainView, ProjectManager projectManager, Config data) {

        this.authenticationView = authenticationView;
        this.mainView = mainView;
        this.projectManager = projectManager;
        this.data = data;
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

            try {
                serverCommunication.startConnection();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
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

                try {
                    serverCommunication.startConnection();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        }
        if (e.getActionCommand().equals("LOGOUT")){ //Si se quiere salir del programa
            System.exit(1);                  //Se sale del programa con código 1
        }

        if (e.getActionCommand().equals("NEW_PROJECT")){

            mainView.initNewProjectView();
        }

        if (e.getActionCommand().equals("POPUP")){

            mainView.showPopupMenu();
        }

        if (e.getActionCommand().equals("BROWSE")){

            mainView.showBrowseMenu();
        }

        if (e.getActionCommand().equals("CREATE")){

            try {
                mainView.createProject();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("CANCEL")){

            System.out.println("polla");
            mainView.initHomeView();
        }
    }
}
