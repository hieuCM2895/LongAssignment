package fa.training.controller;

import fa.training.util.ConnectionManager;

import java.sql.SQLException;

public class SaleManagement {

    public static void main(String[] args) {

        try {
            ApplicationLayer app = new ApplicationLayer();
            app.showInformationDataBase();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionManager.connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
