import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class BirthdayController {
    private VBox view;
    private ListView<String> listView;
    private TextField nameField, searchField;
    private DatePicker datePicker;
    private ObservableList<String> listItems;
    private BirthdayDAO dao = new BirthdayDAO();
    private ListView<String> todayList;

    public BirthdayController() {
        try {
            initializeUI();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize controller", e);
        }
    }

    private void initializeUI() {
        view = new VBox(10);
        view.setPadding(new Insets(10));

        Label title = new Label("Birthday List");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Initialize main list
        listItems = FXCollections.observableArrayList();
        listView = new ListView<>(listItems);
        refreshList();

        // Add birthday form
        HBox form = new HBox(10);
        nameField = new TextField();
        nameField.setPromptText("Name");
        datePicker = new DatePicker();
        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addBirthday());
        form.getChildren().addAll(nameField, datePicker, addBtn);

        // Delete button
        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> deleteSelected());

        // Search components
        searchField = new TextField();
        searchField.setPromptText("Search by name or month");
        Button searchBtn = new Button("Search");
        Button showAllBtn = new Button("Show All");
        HBox searchBox = new HBox(10, searchField, searchBtn, showAllBtn);

        searchBtn.setOnAction(e -> searchBirthdays());
        showAllBtn.setOnAction(e -> refreshList());

        // Today's birthdays section - FIXED INITIALIZATION ORDER
        Label todayLabel = new Label("Today's Birthdays:");
        todayList = new ListView<>();  // Initialize ListView first
        ObservableList<String> todayItems = FXCollections.observableArrayList();
        todayList.setItems(todayItems);  // Set empty items immediately

        // Now refresh with actual data
        refreshTodayList();

        // Assemble UI
        view.getChildren().addAll(
                title, form, listView, deleteBtn,
                searchBox, todayLabel, todayList
        );
    }

    private void addBirthday() {
        if (nameField.getText().isEmpty() || datePicker.getValue() == null) {
            showAlert("Input Error", "Name and date are required");
            return;
        }

        try {
            dao.addBirthday(new Birthday(nameField.getText(), datePicker.getValue()));
            refreshList();
            nameField.clear();
            datePicker.setValue(null);
        } catch (Exception e) {
            showAlert("Database Error", "Failed to add birthday: " + e.getMessage());
        }
    }

    private void deleteSelected() {
        int selected = listView.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            try {
                Birthday b = dao.getAllBirthdays().get(selected);
                dao.deleteBirthday(b.getId());
                refreshList();
            } catch (Exception e) {
                showAlert("Database Error", "Failed to delete birthday: " + e.getMessage());
            }
        }
    }

    private void searchBirthdays() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            refreshList();
            return;
        }

        try {
            listItems.clear();
            for (Birthday b : dao.searchByNameOrMonth(query)) {
                listItems.add(b.getName() + " - " + b.getBirthdate());
            }
        } catch (Exception e) {
            showAlert("Search Error", "Failed to search birthdays: " + e.getMessage());
        }
    }

    private void refreshList() {
        try {
            listItems.clear();
            for (Birthday b : dao.getAllBirthdays()) {
                listItems.add(b.getName() + " - " + b.getBirthdate());
            }
            refreshTodayList();
        } catch (Exception e) {
            showAlert("Database Error", "Failed to load birthdays: " + e.getMessage());
        }
    }

    private void refreshTodayList() {
        try {
            ObservableList<String> todayItems = todayList.getItems();
            todayItems.clear();
            for (Birthday b : dao.getTodaysBirthdays()) {
                todayItems.add(b.getName() + " - Happy Birthday!!!");
            }
        } catch (Exception e) {
            showAlert("Database Error", "Failed to load today's birthdays: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public VBox getView() {
        return view;
    }
}
