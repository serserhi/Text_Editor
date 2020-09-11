package editor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;



public class TextEditor extends JFrame {
    //Variables para la lista de posiciones y de texto a buscar
    SortedSet<Integer> posicion = new TreeSet<>();
    int tamaño;
    String texto = "";
    SortedSet<Integer> auxSet = new TreeSet<>();
    int posActual = 0;

    public TextEditor() {
        //Iconos a usar
        ImageIcon Open = new ImageIcon("D:/abrir.png");
        ImageIcon Save = new ImageIcon("D:/salvar.png");
        ImageIcon Search = new ImageIcon("D:/buscar.png");
        ImageIcon Next = new ImageIcon("D:/proximo.png");
        ImageIcon Previous = new ImageIcon("D:/anterior.png");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text Editor");
        setSize(500, 500);
        setLocation(200, 200);


        //Creamos un contenedor donde iremos poniendo los paneles
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(5,5));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));

        //Creamos el panel donde iran los botones y opciones
        JPanel Panel = new JPanel();
        Panel.setBorder(new LineBorder(Color.BLACK, 1));
        Panel.setLayout(new FlowLayout(5));
        mainContainer.add(Panel, BorderLayout.NORTH);

        //Creamos el panel donde irá el area de edición de texto
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(Color.BLACK, 1));
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
        mainContainer.add(bottomPanel, BorderLayout.CENTER);

        JFileChooser FileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileChooser.setName("FileChooser");
        FileChooser.setVisible(false);
        this.add(FileChooser);

        //Area de edición de texto
        JTextArea nameTextArea = new JTextArea();
        nameTextArea.setName("TextArea");
        nameTextArea.setSize(420,420);
        nameTextArea.setVisible(true);
        nameTextArea.setWrapStyleWord(true);
        nameTextArea.setLineWrap(true);
        nameTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(nameTextArea);

        JScrollPane scrollableTextArea = new JScrollPane(nameTextArea);
        scrollableTextArea.setName("ScrollPane");
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scrollableTextArea);



        //Botón Save
        JButton SaveButton = new JButton(Save);
        SaveButton.setName("SaveButton");
        SaveButton.setSize(30,30);
        SaveButton.setIcon(new ImageIcon(Save.getImage().getScaledInstance(SaveButton.getWidth(), SaveButton.getHeight(), Image.SCALE_SMOOTH)));
        Panel.add(SaveButton);
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            //File file = new File(FilenameField.getText());
                String path = "";
                FileChooser.setVisible(true);
                int returnValue = FileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = FileChooser.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    try (FileWriter writer = new FileWriter(path)) {
                        writer.write(nameTextArea.getText());
                    } catch (IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                }
            }
        });

        //Botón Open
        JButton LoadButton = new JButton(Open);
        LoadButton.setName("OpenButton");
        LoadButton.setSize(30,30);
        LoadButton.setIcon(new ImageIcon(Open.getImage().getScaledInstance(LoadButton.getWidth(), LoadButton.getHeight(), Image.SCALE_SMOOTH)));
        Panel.add(LoadButton);
        LoadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            //String path = FilenameField.getText();
                String path = "";
                FileChooser.setVisible(true);
                int returnValue = FileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = FileChooser.getSelectedFile();
                    if (selectedFile.exists()) {
                        path = selectedFile.getAbsolutePath();
                        try {
                            nameTextArea.setText(new String(Files.readAllBytes(Paths.get(path))));
                            FileChooser.setVisible(false);
                        } catch (IOException e) {
                            System.out.println("Cannot read file: " + e.getMessage());
                            nameTextArea.setText("");
                        }
                    } else {
                        nameTextArea.setText("");
                    }
                }

            }
        });

        //El texto a buscar
        JTextField SearchField = new JTextField();
        SearchField.setName("SearchField");
        SearchField.setSize( 200, 20);
        SearchField.setVisible(true);
        Panel.add(SearchField);

        //Checkbox para seleccionar buscar por expresiones o texto plano
        JCheckBox UseRegExCheckbox = new JCheckBox();
        UseRegExCheckbox.setName("UseRegExCheckbox");
        Panel.add(UseRegExCheckbox);

        //Botón BUSCAR
        JButton StartSearchButton = new JButton(Search);
        StartSearchButton.setName("StartSearchButton");
        StartSearchButton.setSize(30,30);
        StartSearchButton.setIcon(new ImageIcon(Search.getImage().getScaledInstance(StartSearchButton.getWidth(), StartSearchButton.getHeight(), Image.SCALE_SMOOTH)));
        Panel.add(StartSearchButton);
        StartSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        buscar( SearchField,  nameTextArea,  UseRegExCheckbox);
                    }
                }).start();

            }
        });

        //Botón BUSCAR anterior
        JButton PreviousButton = new JButton(Previous);
        PreviousButton.setName("PreviousMatchButton");
        PreviousButton.setSize(30,30);
        PreviousButton.setIcon(new ImageIcon(Previous.getImage().getScaledInstance(PreviousButton.getWidth(), PreviousButton.getHeight(), Image.SCALE_SMOOTH)));
        Panel.add(PreviousButton);
        PreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        buscarAnterior(SearchField, nameTextArea, UseRegExCheckbox);
                    }
                }).start();
            }
        });

        //Botón BUSCAR siguiente
        JButton NextButton = new JButton(Previous);
        NextButton.setName("NextMatchButton");
        NextButton.setSize(30,30);
        NextButton.setIcon(new ImageIcon(Next.getImage().getScaledInstance(NextButton.getWidth(), NextButton.getHeight(), Image.SCALE_SMOOTH)));
        Panel.add(NextButton);
        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        buscarSiguiente(SearchField, nameTextArea, UseRegExCheckbox);
                    }
                }).start();
            }
        });

        //Opciones Barra de Menú
        JMenuBar menuBar = new JMenuBar();

        //Menu File
        JMenu MenuFile = new JMenu("File");
        MenuFile.setName("MenuFile");
        MenuFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem loadMenuItem = new JMenuItem("Open");
        loadMenuItem.setName("MenuOpen");
        loadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //String path = FilenameField.getText();
                String path = "";
                FileChooser.setVisible(true);
                int returnValue = FileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = FileChooser.getSelectedFile();
                    if (selectedFile.exists()) {
                        path = selectedFile.getAbsolutePath();
                        try {
                            nameTextArea.setText(new String(Files.readAllBytes(Paths.get(path))));
                            FileChooser.setVisible(false);
                        } catch (IOException e) {
                            System.out.println("Cannot read file: " + e.getMessage());
                            nameTextArea.setText("");
                        }
                    } else {
                        nameTextArea.setText("");
                    }
                }
            }
        });
        MenuFile.add(loadMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //File file = new File(FilenameField.getText());
                String path = "";
                FileChooser.setVisible(true);
                int returnValue = FileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = FileChooser.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    try (FileWriter writer = new FileWriter(path)) {
                        writer.write(nameTextArea.getText());
                    } catch (IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                }
            }
        });
        MenuFile.add(saveMenuItem);
        MenuFile.addSeparator();
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);

            }
        });
        MenuFile.add(exitMenuItem);

        //Menu Search
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");
        searchMenu.setMnemonic(KeyEvent.VK_S);

        JMenuItem searchMenuItem = new JMenuItem("Start search");
        searchMenuItem.setName("MenuStartSearch");
        searchMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buscar( SearchField,  nameTextArea,  UseRegExCheckbox);
            }
        });
        searchMenu.add(searchMenuItem);

        JMenuItem searchMenuPreviousItem = new JMenuItem("Previous search");
        searchMenuPreviousItem.setName("MenuPreviousMatch");
        searchMenuPreviousItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buscarAnterior( SearchField,  nameTextArea,  UseRegExCheckbox);
            }
        });
        searchMenu.add(searchMenuPreviousItem);

        JMenuItem searchMenuNextItem = new JMenuItem("Next match");
        searchMenuNextItem.setName("MenuNextMatch");
        searchMenuNextItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buscarSiguiente( SearchField,  nameTextArea,  UseRegExCheckbox);
            }
        });
        searchMenu.add(searchMenuNextItem);

        JMenuItem searchMenuRegExp = new JMenuItem("Use regular expressions");
        searchMenuRegExp.setName("MenuUseRegExp");
        searchMenuRegExp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               UseRegExCheckbox.doClick();

            }
        });
        searchMenu.add(searchMenuRegExp);


        menuBar.add(MenuFile);
        menuBar.add(searchMenu);
        setJMenuBar(menuBar);

        setVisible(true);

    }


    public void buscar(JTextField SearchField, JTextArea nameTextArea, JCheckBox UseRegExCheckbox) {
        if (texto.isEmpty()) {
            texto=SearchField.getText();
        } else {
            if (texto.equals(SearchField.getText()) == false) {
                posicion.removeAll(posicion);
                tamaño= 0;
                texto = SearchField.getText();
            }
        }
        int aux = 0;
        if (UseRegExCheckbox.isSelected() == true) {
            Pattern stringPattern = Pattern.compile(SearchField.getText());
            Matcher m = stringPattern.matcher(nameTextArea.getText());

            while (m.find()) {
                aux = m.start();
                tamaño = m.group().length();
                posicion.add(aux);
            }
            if (posicion.isEmpty() == false) {
                posActual = posicion.first();
                nameTextArea.setCaretPosition(posActual + tamaño);
                nameTextArea.select(posActual, posActual + tamaño);
                nameTextArea.grabFocus();
            }

        } else {
            aux = nameTextArea.getText().indexOf(SearchField.getText());
            posActual = aux;
            tamaño = SearchField.getText().length();
            while (aux != -1) {
                posicion.add(aux);
                aux = nameTextArea.getText().indexOf(SearchField.getText(), aux +1);
            }
            if (posActual != -1)  {
                nameTextArea.setCaretPosition(posActual + tamaño);
                nameTextArea.select(posActual, posActual + tamaño);
                nameTextArea.grabFocus();
            }
        }

    }

    public void buscarSiguiente(JTextField SearchField, JTextArea nameTextArea, JCheckBox UseRegExCheckbox) {
        if (texto.isEmpty()) {
            texto= SearchField.getText();
        } else {
            if (texto.equals(SearchField.getText()) == false ) {
                posicion.removeAll(posicion);
                texto= SearchField.getText();
            }
        }
        if (posicion.isEmpty() == false) {

                auxSet = posicion.tailSet(posActual + 1);
                if (auxSet.isEmpty() == false) {
                    posActual = auxSet.first();
                } else {
                    posActual = posicion.first();
                }
                nameTextArea.setCaretPosition(posActual + tamaño);
                nameTextArea.select(posActual, posActual + tamaño);
                nameTextArea.grabFocus();

        }
    }

    public void buscarAnterior(JTextField SearchField, JTextArea nameTextArea, JCheckBox UseRegExCheckbox) {
        if (texto.isEmpty()) {
            texto = SearchField.getText();
        } else {
            if (texto.equals(SearchField.getText()) == false) {
                posicion.removeAll(posicion);
                texto= SearchField.getText();
            }
        }
        if (posicion.isEmpty() == false) {

            auxSet = posicion.headSet(posActual);
            if (auxSet.isEmpty() == false) {
                posActual = auxSet.last();
            } else {
                posActual = posicion.last();
            }
            nameTextArea.setCaretPosition(posActual + tamaño);
            nameTextArea.select(posActual, posActual + tamaño);
            nameTextArea.grabFocus();

        }

    }
}


