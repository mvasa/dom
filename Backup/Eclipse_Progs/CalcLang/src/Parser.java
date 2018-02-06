/*
    This class provides a recursive descent parser of Blunt,
    creating a parse tree (which can later be translated to
    VPL code)
*/

import java.util.*;
import java.io.*;

public class Parser {

  private Lexer lex;

  public Parser( Lexer lexer ) {
    lex = lexer;
  }

  public static void main(String[] args) throws Exception
  {
    System.out.print("Enter file name: ");
    Scanner keys = new Scanner( System.in );
    String name = keys.nextLine();
    Lexer lex = new Lexer( name );
    Parser parser = new Parser( lex );

    Node root = parser.parseStatements();

    TreeViewer viewer = new TreeViewer("Parse Tree", 0, 0, 800, 500, root );

  }

  
    private Node parseStatements()
    {
      Token token = lex.getToken();  
      Node first = parseStatement();
      Node second = null;
      
      token = lex.getToken();
  //dont know what ends the statements but if this happens the statements end    
        if(token.isKind("\n")) {
        lex.putBack (token);
      }
      else{
        lex.putBack(token);
        second = parseStatements();
      }
      return new Node("statements", first, second, null, null);

      
    }

    private Node parseStatement() {
      Token token = lex.getToken();
      Node first = null;
      Node second = null;
      Node third = null;
      Node four = null;
  //String input. check for " " and indentifier at the end of string
  	if(token.matches("single","input")){
  		first = new Node(token);
  		token = lex.getToken();
  		if(token.isKind("string")){
  			second = new Node(token);
  			token = lex.getToken();
  			third = new Node(token);
  			four = null;
  			return new Node("Statement", first, second, third, null);
  		}       
      }
  //print statment
  	if(token.matches("single","print")){
  		 first = new Node(token);
  		token = lex.getToken();
  		//check if the next thing is an expression and call parseExpression if it is
  		second = parseExpression();
  		third = null;
  		four = null;		
  		return new Node("Statement", first, second, null, null);
  	}
  	
  //identifier. only used when doing id = expression. 
  //so if there isnt a equal or expression this is wrong
  	if(token.isKind("id")){
  		first = new Node(token);
  		lex.getToken();
  		if(token.matches("single", "=")){
  			second = new Node(token);
  			lex.getToken();
  			third = parseExpression();
  			four = null;
  			return new Node("Statement", first, second, third, null);
  		}
  	}

  //just prints string. 
  //expect quotes. if no quotes error
  	if(token.matches("single", "msg")){
  		first = new Node(token);
  		lex.getToken();
  		if(token.isKind("string")){
  			second = new Node(token);
  			third = null;
  			four = null;
  			return new Node("Statement", first, second, null, null);   
  		}
  	}
  //newLine
  	if(token.isKind("\n")){
  		first = new Node(token);
  		return new Node("Statement", first, null, null, null);
  	}
  	return new Node("Statement", first, second, third, four);
    }
  private Node parseExpression(){
  //always has a term as first thing. so have to add this token
  	Node first = parseTerm();
  	Token token = lex.getToken();
  	Node second = null;
  	Node third = null;

  	if(token.isKind("+")){
  		second = new Node(token);
  		token = lex.getToken();
  		third = parseExpression();

  	}
  	else if(token.isKind("-")){
  		second = new Node(token);
  		token = lex.getToken();
  		third = parseExpression();
  	}
  	else{
  		lex.putBack(token);
  	}
  	return new Node("Expression", first, second, third, null);
  	
  }

  private Node parseTerm(){
  	Node first = parseFactor();
  	Token token = lex.getToken();
  	Node second = null;
  	Node third = null;

  	if(token.isKind("*")){
  		second = new Node(token);
  		token = lex.getToken();
  		third = parseTerm();

  	}
  	else if(token.isKind("/")){
  		second = new Node(token);
  		token = lex.getToken();
  		third = parseTerm();
  	}
  	else{
  		lex.putBack(token);
  	}
  	return new Node("Term", first, second, third, null);

  }

  private Node parseFactor(){
	  	Token token = lex.getToken();

  	if(token.isKind("num")){//says N. i think this is what it is
  		//Token token = lex.getToken();
  		Node number = new Node(token);
  	
  		return new Node("Factor", number, null, null, null);			
  	}
  
  	else if(token.matches("single", "-")){
  		//Token token = lex.getToken();
  		Node minus = new Node(token);
  		Node second = parseFactor();
  		return new Node("Factor", minus, second, null, null); 
  	}
  	
  	else if( token.matches( "single", "(" )){
  		//Token token = lex.getToken();
  		Node open = new Node(token);
  		Node second = parseExpression();
  		Node close = new Node(token);;
  		return new Node("Factor", open, second, close, null);
  	} 
  //id and id(E)
  	
  	else{//has to be identifier or identifier(E)
  	//Token token = lex.getToken();
  	Node first = new Node(token);
  	
  	if( token.matches( "single", "(" )){
  		Node open = new Node(token);
  		Node second = parseExpression();
  		Node close = new Node (token);
  		return new Node("Factor", first, open, second, close);
  	}
  	
  	else{
  		lex.putBack(token);
  		return new Node("Factor", first, null, null, null);
  	}	
  }
  }
  // check whether token is correct kind
  private void errorCheck( Token token, String kind ) {
    if( ! token.isKind( kind ) ) {
      System.out.println("Error:  expected " + token + " to be of kind " + kind );
      System.exit(1);
    }
  }

  // check whether token is correct kind and details
  private void errorCheck( Token token, String kind, String details ) {
    if( ! token.isKind( kind ) || ! token.getDetails().equals( details ) ) {
      System.out.println("Error:  expected " + token + " to be kind=" + kind + " and details=" + details );
      System.exit(1);
    }
  }

  

}