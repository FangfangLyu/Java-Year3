import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

//javac -cp sqlite-jdbc-3.23.1.jar; Car.java
import java.net.InetSocketAddress;
import java.util.Map;
import java.sql.*;
public class Car {

    public static void main(String[] args) throws IOException {
        int port = 8500;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		
		Database db= new Database("jdbc:sqlite:Flyu.db");
		String query="select * from ";
		server.createContext("/carInfo", new RouteHandler(db,query+"carInfo;"));
		server.createContext("/Sellers", new RouteHandler(db,query+"SellerInfo;"));
		server.createContext("/Buyers", new RouteHandler(db,query+"BuyerInfo;"));
		
		server.start(); 
        System.out.println("Server is listening on port " + port );
    }    
}
