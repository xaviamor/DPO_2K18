package views;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by xavipargela on 12/3/18.
 */
public class MainView extends JFrame {

    private JButton jbNew;
    private JButton jbUser;
    private JScrollPane jsc1;
    private JScrollPane jsc2;
    private JList userProjects;
    private JList sharedProjects;

    private DefaultListModel<String> dataUser;
    private DefaultListModel<String> dataShared;
    private JList<String> stringsUser;
    private JList<String> stringsShared;

    private JLabel jlProjectName;
    private JTextField jtfProjectName;
    private JLabel jlContributors;
    private JScrollPane jscContributors;
    private JButton jbCreate;
    private JButton jbCancel;

    private JList<String> contributors;
    private DefaultListModel<String> dataContributors;

    public MainView() {

        initHome();
        this.setSize(600, 250);
        this.setTitle("MarksManagement");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initHome() {

        this.getContentPane().removeAll();
        initComponents();
        initListeners();
        initDragDrop();
    }

    public void initComponents(){

        // Esta lista habra que cogerla de la base de datos directamente
        dataUser = new DefaultListModel<>();
        dataUser.addElement("Item1");
        dataUser.addElement("Item2");
        dataUser.addElement("Item3");
        dataUser.addElement("Item4");

        stringsUser = new JList<String>(dataUser);

        dataShared = new DefaultListModel<>();
        dataShared.addElement("Item1");
        dataShared.addElement("Item2");
        dataShared.addElement("Item3");
        dataShared.addElement("Item4");

        stringsShared = new JList<String>(dataShared);

        jbNew = new JButton("New Project");

        jbUser = new JButton("User");

        jbUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showPopupMenu();
            }
        });

        JPanel jpButtons = new JPanel(new BorderLayout());

        jpButtons.add(jbNew, BorderLayout.LINE_START);

        jpButtons.add(jbUser, BorderLayout.LINE_END);

        userProjects = new JList();     //Clase que contendra la info de la DB
        sharedProjects = new JList();

        // Creación de la lista, de momento con un ejemplo clicable
        userProjects.setModel(new AbstractListModel() {

            @Override
            public int getSize() {
                return stringsUser.getModel().getSize();
            }

            @Override
            public Object getElementAt(int i) {
                return stringsUser.getModel().getElementAt(i);
            }
        });

        sharedProjects.setModel(new AbstractListModel() {

            @Override
            public int getSize() {
                return stringsShared.getModel().getSize();
            }

            @Override
            public Object getElementAt(int i) {
                return stringsShared.getModel().getElementAt(i);
            }
        });

        // Adición al window de los Java Components
        userProjects.setPreferredSize(new Dimension(270,100));
        sharedProjects.setPreferredSize(new Dimension(270,100));

        jsc1 = new JScrollPane(userProjects);
        jsc2 = new JScrollPane(sharedProjects);

        jsc1.setBorder(BorderFactory.createTitledBorder("Your Projects"));
        jsc2.setBorder(BorderFactory.createTitledBorder("Shared Projects"));

        JPanel jpLists = new JPanel(new FlowLayout());

        jpLists.add(jsc1);
        jpLists.add(jsc2);

        this.getContentPane().add(jpLists, BorderLayout.CENTER);
        this.getContentPane().add(jpButtons, BorderLayout.NORTH);
        validate();

    }

    private void showPopupMenu() {

        final JPopupMenu popup = new JPopupMenu();

        // New project menu item
        JMenuItem menuItem1 = new JMenuItem("Home", new ImageIcon(((new ImageIcon("icons/home_icon.png"))
                .getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        menuItem1.setMnemonic(KeyEvent.VK_P);
        menuItem1.getAccessibleContext().setAccessibleDescription(
                "Go to the Home screen");
        menuItem1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                initHome();
            }
        });
        popup.add(menuItem1);

        // New project menu item
        JMenuItem menuItem2 = new JMenuItem("New Project", new ImageIcon(((new ImageIcon("icons/addProject_icon.png"))
                .getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        menuItem2.setMnemonic(KeyEvent.VK_P);
        menuItem2.getAccessibleContext().setAccessibleDescription(
                "Create a New Project");
        menuItem2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                initNewProjectView();
            }
        });
        popup.add(menuItem2);

        // Logout menu item
        JMenuItem menuItem3 = new JMenuItem("Logout", new ImageIcon(((new ImageIcon("icons/logout_icon.png"))
                .getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        menuItem3.setMnemonic(KeyEvent.VK_P);
        menuItem3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Logout();
            }
        });


        popup.add(menuItem3);

        popup.show(jbUser, -75, jbUser.getBounds().y + jbUser.getBounds().height);
    }

    private void initNewProjectView() {

        this.getContentPane().removeAll();

        jbNew = new JButton("New Project");

        jbUser = new JButton("User");

        jbUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showPopupMenu();
            }
        });

        JPanel jpButtons = new JPanel(new BorderLayout());

        jpButtons.add(jbNew, BorderLayout.LINE_START);

        jpButtons.add(jbUser, BorderLayout.LINE_END);

        jlProjectName = new JLabel("Insert a name for your project:");
        jtfProjectName = new JTextField("Project name");
        jtfProjectName.setColumns(20);

        jlContributors = new JLabel("Contributors:");
        // Esta lista habra que cogerla de la base de datos directamente
        dataContributors = new DefaultListModel<>();
        dataContributors.addElement("Item1");
        dataContributors.addElement("Item2");
        dataContributors.addElement("Item3");
        dataContributors.addElement("Item4");
        dataContributors.addElement("Item5");
        dataContributors.addElement("Item6");
        dataContributors.addElement("Item7");
        dataContributors.addElement("Item8");
        dataContributors.addElement("Item9");
        dataContributors.addElement("Item10");
        dataContributors.addElement("Item11");
        dataContributors.addElement("Item12");


        contributors = new JList<>(dataContributors);
        contributors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contributors.setLayoutOrientation(JList.VERTICAL);

        jscContributors = new JScrollPane();
        jscContributors.setViewportView(contributors);
        jscContributors.setBorder(BorderFactory.createEmptyBorder());
        jscContributors.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jscContributors.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jbCreate = new JButton("Create");
        jbCancel = new JButton("Cancel");

        JPanel jpName = new JPanel(new FlowLayout());
        jpName.add(jlProjectName);
        jpName.add(jtfProjectName);

        JPanel jpContributors = new JPanel(new BorderLayout());
        jpContributors.add(jlContributors, BorderLayout.NORTH);
        jpContributors.add(jscContributors, BorderLayout.CENTER);

        JPanel jpButtonsBottom = new JPanel(new FlowLayout());
        jpButtonsBottom.add(jbCreate);
        jpButtonsBottom.add(jbCancel);

        JPanel jpAux = new JPanel(new BorderLayout());
        jpAux.add(jpName, BorderLayout.NORTH);
        jpAux.add(jpContributors, BorderLayout.CENTER);
        jpAux.add(jpButtonsBottom, BorderLayout.SOUTH);

        JPanel jpAux2 = new JPanel(new BorderLayout());
        jpAux2.add(jpButtons, BorderLayout.NORTH);
        jpAux2.add(jpAux, BorderLayout.CENTER);

        getContentPane().add(jpAux2);
        validate();

    }

    private void Logout(){

    }

    private void initListeners(){

        //Action Listeners de las dos listas, User y Shared -- Más abajo están los procedimientos a seguir
        userProjects.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                jListUserValueChanged(evt);
            }
        });

        sharedProjects.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                jListSharedValueChanged(evt);
            }
        });
    }

    private void initDragDrop() {

        userProjects.setDragEnabled(true);
        userProjects.setDropMode(DropMode.INSERT);
        userProjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        userProjects.setTransferHandler(new TransferHandler() {

            int index;
            boolean beforeIndex = false;

            @Override
            public int getSourceActions(JComponent comp) {
                return COPY_OR_MOVE;
            }

            @Override
            public Transferable createTransferable(JComponent comp) {
                index = userProjects.getSelectedIndex();
                return new StringSelection ((String) userProjects.getSelectedValue());
            }

            @Override
            public void exportDone( JComponent comp, Transferable trans, int action ) {
                if (action==MOVE) {
                    if (beforeIndex) {
                        dataUser.remove(index + 1);
                    }else{
                        dataUser.remove(index);
                    }
                    jsc1.updateUI();
                }
            }

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                // data of type string?
                return support.isDataFlavorSupported(DataFlavor.stringFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                try {
                    // convert data to string
                    String s = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();
                    dataUser.add(dl.getIndex(), s);
                    beforeIndex = dl.getIndex() < index ? true : false;
                    return true;
                }
                catch (UnsupportedFlavorException | IOException e) {}
                return false;
            }
        });

        sharedProjects.setDragEnabled(true);
        sharedProjects.setDropMode(DropMode.INSERT);
        sharedProjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sharedProjects.setTransferHandler(new TransferHandler() {

            int index;
            boolean beforeIndex = false;

            @Override
            public int getSourceActions(JComponent comp) {
                return COPY_OR_MOVE;
            }

            @Override
            public Transferable createTransferable(JComponent comp) {
                index = sharedProjects.getSelectedIndex();
                return new StringSelection ((String) sharedProjects.getSelectedValue());
            }

            @Override
            public void exportDone( JComponent comp, Transferable trans, int action ) {
                if (action==MOVE) {
                    if (beforeIndex) {
                        dataShared.remove(index + 1);
                    }else{
                        dataShared.remove(index);
                    }
                    jsc2.updateUI();
                }
            }

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                // data of type string?
                return support.isDataFlavorSupported(DataFlavor.stringFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                try {
                    // convert data to string
                    String s = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();
                    dataShared.add(dl.getIndex(), s);
                    beforeIndex = dl.getIndex() < index ? true : false;
                    return true;
                }
                catch (UnsupportedFlavorException | IOException e) {}
                return false;
            }
        });

    }

    private void jListUserValueChanged(javax.swing.event.ListSelectionEvent evt) {
        //set text on right here
        String s = (String) userProjects.getSelectedValue();

        }

    private void jListSharedValueChanged(javax.swing.event.ListSelectionEvent evt) {
        String s = (String) sharedProjects.getSelectedValue();

    }


}
