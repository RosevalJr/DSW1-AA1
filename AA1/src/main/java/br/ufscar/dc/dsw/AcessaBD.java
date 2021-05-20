package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {
	public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(""
                    + "jdbc:mysql://localhost:3306/SISTEMAEMPREGOS", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from EMPRESA");
            while (rs.next()) {
                System.out.print(rs.getLong("CNPJ"));
                System.out.print(", " + rs.getString("NOME"));
                System.out.print(", " + rs.getString("EMAIL"));
                System.out.print(", " + rs.getString("DESCRICAO"));
                System.out.println(", " + rs.getString("CIDADE"));
            }
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("A classe do driver de conexão não foi encontrada!");
        } catch (SQLException e) {
            System.out.println("O comando SQL não pode ser executado!");
        }
    }
}