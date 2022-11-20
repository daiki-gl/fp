package com.finalproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.finalproject.SubjectsTbl.AllSubjectsModel;
import com.finalproject.TeacherList.TeacherData;
import com.finalproject.TeacherList.TeacherModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TeacherListController implements Initializable {

    // For editing modal(List Page)
    TextField editName = new TextField();
    PasswordField editPassword = new PasswordField();
    DatePicker editHireDate = new DatePicker();
    ComboBox<String> editSubject1;
    ComboBox<String> editSubject2;
    ComboBox<String> editSubject3;

    // To input general data(List Page)
    @FXML
    private TextField name;
    @FXML
    private DatePicker hireDate;
    @FXML
    private PasswordField password;

    // Research Tab
    @FXML
    private TextField searchName;
    @FXML
    private DatePicker searchHireDate;
    @FXML
    private Button serachByNameBtn;
    @FXML
    private Button serachByDateBtn;
    @FXML
    private Button serachByEngBtn;
    @FXML
    private Button serachByMathBtn;
    @FXML
    private Button serachBySciBtn;
    @FXML
    private Button serachBySocBtn;
    @FXML
    private Button showEveryTeacherBtn;
    @FXML
    private Hyperlink logout;

    // Columns for list(List Page)
    @FXML
    private TableView<TeacherData> teacherDataTableView;
    @FXML
    private TableColumn<TeacherData, String> idColumn;
    @FXML
    private TableColumn<TeacherData, String> nameColumn;
    @FXML
    private TableColumn<TeacherData, String> hireDateColumn;
    @FXML
    private TableColumn<TeacherData, String> subject1Column;
    @FXML
    private TableColumn<TeacherData, String> subject2Column;
    @FXML
    private TableColumn<TeacherData, String> subject3Column;

    // Columns for research
    @FXML
    private TableView<TeacherData> teacherDataTableSearch;
    @FXML
    private TableColumn<TeacherData, String> idColumnSearch;
    @FXML
    private TableColumn<TeacherData, String> nameColumnSearch;
    @FXML
    private TableColumn<TeacherData, String> hireDateColumnSearch;
    @FXML
    private TableColumn<TeacherData, String> subject1ColumnSearch;
    @FXML
    private TableColumn<TeacherData, String> subject2ColumnSearch;
    @FXML
    private TableColumn<TeacherData, String> subject3ColumnSearch;
    @FXML
    private Label searchMsg;
    @FXML
    private Label loginName;

    // Action Buttons(List Page)
    @FXML
    private Button addTeacherBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button deleteTeacherBtn;
    @FXML
    private Button updateTeacherBtn;
    @FXML
    private Label errorMsg;

    // To input Subject 1 to 3(List Page)
    @FXML
    private ComboBox<String> subject1Box;
    @FXML
    private ComboBox<String> subject2Box;
    @FXML
    private ComboBox<String> subject3Box;

    // To get each subjects tables' subject_id(List Page)
    int englishId;
    int mathId;
    int scienceId;
    int socialStudiesId;

    // To manipulate row data(List Page)
    public TableView<TeacherData> getTeacherDataTableView() {
        return teacherDataTableView;
    }

    // search
    public TableView<TeacherData> getTeacherDataTableSearch() {
        return teacherDataTableView;
    }

    ObservableList<TeacherData> tmpSearchData;

    // To open modal(List Page)
    Dialog<ButtonType> dialog = null;
    Alert alert = new Alert(AlertType.NONE);

    // instantiate a model(List Page)
    TeacherModel teacherModel = null;
    AllSubjectsModel allSubjectsModel = null;
    private String editIdString;
    private String editNameString;
    private String editPasswordString;
    private String editSubject1String;
    private String editSubject2String;
    private String editSubject3String;
    ObservableList<String> setSubjectList;

    LoginController lController = new LoginController();
    String loginUserId;
    ObservableList<TeacherData> loginUserData;
    // String[] loginData;
    ArrayList<String> loginData = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.teacherModel = new TeacherModel();
        this.allSubjectsModel = new AllSubjectsModel();
        this.loadTeacherData();
        this.loadTeacherDataSearch(teacherModel.getTeachers());

        loginUserId = lController.getLoinUserId();

        loadLoginUserData();
        loginName.setText("User Name: " + editNameString);

        // Initialize Combo box data(List Page)
        setSubjectList = FXCollections.observableArrayList(allSubjectsModel.getSubjectsName());

        getEachSubjectTablesId();

    }

    // load data and show everything(Both Page)
    @FXML
    public void loadTeacherData() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("name"));
        this.hireDateColumn.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("hireDate"));
        this.subject1Column.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("subject1"));
        this.subject2Column.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("subject2"));
        this.subject3Column.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("subject3"));
        this.teacherDataTableView.setItems(teacherModel.getTeachers());
        errorMsg.setText(null);

    }

    // **************************************
    // load data and show everything
    @FXML
    public void loadTeacherDataSearch(ObservableList<TeacherData> data) {
        this.idColumnSearch.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("id"));
        this.nameColumnSearch.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("name"));
        this.hireDateColumnSearch.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("hireDate"));
        this.subject1ColumnSearch.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("subject1"));
        this.subject2ColumnSearch.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("subject2"));
        this.subject3ColumnSearch.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("subject3"));
        this.teacherDataTableSearch.setItems(data);
        errorMsg.setText(null);
    }

    @FXML
    public void showEveryData(ActionEvent event) {
        this.loadTeacherDataSearch(teacherModel.getTeachers());
        this.searchMsg.setText("Search All Teachers");
        this.clearFields(null);
    }

    @FXML
    public void searchByName(ActionEvent event) {
        tmpSearchData = teacherModel.searchTeachersByName(this.searchName.getText());
        this.loadTeacherDataSearch(tmpSearchData);
        this.searchMsg.setText("Search By Name: " + this.searchName.getText());
        this.clearFields(null);
    }

    @FXML
    public void searchByDate(ActionEvent event) {
        tmpSearchData = teacherModel.searchTeachersByDate(this.searchHireDate);
        this.loadTeacherDataSearch(tmpSearchData);
        this.searchMsg.setText("Search By Date: " + this.searchHireDate.getValue());
        this.clearFields(null);
    }

    @FXML
    public void searchByEng(ActionEvent event) {
        tmpSearchData = teacherModel.searchTeachersBySubject("english");
        this.loadTeacherDataSearch(tmpSearchData);
        this.searchMsg.setText("Search By English");
    }

    @FXML
    public void searchBySci(ActionEvent event) {
        tmpSearchData = teacherModel.searchTeachersBySubject("science");
        this.loadTeacherDataSearch(tmpSearchData);
        this.searchMsg.setText("Search By Science");
    }

    @FXML
    public void searchBySoci(ActionEvent event) {
        tmpSearchData = teacherModel.searchTeachersBySubject("socialstudies");
        this.loadTeacherDataSearch(tmpSearchData);
        this.searchMsg.setText("Search By Social Studies");
    }

    @FXML
    public void searchByMath(ActionEvent event) {
        tmpSearchData = teacherModel.searchTeachersBySubject("math");
        this.loadTeacherDataSearch(tmpSearchData);
        this.searchMsg.setText("Search By Mathematics");
    }
    // ***************************************

    // Get each table's subject_id(List Page)
    // e.g english_tbl: subject_id = 2
    public void getEachSubjectTablesId() {
        String getEnglishIdQuery = "SELECT subject_id FROM english_tbl";
        englishId = teacherModel.getEachSubjectId(getEnglishIdQuery);

        String getMathIdQuery = "SELECT subject_id FROM math_tbl";
        mathId = teacherModel.getEachSubjectId(getMathIdQuery);

        String getScienceIdQuery = "SELECT subject_id FROM science_tbl";
        scienceId = teacherModel.getEachSubjectId(getScienceIdQuery);

        String getSocialStudiesIdQuery = "SELECT subject_id FROM socialstudies_tbl";
        socialStudiesId = teacherModel.getEachSubjectId(getSocialStudiesIdQuery);
    }

    // Execute sql statement and add teacher_id into specific subject_tbl
    // If I use solid number, "if statement" can change to "switch"
    public void addEachSubjectTable(int subject_id, int teacher_id) {
        String query = null;
        if (subject_id == englishId) {
            query = "INSERT INTO english_tbl (subject_id, teacher_id) VALUES (2, ?)";
        } else if (subject_id == mathId) {
            query = "INSERT INTO math_tbl (subject_id, teacher_id) VALUES (3, ?)";
        } else if (subject_id == scienceId) {
            query = "INSERT INTO science_tbl (subject_id, teacher_id) VALUES (4, ?)";
        } else if (subject_id == socialStudiesId) {
            query = "INSERT INTO socialstudies_tbl (subject_id, teacher_id) VALUES (5, ?)";
        } else {
            return;
        }
        teacherModel.executeEachTable(teacher_id, query);
    }

    // Update with Modal(List Page)
    // create modal
    private void createModal() {

        dialog = new Dialog<ButtonType>();

        dialog.getDialogPane().getStylesheets().add("file:src/main/resources/com/finalproject/application.css");

        dialog.setTitle("Edit Teacher Data<User ID:" + loginUserId + ">");
        ButtonType editModalButton = new ButtonType("Edit", ButtonData.OK_DONE);
        ButtonType cancellModalButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);

        // set the content
        GridPane gridPane = new GridPane();
        gridPane.setId("ParentGridpane");
        gridPane.setStyle("-fx-font-family: arial");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(40, 50, 30, 50));

        editName.setText(editNameString);
        editPassword.setText(editPasswordString);

        gridPane.add(new Label("Name"), 0, 0);
        gridPane.add(editName, 1, 0);

        gridPane.add(new Label("Password"), 0, 1);
        gridPane.add(editPassword, 1, 1);

        gridPane.add(new Label("Hire Date"), 0, 2);
        gridPane.add(editHireDate, 1, 2);

        gridPane.add(new Label("Subject1(now):"), 0, 3);
        gridPane.add(new Label(editSubject1String), 1, 3);
        gridPane.add(editSubject1 = new ComboBox<>(setSubjectList), 1, 4);

        gridPane.add(new Label("Subject2(now):"), 0, 5);
        gridPane.add(new Label(editSubject2String), 1, 5);
        gridPane.add(editSubject2 = new ComboBox<>(setSubjectList), 1, 6);

        gridPane.add(new Label("Subject3(now):"), 0, 7);
        gridPane.add(new Label(editSubject3String), 1, 7);
        gridPane.add(editSubject3 = new ComboBox<>(setSubjectList), 1, 8);

        Label waringMsg1 = new Label("*Do not select more than two of the same subject.");
        Label waringMsg2 = new Label("*If you want to clear subject, please choose \"Clear\".");
        waringMsg1.setId("warning");
        waringMsg2.setId("warning");
        gridPane.add(waringMsg1, 1, 11);
        gridPane.add(waringMsg2, 1, 12);

        dialog.getDialogPane().setContent(gridPane);

        dialog.getDialogPane().getButtonTypes().add(editModalButton);
        dialog.getDialogPane().getButtonTypes().add(cancellModalButton);
    }

    // When Edit data and if there's an subject duplication(List Page)
    private void alertModal() {

        dialog = new Dialog<ButtonType>();

        dialog.getDialogPane().getStylesheets().add("file:src/main/resources/com/finalproject/application.css");

        dialog.setTitle("--- Error ---");
        ButtonType doneButton = new ButtonType("Close", ButtonData.OK_DONE);

        // set the content
        GridPane gridPane = new GridPane();
        gridPane.setId("ParentGridpane");
        gridPane.setStyle("-fx-font-family: arial");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(40, 50, 30, 50));

        Label waringMsg = new Label("*There are duplication. Please check and try again.\nCouldn't edit your data.");
        waringMsg.setId("warning");
        gridPane.add(waringMsg, 0, 1);

        dialog.getDialogPane().setContent(gridPane);

        dialog.getDialogPane().getButtonTypes().add(doneButton);
    }

    // Check duplication of subject(List Page)
    public boolean checkDuplication(String sub1, String sub2, String sub3) {
        String subs[] = { sub1, sub2, sub3 };
        Set set = new HashSet();

        for (String duplicateCheck : subs) {
            if (duplicateCheck != null && !duplicateCheck.equals("Clear") && !set.add(duplicateCheck)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> loadLoginUserData() {
        teacherModel.getLogindata(loginUserId);
        this.loginData.addAll(teacherModel.getLogindata(loginUserId));
        this.editNameString = this.loginData.get(0);
        this.editPasswordString = this.loginData.get(1);
        this.editSubject1String = this.loginData.get(3);
        this.editSubject2String = this.loginData.get(4);
        this.editSubject3String = this.loginData.get(5);
        System.out.println("LoginData(Controller):" + loginData);
        System.out.println("Length:" + loginData.size());
        return loginData;
    }

    // Edit teacher data(List Page)
    @FXML
    private void editTeacher(ActionEvent event) {

        this.loadLoginUserData();
        // call the modal
        this.createModal();

        // call model
        dialog.showAndWait().ifPresent(response -> {
            if (response.getButtonData().equals(ButtonData.OK_DONE)) {

                String addSubject1 = editSubject1.getValue();
                String addSubject2 = editSubject2.getValue();
                String addSubject3 = editSubject3.getValue();

                // check duplicate
                if (checkDuplication(addSubject1, addSubject2, addSubject3)) {
                    ArrayList<String> deleteSubjects = teacherModel.getSubjectData(loginUserId);

                    // check hire date
                    LocalDate setHireDate;
                    if (editHireDate.getValue() == null) {
                        String originalDate = loginData.get(2);
                        if (originalDate == null) {
                            originalDate = "1999-01-01";
                        }
                        setHireDate = LocalDate.parse(originalDate);
                    } else {
                        setHireDate = editHireDate.getValue();
                    }

                    // Clear subject
                    if (addSubject1 == null) {
                        addSubject1 = deleteSubjects.get(0);
                    } else if (addSubject1.equals("Clear")) {
                        addSubject1 = "";
                    }
                    if (addSubject2 == null) {
                        addSubject2 = deleteSubjects.get(1);
                    } else if (addSubject2.equals("Clear")) {
                        addSubject2 = "";
                    }
                    if (addSubject3 == null) {
                        addSubject3 = deleteSubjects.get(2);
                    } else if (addSubject3.equals("Clear")) {
                        addSubject3 = "";
                    }

                    // Delete Subject data First
                    for (String deleteSubject : deleteSubjects) {
                        if (teacherModel.checkSubject(deleteSubject) != 0) {
                            deleteEachSubjectTable(teacherModel.checkSubject(deleteSubject),
                                    Integer.parseInt(loginUserId));
                        }
                    }

                    // Edit Teacher_tbl data
                    teacherModel.editLoginTeacher(
                            loginUserId, editName.getText(), Date.valueOf(setHireDate),
                            editPassword.getText(), addSubject1, addSubject2, addSubject3);

                    // Add New Subject Data
                    ArrayList<String> addSubjects = teacherModel.getSubjectData(loginUserId);
                    for (String addSubject : addSubjects) {
                        if (teacherModel.checkSubject(addSubject) != 0) {
                            addEachSubjectTable(teacherModel.checkSubject(addSubject),
                                    Integer.parseInt(loginUserId));
                        }
                    }

                } else {
                    alertModal();
                    dialog.showAndWait().ifPresent(res -> {
                        this.clearFields(null);
                    });
                }
                loginData.removeAll(loginData);
                this.loadTeacherData();
                this.loadTeacherDataSearch(teacherModel.getTeachers());
                this.loginName.setText("User Name: " + editName.getText());
                this.clearFields(null);
            }
        });
    }

    // Execute sql statement and add teacher_id into specific subject_tbl(List Page)
    // If I use solid number, "if statement" can change to "switch"
    public void deleteEachSubjectTable(int subject_id, int teacher_id) {
        String query = null;
        if (subject_id == englishId) {
            // "DELETE FROM teachers_tbl WHERE teacher_id = ?";
            query = "DELETE FROM english_tbl WHERE teacher_id = ?";
        } else if (subject_id == mathId) {
            query = "DELETE FROM  math_tbl WHERE teacher_id = ?";
        } else if (subject_id == scienceId) {
            query = "DELETE FROM  science_tbl WHERE teacher_id = ?";
        } else if (subject_id == socialStudiesId) {
            query = "DELETE FROM  socialstudies_tbl WHERE teacher_id = ?";
        } else {
            return;
        }
        teacherModel.executeEachTable(teacher_id, query);
    }

    // clear fields
    @FXML
    private void clearFields(ActionEvent event) {
        this.searchName.setText("");
        this.searchHireDate.setValue(null);
        errorMsg.setText(null);
    }

    @FXML
    public void Logout(ActionEvent event) {
        Stage stage = (Stage) this.logout.getScene().getWindow();
        stage.close();
        loginPage();
    }

    public void loginPage() {
        Stage loginStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));

            loginStage.setScene(scene);
            loginStage.setTitle("Login Page");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
