package com.letscode;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String password = System.getenv("password");
        System.out.println(password);

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/emprestimo?user=root&password=" + password);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from emprestimo.cliente where cpf = '324432'");

        while (result.next()) {
            System.out.println(result.getInt("id"));
            System.out.println(result.getString("nome"));
            System.out.println(result.getString("cpf"));
            System.out.println(result.getFloat("salario"));
            System.out.println(result.getString("endereco"));
        }

        connection.setAutoCommit(false);
        String nome = "'Eduardo'";
        statement = connection.createStatement();
        try {
            statement.execute("insert into emprestimo.cliente(nome, cpf, salario, endereco, cidade) " +
                    "values(" + nome + " , '543453234', 1000, 'Rua abc', 'SP')");

            statement.execute("insert into emprestimo.cliente(nome, cpf, salario, endereco, cidade) " +
                    "values(" + nome + " , '2123431235', 1000, 'Rua abc', 'SP')");

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }

        statement = connection.createStatement();
        statement.execute("delete from emprestimo.cliente where id = 27");
        connection.commit();

        statement = connection.createStatement();
        statement.execute("update emprestimo.cliente set nome = 'Joao' where id = 26");
        connection.commit();

    }

}
