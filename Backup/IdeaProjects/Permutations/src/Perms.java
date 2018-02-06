/*
  demo code to generate
  all permutations by
  simulating the tree of
  possibilities with method
  calls

  (can only go up to 26 symbols)
*/

import java.util.Scanner;

public class Perms {

    private static char[] symbols;

    public static void main(String[] args) {
        System.out.print("enter n: ");
        int n = new Scanner( System.in ).nextInt();

        // generate a..z for convenience
        symbols = new char[n];
        for( int k=0; k<n; k++ )
            symbols[k] = (char) ('a' + k);

        generate( n, "" );

    }



    // given number of symbols n and the
    // word generated at this conceptual node
    private static void generate( int n, String s ) {

        if( s.length() == n )
            System.out.println( s );  // have reached a leaf
        else {
            // call generate with all possible single symbol extensions to s
            for( int k=0; k<symbols.length; k++ ) {
                if( ! s.contains( "" + symbols[k] ) )
                    generate( n, s+symbols[k] );
            }
        }
    }

}