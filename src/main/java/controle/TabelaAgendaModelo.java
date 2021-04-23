package controle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TabelaAgendaModelo extends AbstractTableModel {

    private List<Contato> lista=new ArrayList<Contato>();

    public TabelaAgendaModelo() {
    }

    public Contato getContato(int linha) {
        return lista.get(linha);
    }
    
    @Override
    public int getRowCount() {
        if(lista!=null) return lista.size();
        else return 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: 
                return "Nome";
            case 1:
                return "Telefone";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contato contato=lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return contato.getNome();
            case 1:
                return contato.getTelefone();
            default:
                return null;
        }
    }

    public void remove(int linha) {
        lista.remove(linha);
        fireTableDataChanged();
    }

    public void setContato(Contato contatoEditado, int linha) {
        lista.set(linha, contatoEditado);
        fireTableDataChanged();
    }
    
    public void adicionarContato(Contato contato) {
        lista.add(contato);
        fireTableDataChanged();
    }

    void carregarContatos() {
       // FileInputStream fis = null;
       Statement stmt = null;
       ResultSet rs = null;
        Connection conexao = null;
        try{
             Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
             e.printStackTrace();
        }
        try {/*
            fis = new FileInputStream("agenda.ser"); //FileInputStream("agenda.ser") Cria um FileInputStream abrindo uma conexão com um arquivo real, o arquivo nomeado pelo nome do caminho no sistema de arquivos. 
            ObjectInputStream ois = new ObjectInputStream(fis); //Cria um ObjectInputStream que lê a partir do InputStream especificado. Um cabeçalho de fluxo de serialização é lido do fluxo e verificado. 
            lista = (ArrayList<Contato>) ois.readObject(); //deserializa para um objeto novamente
            fireTableDataChanged();*/
           final String JDBC_URL= "jdbc:mysql://localhost:3308/agendatel";
           
             conexao = DriverManager.getConnection(JDBC_URL,"root",""); //
            stmt = conexao.createStatement(); //
             rs = stmt.executeQuery("select Nome,Telefone from contatos");              
             while(rs.next()){
           String nome = rs.getString("Nome");
           String telefone = rs.getString("Telefone");
            lista.add(new Contato(nome, telefone));          
             }
              fireTableDataChanged();
            
        } catch (SQLException e) {
            e.printStackTrace();System.exit(-1);
            JOptionPane.showMessageDialog(null, "Erro de conexão.");
        }        
        
        finally {
            try {
                //fis.close();
                conexao.close();
                stmt.close();
                rs.close();
                
            } catch (SQLException e) {
                //catch (IOException ex)
                e.printStackTrace();System.exit(-1);
                Logger.getLogger(TabelaAgendaModelo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void persistirContatos() 
    {
        Statement stmt = null;
       ResultSet rs = null;
        Connection conexao = null;
        String JDBC_URL = null;
        try{
             Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            /*
            FileOutputStream fos = new FileOutputStream("agenda.ser"); // FileOutputStream("agenda.ser") Cria um fluxo de saída de arquivo para gravar no arquivo com o nome especificado.
            ObjectOutputStream oos=new ObjectOutputStream(fos); //ObjectOutputStream(fos) método construtor recebe um FileOutputStream, que indica o arquivo onde o objeto será persistido
            oos.writeObject(lista); // writeObject(g1) serializa o objeto q será persistido no arquivo "agenda.ser"
            oos.close();*/
              JDBC_URL= "jdbc:mysql://localhost:3308/agendatel";    
             
             conexao = DriverManager.getConnection(JDBC_URL,"root",""); //
            stmt = conexao.createStatement(); //
            
            for(Contato contato: lista){                
                
                stmt.executeQuery("insert into contatos values('"+contato.getNome()+"','"+contato.getTelefone()+")");   
            }                      
             
                   
        } catch (SQLException e) {
            //catch (IOException ex)
             e.printStackTrace();System.exit(-1);
            JOptionPane.showMessageDialog(null, "Erro de conexão.");
        }
         finally 
        {
            try {
                //fis.close();
                conexao.close();
                stmt.close();               
                
            } catch (SQLException e) {
                //catch (IOException ex)
                e.printStackTrace();System.exit(-1);
                Logger.getLogger(TabelaAgendaModelo.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }
    

}