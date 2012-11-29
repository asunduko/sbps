package org.SBPSWar.util;

import java.security.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public class EncryptionUtils
{
  private static final String KEY_STRING =
 "163-105-238-97-284-51-150-231-61";

  public static String encrypt( String source )
  {
    try
    {
      // Get our secret key
      Key key = getKey();

      // Create the cipher 
      Cipher desCipher = 
Cipher.getInstance("DES/ECB/PKCS5Padding");

      // Initialize the cipher for encryption
      desCipher.init(Cipher.ENCRYPT_MODE, key);

      // Our cleartext as bytes
      byte[] cleartext = source.getBytes();

      // Encrypt the cleartext
      byte[] ciphertext = desCipher.doFinal(cleartext);

      // Return a String representation of the cipher text
      return getString( ciphertext );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }

  public static String generateKey()
  {
    try
    {
      KeyGenerator keygen = KeyGenerator.getInstance("DES");
      SecretKey desKey = keygen.generateKey();
      byte[] bytes = desKey.getEncoded();
      return getString( bytes );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return null;
    }
  }

  public static String decrypt( String source )
  {
    try
    {
      // Get our secret key
      Key key = getKey();

      // Create the cipher 
      Cipher desCipher = 
Cipher.getInstance("DES/ECB/PKCS5Padding");

      // Encrypt the cleartext
      byte[] ciphertext = getBytes( source );

      // Initialize the same cipher for decryption
      desCipher.init(Cipher.DECRYPT_MODE, key);

      // Decrypt the ciphertext
      byte[] cleartext = desCipher.doFinal(ciphertext);

      // Return the clear text
      return new String( cleartext );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }

  private static Key getKey()
  {
    try
    {
      byte[] bytes = getBytes( KEY_STRING );
      DESKeySpec pass = new DESKeySpec( bytes ); 
      SecretKeyFactory skf = SecretKeyFactory.getInstance("DES"); 
      SecretKey s = skf.generateSecret(pass); 
      return s;
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Returns true if the specified text is encrypted, false otherwise
   */
  public static boolean isEncrypted( String text )
  {
    // If the string does not have any separators then it is not
    // encrypted
    if( text.indexOf( '-' ) == -1 )
    {
      ///System.out.println( "text is not encrypted: no dashes" );
      return false;
    }

    StringTokenizer st = new StringTokenizer( text, "-", false );
    while( st.hasMoreTokens() )
    {
      String token = st.nextToken();
      if( token.length() > 3 )
      {
        //System.out.println( "text is not encrypted: length of token greater than 3: " + token );
        return false;
      }
      for( int i=0; i<token.length(); i++ )
      {
        if( !Character.isDigit( token.charAt( i ) ) )
        {
          //System.out.println( "text is not encrypted: token is not a digit" );
          return false;
        }
      }
    }
    //System.out.println( "text is encrypted" );
    return true;
  }

  private static String getString( byte[] bytes )
  {
    StringBuffer sb = new StringBuffer();
    for( int i=0; i<bytes.length; i++ )
    {
      byte b = bytes[ i ];
      sb.append( ( int )( 0x00FF & b ) );
      if( i+1 <bytes.length )
      {
        sb.append( "-" );
      }
    }
    return sb.toString();
  }

  private static byte[] getBytes( String str )
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    StringTokenizer st = new StringTokenizer( str, "-", false );
    while( st.hasMoreTokens() )
    {
      int i = Integer.parseInt( st.nextToken() );
      bos.write( ( byte )i );
    }
    return bos.toByteArray();
  }
  
  
  public static void main( String[] args )
  {
	  String plaintext = new Integer(15).toString();
	  String encrypted =  EncryptionUtils.encrypt( plaintext );
	  System.out.println( plaintext + " = " + 
			  encrypted );
	  
	  String decrypted =  EncryptionUtils.decrypt(encrypted);
	  System.out.println( encrypted + " = " + 
			  decrypted );
	//get current date time with Calendar()
	  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	   Calendar cal = Calendar.getInstance();
	   System.out.println(dateFormat.format(cal.getTime()));
  
  }
  
  
/*  public static void main( String[] args )
  {
    if( args.length < 1 )
    {
      System.out.println( "Usage: EncryptionUtils <command> <args>" );
      System.out.println( "\t<command>: encrypt, decrypt, generate-key" );
      System.exit( 0 );
    }
    String command = args[ 0 ];
    if( command.equalsIgnoreCase( "generate-key" ) )
    {
      System.out.println( "New key: " + 
EncryptionUtils.generateKey() );
    }
    else if( command.equalsIgnoreCase( "encrypt" ) )
    {
      String plaintext = args[ 1 ];
      System.out.println( plaintext + " = " + 
EncryptionUtils.encrypt( plaintext ) );
    }
    else if( command.equalsIgnoreCase( "decrypt" ) )
    {
      String ciphertext = args[ 1 ];
      System.out.println( ciphertext + " = " + 
EncryptionUtils.decrypt( ciphertext ) );
    }
  }*/

  public static void showProviders()
  {
    try
    {
      Provider[] providers = Security.getProviders();
      for( int i=0; i<providers.length; i++ )
      {
        System.out.println( "Provider: " + 
providers[ i ].getName() + ", " + providers[ i ].getInfo() );
        for( Iterator itr = providers[ i ].keySet().iterator(); 
itr.hasNext(); )
        {
          String key = ( String )itr.next();
          String value = ( String )providers[ i ].get( key );
          System.out.println( "\t" + key + " = " + value );
        }

      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
} 
