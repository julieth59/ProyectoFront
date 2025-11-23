package Ejemplofront.front.window;

import Ejemplofront.front.DTOs.LibroDto;
import Ejemplofront.front.DTOs.UsuarioDto;
import Ejemplofront.front.webservicesclient.LibroApiClient;
import Ejemplofront.front.webservicesclient.UsuarioApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class MainFrame extends JFrame {

    @Autowired
    private LibroApiClient libroService;

    @Autowired
    private UsuarioApiClient usuarioService;

    private final JTextField txtusuarioId = new JTextField(10);
    private final JTextField txtlibroId   = new JTextField(10);

    // --- Campos para crear libro ---
    private final JTextField txtTituloNuevo = new JTextField(15);
    private final JTextField txtAutorNuevo = new JTextField(15);
    private final JTextField txtAnioNuevo = new JTextField(6);
    private final JTextField txtCategoriaNuevo = new JTextField(10);
    private final JTextField txtEstadoNuevo = new JTextField(10);

    private JComboBox<String> combousuarios;
    private JComboBox<String> combolibros;

    private final JTextArea txtOutput = new JTextArea(5, 30);
    private final Map<String, Integer> usuarioIdMap = new HashMap<>();
    private final Map<String, Integer> libroIdMap   = new HashMap<>();

    public MainFrame() {
        setSize(800, 600);
    }

    public void initializeUI() {
        setTitle("Matrícula de libros (Spring Boot + Swing)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Cargar datos
        java.util.List<UsuarioDto> usuarioDTOList = usuarioService.getUsuarios();
        java.util.List<LibroDto>   libroDTOList   = libroService.getLibros();

        // ------ COMBOS ------
        DefaultComboBoxModel<String> modelousuarios = new DefaultComboBoxModel<>();
        usuarioIdMap.clear();
        for (UsuarioDto u : usuarioDTOList) {
            String displayKey = String.format("%s, %s", u.getCorreo(), u.getNombre());
            modelousuarios.addElement(displayKey);
            usuarioIdMap.put(displayKey, Math.toIntExact(u.getId()));
        }
        combousuarios = new JComboBox<>(modelousuarios);

        DefaultComboBoxModel<String> modelolibros = new DefaultComboBoxModel<>();
        libroIdMap.clear();
        for (LibroDto c : libroDTOList) {
            String displayKey = String.format("%s, %s", c.getId(), c.getTitulo());
            modelolibros.addElement(displayKey);
            libroIdMap.put(displayKey, Math.toIntExact(c.getId()));
        }
        combolibros = new JComboBox<>(modelolibros);

        // ------ PANEL SUPERIOR (selección / matrícula) ------
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Seleccionar Usuario:"));
        inputPanel.add(combousuarios);

        inputPanel.add(new JLabel("Seleccionar Libro:"));
        inputPanel.add(combolibros);

        inputPanel.add(new JLabel("ID Usuario (e.g., 101):"));
        inputPanel.add(txtusuarioId);

        inputPanel.add(new JLabel("ID Libro (e.g., 500):"));
        inputPanel.add(txtlibroId);

        JButton btnMatricular = new JButton("Matricular Usuario en Libro");
        btnMatricular.addActionListener(e -> enrollUser());
        inputPanel.add(btnMatricular);
        inputPanel.add(new JLabel()); // placeholder

        add(inputPanel, BorderLayout.NORTH);

        // ------ PANEL CENTRAL (log) ------
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registro de Operaciones"));
        add(scrollPane, BorderLayout.CENTER);

        // ------ PANEL INFERIOR (crear libro) ------
        JPanel nuevoLibroPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        nuevoLibroPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Libro"));

        nuevoLibroPanel.add(new JLabel("Título:"));
        nuevoLibroPanel.add(txtTituloNuevo);

        nuevoLibroPanel.add(new JLabel("Autor:"));
        nuevoLibroPanel.add(txtAutorNuevo);

        nuevoLibroPanel.add(new JLabel("Año:"));
        nuevoLibroPanel.add(txtAnioNuevo);

        nuevoLibroPanel.add(new JLabel("Categoría:"));
        nuevoLibroPanel.add(txtCategoriaNuevo);

        nuevoLibroPanel.add(new JLabel("Estado:"));
        nuevoLibroPanel.add(txtEstadoNuevo);

        JButton btnAgregarLibro = new JButton("Agregar Libro");
        btnAgregarLibro.addActionListener(e -> agregarLibro());
        nuevoLibroPanel.add(btnAgregarLibro);

        add(nuevoLibroPanel, BorderLayout.SOUTH);

        // ------ Ventana ------
        setPreferredSize(new Dimension(800, 300));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Matricular (tu lógica original; aquí solo validación de ejemplo) */
    private void enrollUser() {
        int usuarioId, libroId;
        try {
            String selectedUsuario = (String) combousuarios.getSelectedItem();
            String selectedLibro   = (String) combolibros.getSelectedItem();
            System.out.println("valor combo usuario: " + selectedUsuario);
            System.out.println("valor combo libro: " + selectedLibro);
            System.out.println("valor id usuario: " + usuarioIdMap.get(selectedUsuario));
            System.out.println("valor id libro: " + libroIdMap.get(selectedLibro));

            usuarioId = Integer.parseInt(txtusuarioId.getText().trim());
            libroId   = Integer.parseInt(txtlibroId.getText().trim());
            // TODO: aquí llamarías a tu servicio de matrícula si existiera
            txtOutput.append(String.format("Matriculado usuario %d en libro %d%n", usuarioId, libroId));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, ingrese IDs numéricos válidos.",
                    "Error de Entrada",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void agregarLibro() {
         String titulo = txtTituloNuevo.getText().trim();
         String autor = txtAutorNuevo.getText().trim();
         String categoria = txtCategoriaNuevo.getText().trim();
         String estado = txtEstadoNuevo.getText().trim();
         String anioTexto = txtAnioNuevo.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty() || estado.isEmpty() || anioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios (Título, Autor, Año, Categoría y Estado).",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(anioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El año debe ser un número válido.",
                    "Error de Entrada",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        new SwingWorker<LibroDto, Void>() {
            @Override
            protected LibroDto doInBackground() {
                LibroDto dto = new LibroDto();
                dto.setTitulo(titulo);
                dto.setAutor(autor);
                dto.setAnioPublicacion(anio);
                dto.setCategoria(categoria);
                dto.setEstado(estado);

                return libroService.crearLibro(dto);
            }

            @Override
            protected void done() {
                try {
                    LibroDto creado = get();
                    txtOutput.append(String.format("Libro creado: %d - %s (%s)\n",
                            creado.getId(), creado.getTitulo(), creado.getAutor()));

                    // limpiar
                    txtTituloNuevo.setText("");
                    txtAutorNuevo.setText("");
                    txtAnioNuevo.setText("");
                    txtCategoriaNuevo.setText("");
                    txtEstadoNuevo.setText("");

                    refrescarComboLibros();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "No se pudo crear el libro:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }


    /** Vuelve a consultar libros y actualiza el JComboBox */
    private void refrescarComboLibros() {
        java.util.List<LibroDto> libros = libroService.getLibros();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        libroIdMap.clear();
        for (LibroDto c : libros) {
            String displayKey = String.format("%s, %s", c.getId(), c.getTitulo());
            model.addElement(displayKey);
            libroIdMap.put(displayKey, Math.toIntExact(c.getId()));
        }
        combolibros.setModel(model);
    }
}
