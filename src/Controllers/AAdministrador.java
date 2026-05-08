package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import services.*;
import utilidades.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AAdministrador implements Initializable {

    // ==================== COMPONENTES FXML ====================

    @FXML
    private TextField codigoEspacioField;

    @FXML
    private TableColumn<Espacio, String> colCodigoEspacio;

    @FXML
    private TableColumn<Espacio, TipoVehiculo> colTipoEspacio;

    @FXML
    private TableColumn<Espacio, Estado> colEstadoEspacio;

    @FXML
    private TableColumn<UsuarioAutorizado, String> colNombreUsuarioAut;

    @FXML
    private TableColumn<UsuarioAutorizado, String> colIdentificacionUsuarioAut;

    @FXML
    private TableColumn<UsuarioAutorizado, TipoUsuario> colTipoUsuarioAut;

    @FXML
    private TableColumn<UsuarioAutorizado, Double> colDescuentoUsuarioAut;

    @FXML
    private TableColumn<UsuarioAutorizado, String> colPlacaUsuarioAut;

    @FXML
    private PasswordField confirmarContrasenaOperadorField;

    @FXML
    private PasswordField contrasenaOperadorField;

    @FXML
    private TextField descuentoUsuarioAutField;

    @FXML
    private ComboBox<Estado> estadoEspacioCombo;

    @FXML
    private TextField identificacionUsuarioAutField;

    @FXML
    private Label mensajeRegistroOperadorLabel;

    @FXML
    private TextField nombreCompletoOperadorField;

    @FXML
    private TextField nombreOperadorField;

    @FXML
    private TextField nombreUsuarioAutField;

    @FXML
    private TextField placaUsuarioAutField;

    @FXML
    private TableView<Espacio> tablaEspacios;

    @FXML
    private TableView<UsuarioAutorizado> tablaUsuariosAutorizados;

    @FXML
    private TextField tarifaBiciField;

    @FXML
    private TextField tarifaCarroField;

    @FXML
    private TextField tarifaMotoField;

    @FXML
    private Label tarifasMensajeLabel;

    @FXML
    private ComboBox<TipoVehiculo> tipoEspacioCombo;

    @FXML
    private ComboBox<TipoUsuario> tipoUsuarioAutCombo;

    @FXML
    private Label usuarioLogueadoLabel;

    // ==================== VARIABLES DE SERVICIO ====================

    private Parqueadero parqueadero;
    private Administrador administradorActual;
    private Sesion sesion;

    // ==================== INICIALIZACIÓN ====================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializar servicios
        parqueadero = ParqueaderoStorage.cargarDatos();
        sesion = Sesion.getInstancia();
        administradorActual = (Administrador) sesion.getUsuarioActual();

        // Configurar usuario logueado
        if (administradorActual != null) {
            usuarioLogueadoLabel.setText("Admin: " + administradorActual.getNombre());
        }

        // Inicializar ComboBoxes
        inicializarComboBoxes();

        // Configurar columnas de tablas
        configurarColumnasTablas();

        // Cargar datos iniciales
        cargarEspacios();
        cargarUsuariosAutorizados();
        cargarTarifasActuales();
    }

    private void inicializarComboBoxes() {
        tipoEspacioCombo.getItems().setAll(TipoVehiculo.CARRO, TipoVehiculo.MOTO, TipoVehiculo.BICICLETA);
        estadoEspacioCombo.getItems().setAll(Estado.DISPONIBLE, Estado.FUERA_DE_SERVICIO);
        tipoUsuarioAutCombo.getItems().setAll(TipoUsuario.ESTUDIANTE, TipoUsuario.DOCENTE, TipoUsuario.ADMINISTRATIVO);
    }

    private void configurarColumnasTablas() {
        // Configurar columnas de espacios
        colCodigoEspacio.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colTipoEspacio.setCellValueFactory(new PropertyValueFactory<>("tipoEspacio"));
        colEstadoEspacio.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Configurar columnas de usuarios autorizados
        colNombreUsuarioAut.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdentificacionUsuarioAut.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colTipoUsuarioAut.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        colDescuentoUsuarioAut.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        colPlacaUsuarioAut.setCellValueFactory(new PropertyValueFactory<>("placaVehiculo"));
    }

    private void cargarEspacios() {
        tablaEspacios.getItems().clear();
        if (parqueadero.getEspacios() != null) {
            tablaEspacios.getItems().addAll(parqueadero.getEspacios());
        }
    }

    private void cargarUsuariosAutorizados() {
        tablaUsuariosAutorizados.getItems().clear();
        if (parqueadero.getUsuariosAutorizados() != null) {
            tablaUsuariosAutorizados.getItems().addAll(parqueadero.getUsuariosAutorizados());
        }
    }

    private void cargarTarifasActuales() {
        // Buscar tarifas existentes y mostrarlas en los campos
        for (Tarifa tarifa : parqueadero.getTarifas()) {
            if (tarifa.getTipoVehiculo() == TipoVehiculo.CARRO) {
                tarifaCarroField.setText(String.valueOf(tarifa.getValorHora()));
            } else if (tarifa.getTipoVehiculo() == TipoVehiculo.MOTO) {
                tarifaMotoField.setText(String.valueOf(tarifa.getValorHora()));
            } else if (tarifa.getTipoVehiculo() == TipoVehiculo.BICICLETA) {
                tarifaBiciField.setText(String.valueOf(tarifa.getValorHora()));
            }
        }
    }

    // ==================== GESTIÓN DE ESPACIOS ====================

    @FXML
    void handleRegistrarEspacio(ActionEvent event) {
        String codigo = codigoEspacioField.getText().trim();
        TipoVehiculo tipoEspacio = tipoEspacioCombo.getValue();
        Estado estado = estadoEspacioCombo.getValue();

        // Validaciones
        if (codigo.isEmpty()) {
            mostrarAlerta("Error", "El código del espacio es obligatorio", Alert.AlertType.ERROR);
            return;
        }

        if (tipoEspacio == null) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de espacio", Alert.AlertType.ERROR);
            return;
        }

        if (estado == null) {
            estado = Estado.DISPONIBLE;
        }

        // Verificar si ya existe un espacio con ese código
        if (parqueadero.buscarEspacio(codigo) != null) {
            mostrarAlerta("Error", "Ya existe un espacio con el código " + codigo, Alert.AlertType.ERROR);
            return;
        }

        // Crear y registrar el espacio
        Espacio nuevoEspacio = new Espacio(codigo, tipoEspacio);
        nuevoEspacio.setEstado(estado);

        if (parqueadero.crearEspacio(nuevoEspacio)) {
            mostrarAlerta("Éxito", "Espacio registrado correctamente", Alert.AlertType.INFORMATION);
            limpiarCamposEspacio();
            cargarEspacios();
            ParqueaderoStorage.guardarDatos(parqueadero);
        } else {
            mostrarAlerta("Error", "No se pudo registrar el espacio", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleModificarEspacio(ActionEvent event) {
        Espacio espacioSeleccionado = tablaEspacios.getSelectionModel().getSelectedItem();

        if (espacioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un espacio para modificar", Alert.AlertType.ERROR);
            return;
        }

        // Crear diálogo de modificación
        TextInputDialog codigoDialog = new TextInputDialog(espacioSeleccionado.getCodigo());
        codigoDialog.setTitle("Modificar Espacio");
        codigoDialog.setHeaderText("Modificar código del espacio");
        codigoDialog.setContentText("Nuevo código:");

        Optional<String> nuevoCodigo = codigoDialog.showAndWait();
        if (nuevoCodigo.isPresent() && !nuevoCodigo.get().trim().isEmpty()) {
            String codigoNuevo = nuevoCodigo.get().trim();

            // Verificar que el nuevo código no exista
            if (parqueadero.buscarEspacio(codigoNuevo) != null && !codigoNuevo.equals(espacioSeleccionado.getCodigo())) {
                mostrarAlerta("Error", "Ya existe un espacio con el código " + codigoNuevo, Alert.AlertType.ERROR);
                return;
            }

            espacioSeleccionado.setCodigo(codigoNuevo);
            cargarEspacios();
            ParqueaderoStorage.guardarDatos(parqueadero);
            mostrarAlerta("Éxito", "Espacio modificado correctamente", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void handleDeshabilitarEspacio(ActionEvent event) {
        Espacio espacioSeleccionado = tablaEspacios.getSelectionModel().getSelectedItem();

        if (espacioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un espacio para deshabilitar", Alert.AlertType.ERROR);
            return;
        }

        // Confirmar acción
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("Deshabilitar espacio");
        confirmacion.setContentText("¿Está seguro de que desea deshabilitar el espacio " +
                espacioSeleccionado.getCodigo() + "?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (espacioSeleccionado.getEstado() == Estado.OCUPADO) {
                mostrarAlerta("Error", "No se puede deshabilitar un espacio ocupado", Alert.AlertType.ERROR);
                return;
            }

            parqueadero.deshavilitarEspacio(espacioSeleccionado);
            cargarEspacios();
            ParqueaderoStorage.guardarDatos(parqueadero);
            mostrarAlerta("Éxito", "Espacio deshabilitado correctamente", Alert.AlertType.INFORMATION);
        }
    }

    // ==================== GESTIÓN DE TARIFAS ====================

    @FXML
    void handleGuardarTarifas(ActionEvent event) {
        try {
            double tarifaCarro = Double.parseDouble(tarifaCarroField.getText().trim());
            double tarifaMoto = Double.parseDouble(tarifaMotoField.getText().trim());
            double tarifaBici = Double.parseDouble(tarifaBiciField.getText().trim());

            if (tarifaCarro < 0 || tarifaMoto < 0 || tarifaBici < 0) {
                throw new NumberFormatException();
            }

            // Actualizar o crear tarifas
            actualizarOCrearTarifa(TipoVehiculo.CARRO, tarifaCarro);
            actualizarOCrearTarifa(TipoVehiculo.MOTO, tarifaMoto);
            actualizarOCrearTarifa(TipoVehiculo.BICICLETA, tarifaBici);

            ParqueaderoStorage.guardarDatos(parqueadero);
            tarifasMensajeLabel.setText("Tarifas guardadas correctamente");
            tarifasMensajeLabel.setStyle("-fx-text-fill: green;");

        } catch (NumberFormatException e) {
            tarifasMensajeLabel.setText("Error: Ingrese valores numéricos válidos");
            tarifasMensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void actualizarOCrearTarifa(TipoVehiculo tipo, double valorHora) {
        Tarifa tarifaExistente = parqueadero.buscarTarifa(tipo);

        if (tarifaExistente != null) {
            tarifaExistente.setValorHora(valorHora);
        } else {
            Tarifa nuevaTarifa = new Tarifa(tipo, valorHora);
            parqueadero.crearTarifa(nuevaTarifa);
        }
    }

    // ==================== GESTIÓN DE USUARIOS AUTORIZADOS ====================

    @FXML
    void handleRegistrarUsuarioAutorizado(ActionEvent event) {
        String nombre = nombreUsuarioAutField.getText().trim();
        String identificacion = identificacionUsuarioAutField.getText().trim();
        TipoUsuario tipoUsuario = tipoUsuarioAutCombo.getValue();
        String descuentoTexto = descuentoUsuarioAutField.getText().trim();
        String placa = placaUsuarioAutField.getText().trim();

        // Validaciones
        if (nombre.isEmpty() || identificacion.isEmpty()) {
            mostrarAlerta("Error", "Nombre e identificación son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        if (tipoUsuario == null) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de usuario", Alert.AlertType.ERROR);
            return;
        }

        double descuento = 0;
        if (!descuentoTexto.isEmpty()) {
            try {
                descuento = Double.parseDouble(descuentoTexto);
                if (descuento < 0 || descuento > 100) {
                    mostrarAlerta("Error", "El descuento debe estar entre 0 y 100", Alert.AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Descuento inválido", Alert.AlertType.ERROR);
                return;
            }
        }

        // Verificar si ya existe un usuario con esa identificación
        for (UsuarioAutorizado ua : parqueadero.getUsuariosAutorizados()) {
            if (ua.getIdentificacion().equals(identificacion)) {
                mostrarAlerta("Error", "Ya existe un usuario con esa identificación", Alert.AlertType.ERROR);
                return;
            }
        }

        // Crear y registrar usuario autorizado
        Usuario nuevoUsuario = new Usuario(nombre, identificacion, tipoUsuario, descuento);
        nuevoUsuario.setPlacaVehiculo(placa);

        if (parqueadero.crearUsuarioAutorizado(nuevoUsuario)) {
            mostrarAlerta("Éxito", "Usuario autorizado registrado correctamente", Alert.AlertType.INFORMATION);
            limpiarCamposUsuarioAutorizado();
            cargarUsuariosAutorizados();
            ParqueaderoStorage.guardarDatos(parqueadero);
        } else {
            mostrarAlerta("Error", "No se pudo registrar el usuario", Alert.AlertType.ERROR);
        }
    }

    // ==================== REGISTRO DE OPERADORES ====================

    @FXML
    void handleRegistrarOperador(ActionEvent event) {
        String nombreUsuario = nombreOperadorField.getText().trim();
        String contrasena = contrasenaOperadorField.getText().trim();
        String confirmarContrasena = confirmarContrasenaOperadorField.getText().trim();
        String nombreCompleto = nombreCompletoOperadorField.getText().trim();

        // Validaciones
        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || nombreCompleto.isEmpty()) {
            mensajeRegistroOperadorLabel.setText("Todos los campos son obligatorios");
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            mensajeRegistroOperadorLabel.setText("Las contraseñas no coinciden");
            return;
        }

        if (contrasena.length() < 4) {
            mensajeRegistroOperadorLabel.setText("La contraseña debe tener al menos 4 caracteres");
            return;
        }

        // Verificar si el nombre de usuario ya existe
        for (Operador op : parqueadero.getOperadores()) {
            if (op.getNombreUsuario().equals(nombreUsuario)) {
                mensajeRegistroOperadorLabel.setText("El nombre de usuario ya existe");
                return;
            }
        }

        // Crear y registrar operador (ID temporal, se generará automático)
        int nuevoId = parqueadero.getOperadores().size() + 1;
        Operador nuevoOperador = new Operador(nuevoId, nombreCompleto, "COD" + nuevoId);
        nuevoOperador.setNombreUsuario(nombreUsuario);
        nuevoOperador.setContrasena(contrasena);

        if (parqueadero.crearOperador(nuevoOperador)) {
            mensajeRegistroOperadorLabel.setText("Operador registrado con éxito");
            mensajeRegistroOperadorLabel.setStyle("-fx-text-fill: green;");
            limpiarCamposOperador();
            ParqueaderoStorage.guardarDatos(parqueadero);
        } else {
            mensajeRegistroOperadorLabel.setText("Error al registrar operador");
            mensajeRegistroOperadorLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // ==================== CIERRE DE SESIÓN ====================

    @FXML
    void handleCerrarSesion(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Cerrar Sesión");
        confirmacion.setHeaderText("¿Está seguro de que desea cerrar sesión?");
        confirmacion.setContentText("Se guardarán todos los datos automáticamente.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            ParqueaderoStorage.guardarDatos(parqueadero);
            sesion.cerrarSesion();

            // Cerrar ventana actual y abrir login
            Stage stage = (Stage) usuarioLogueadoLabel.getScene().getWindow();
            stage.close();

            // Aquí deberías abrir la ventana de login
            // new Login().start(new Stage());
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private void limpiarCamposEspacio() {
        codigoEspacioField.clear();
        tipoEspacioCombo.getSelectionModel().clearSelection();
        estadoEspacioCombo.getSelectionModel().clearSelection();
    }

    private void limpiarCamposUsuarioAutorizado() {
        nombreUsuarioAutField.clear();
        identificacionUsuarioAutField.clear();
        tipoUsuarioAutCombo.getSelectionModel().clearSelection();
        descuentoUsuarioAutField.clear();
        placaUsuarioAutField.clear();
    }

    private void limpiarCamposOperador() {
        nombreOperadorField.clear();
        contrasenaOperadorField.clear();
        confirmarContrasenaOperadorField.clear();
        nombreCompletoOperadorField.clear();
        mensajeRegistroOperadorLabel.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}