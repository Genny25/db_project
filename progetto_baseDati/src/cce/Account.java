package cce;

import java.util.Date;

public class Account {
    int idAccount;
    String email, username, password, nome, cognome, luogo_nascita,numero_telefono;
    Date data_nascita;

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    public void setLuogo_nascita(String luogo_nascita) {
        this.luogo_nascita = luogo_nascita;
    }

    public Date getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(Date data_nascita) {
        this.data_nascita = data_nascita;
    }

    public void print() {
        System.out.printf("%5d | %-10s | %-10s | %-15s | %-28s | %-35s | %-15s | %12s | %15s\n",
                idAccount, nome, cognome, username, email, password, luogo_nascita, data_nascita,numero_telefono);
    }

    public static void printTitle() {
        System.out.printf("%5s | %-10s | %-10s | %-15s | %-28s | %-35s | %-15s | %-12s | %-15s\n",
                "ID", "Nome", "Cognome", "Username", "Email", "Password", "Luogo Nascita", "Data Nascita", "Numero telefono");
        System.out.println("------+------------+------------+-----------------+------------------------------+-------------------------------------+-----------------+--------------+--------------------");
    }

    @Override
    public String toString() {
        return "cce.Account (" + idAccount + "): Nome: " + nome + ", Cognome: " + cognome +
                ", Username: " + username + ", Email: " + email +
                ", Password: " + password + ", Luogo di nascita: " + luogo_nascita +
                ", Data di nascita: " + data_nascita + ", Numero telefono: " + numero_telefono;
    }
}
