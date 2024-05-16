package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Computador;

public class ComputadorDAO {
    
    static String url = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "He211204@";
    
    public static boolean salvar(Computador objComputador){
        String erro = "Falha de conexão!";
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //carregar driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //conexao com banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando de inserir
            PreparedStatement comandoSQL = conexao.prepareStatement(
                    "INSERT INTO computador (processador, hd, marca) VALUES (?,?,?);"
            );
            
            comandoSQL.setString(1, objComputador.getProcessador());
            comandoSQL.setString(2, objComputador.getHD());
            comandoSQL.setString(3, objComputador.getMarca());
            
            //Executar
            int linhasAfetadas = comandoSQL.executeUpdate();
            
            if(linhasAfetadas >0){
                retorno = true;
            }
        
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, erro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, erro);
        } finally {
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return retorno;
    }
    
    public static ArrayList<Computador> listar(){
        ArrayList<Computador> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            //carregar driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //conexao com banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando de inserir
            PreparedStatement comandoSQL = conexao.prepareStatement(
                    "SELECT * FROM computador"
            );
            
            //executar o comando
            rs = comandoSQL.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    
                    int id = rs.getInt("idComputador");
                    String processador = rs.getString("processador");
                    String hd = rs.getString("hd");
                    String marca = rs.getString("marca");
                    
                    Computador computador = new Computador(hd, processador, id, marca);
                    listaRetorno.add(computador);
                    
                    
                }
            } 
            
        } catch (Exception e) {
            listaRetorno = null;
        } finally {
            
            if(conexao != null){
                
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return listaRetorno;
    }
    
    public static boolean alterar(Computador objComputador){
        String erro = "Falha de conexão!";
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //carregar driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //conexao com banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando de inserir
            PreparedStatement comandoSQL = conexao.prepareStatement(
                    "UPDATE computador SET processador = ?, hd = ? WHERE idComputador = ?;"
            );
            
            comandoSQL.setString(1, objComputador.getProcessador());
            comandoSQL.setString(2, objComputador.getHD());
            comandoSQL.setInt(3, objComputador.getId());
            
            //Executar
            int linhasAfetadas = comandoSQL.executeUpdate();
            
            if(linhasAfetadas >0){
                retorno = true;
            }
        
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, erro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, erro);
        } finally {
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return retorno;
    }
    
    public static boolean excluir(int idExcluir){
        String erro = "Falha de conexão!";
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //carregar driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //conexao com banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando de inserir
            PreparedStatement comandoSQL = conexao.prepareStatement(
                    "DELETE FROM computador WHERE idComputador = ?;"
            );
            
            comandoSQL.setInt(1, idExcluir);
            
            //Executar
            int linhasAfetadas = comandoSQL.executeUpdate();
            
            if(linhasAfetadas >0){
                retorno = true;
            }
        
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, erro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, erro);
        } finally {
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return retorno;
    }
    
    public static ArrayList<Computador> listarFiltro(String filtro){
        ArrayList<Computador> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            //carregar driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //conexao com banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando de inserir
            PreparedStatement comandoSQL = conexao.prepareStatement(
                    "SELECT idComputador, processador, hd, marca FROM computadores WHERE processador LIKE ?"
            );
            comandoSQL.setString(1, String.format("%s%s%s", "%", filtro, "%"));
            
            //executar o comando
            rs = comandoSQL.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    
                    int id = rs.getInt("idComputador");
                    filtro = rs.getString("processador");
                    String hd = rs.getString("hd");
                    String marca = rs.getString("marca");
                    
                    Computador computador = new Computador(hd, filtro, id, marca);
                    listaRetorno.add(computador);
                    
                    
                }
            } 
            
        } catch (Exception e) {
            listaRetorno = null;
        } finally {
            
            if(conexao != null){
                
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return listaRetorno;
    }
}
