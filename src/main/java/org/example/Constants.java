package org.example;

public enum Constants {

    DRIVER_NAME {
        public String toString() {
            return "org.sqlite.JDBC";
        }
    },
    DB_PATH {
        public String toString() {
            return "jdbc:sqlite:C:/Users/user/Desktop/Park.db";
        }
    },
    FIND_ALL_QUERY {
        public String toString() {
            return "SELECT * FROM plants";
        }
    },
    INSERT_QUERY {
        
        public String toString() {
            
            return "INSERT INTO plants (name,age,is_trimmed,is_sick) VALUES ('%s', %d, %d, %d);";
        }
    },
    DELETE_QUERY {
        public String toString() {
            return "DELETE FROM plants WHERE id=%d";
        }
    },
    FIND_BY_ID_QUERY {
        public String toString() {
            return "SELECT * FROM plants WHERE id=%d";
        }
    },
    UPDATE_QUERY {
        public String toString() {
            return "UPDATE plants SET name = '%s', age = %d, is_trimmed = %d, is_sick = %d WHERE id=%d;";
        }
    }





}
