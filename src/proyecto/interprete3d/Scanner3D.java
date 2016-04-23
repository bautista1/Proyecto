/* ,mmmmmmmmmmThe following code was generated by JFlex 1.4.3 on 23/04/16 05:20 AM */


package proyecto.interprete3d;

import java_cup.runtime.Symbol;
import proyecto.compilador.util.*;
import proyecto.interprete3d.Interprete;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 23/04/16 05:20 AM from the specification file
 * <tt>Scanner3D.lex</tt>
 */
public class Scanner3D implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\42\1\41\1\0\2\42\22\0\1\42\1\25\1\36\2\0"+
    "\1\21\2\0\1\10\1\11\1\22\1\21\1\16\1\21\1\7\1\23"+
    "\12\2\1\20\1\17\1\26\1\24\1\26\2\0\13\37\1\3\16\37"+
    "\1\14\1\0\1\15\1\0\1\40\1\0\1\33\1\37\1\32\1\37"+
    "\1\30\1\4\2\37\1\5\2\37\1\34\1\37\1\6\1\37\1\35"+
    "\1\37\1\27\1\37\1\1\1\31\5\37\1\12\1\0\1\13\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\3\2\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\2\15\1\16"+
    "\1\1\1\17\3\2\1\1\1\20\1\21\1\22\1\0"+
    "\1\23\1\2\1\24\1\0\1\21\1\17\3\2\1\0"+
    "\1\25\1\3\1\2\1\0\3\2\1\23\1\2\1\26"+
    "\3\2\1\27\1\30";

  private static int [] zzUnpackAction() {
    int [] result = new int[54];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\43\0\106\0\151\0\214\0\257\0\322\0\43"+
    "\0\43\0\43\0\43\0\43\0\43\0\43\0\43\0\43"+
    "\0\43\0\365\0\u0118\0\u0118\0\u0118\0\u013b\0\u015e\0\u0181"+
    "\0\u01a4\0\43\0\43\0\106\0\u01c7\0\u01ea\0\u020d\0\257"+
    "\0\u0230\0\u0253\0\43\0\u0276\0\u0299\0\u02bc\0\u01a4\0\43"+
    "\0\u01c7\0\u02df\0\u0302\0\u0325\0\u0348\0\u036b\0\257\0\u038e"+
    "\0\257\0\u03b1\0\u03d4\0\u03f7\0\257\0\257";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[54];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\6\1\2"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\2\21\1\22\1\23\1\24\1\25\1\26\2\6"+
    "\1\27\2\6\1\30\1\31\1\6\1\2\1\32\1\33"+
    "\44\0\1\6\1\34\4\6\20\0\7\6\1\0\2\6"+
    "\4\0\1\4\4\0\1\35\34\0\1\6\1\36\1\6"+
    "\1\37\2\6\20\0\7\6\1\0\2\6\3\0\6\6"+
    "\20\0\7\6\1\0\2\6\3\0\3\6\1\40\2\6"+
    "\20\0\7\6\1\0\2\6\24\0\1\41\1\42\43\0"+
    "\1\43\17\0\6\6\20\0\1\6\1\44\5\6\1\0"+
    "\2\6\3\0\6\6\20\0\4\6\1\45\2\6\1\0"+
    "\2\6\3\0\6\6\20\0\1\46\6\6\1\0\2\6"+
    "\2\0\10\47\2\0\24\47\1\50\4\47\2\0\1\51"+
    "\41\0\1\6\1\36\4\6\20\0\7\6\1\0\2\6"+
    "\3\0\4\6\1\52\1\6\20\0\7\6\1\0\2\6"+
    "\2\0\22\41\1\53\20\41\41\42\1\0\1\42\1\0"+
    "\1\54\5\6\20\0\7\6\1\0\2\6\3\0\6\6"+
    "\20\0\5\6\1\55\1\6\1\0\2\6\3\0\4\6"+
    "\1\56\1\6\20\0\7\6\1\0\2\6\3\0\5\6"+
    "\1\57\20\0\7\6\1\0\2\6\25\0\1\33\20\0"+
    "\6\6\20\0\2\6\1\60\4\6\1\0\2\6\3\0"+
    "\6\6\20\0\5\6\1\61\1\6\1\0\2\6\3\0"+
    "\5\6\1\62\20\0\7\6\1\0\2\6\3\0\6\6"+
    "\20\0\1\63\6\6\1\0\2\6\3\0\1\64\5\6"+
    "\20\0\7\6\1\0\2\6\3\0\5\6\1\65\20\0"+
    "\7\6\1\0\2\6\3\0\3\6\1\66\2\6\20\0"+
    "\7\6\1\0\2\6\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1050];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\5\1\12\11\10\1\2\11\1\1\1\0"+
    "\3\1\1\0\1\1\1\11\3\1\1\0\1\11\2\1"+
    "\1\0\13\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[54];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	private int linea = 0;
	private int posicion = 0;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner3D(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner3D(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 120) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 15: 
          { return new Symbol(Sym3D.opRel,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 25: break;
        case 6: 
          { return new Symbol(Sym3D.llaveI,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 26: break;
        case 11: 
          { return new Symbol(Sym3D.puntoComa,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 27: break;
        case 21: 
          { return new Symbol(Sym3D.cadena,new CompLex(yytext().substring(1,yytext().length()-1), yyline+1, yychar-posicion));
          }
        case 28: break;
        case 13: 
          { return new Symbol(Sym3D.opMat,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 29: break;
        case 9: 
          { return new Symbol(Sym3D.corcheteD,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 30: break;
        case 10: 
          { return new Symbol(Sym3D.coma,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 31: break;
        case 20: 
          { return new Symbol(Sym3D.ifS,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 32: break;
        case 19: 
          { return new Symbol(Sym3D.etiqueta,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 33: break;
        case 12: 
          { return new Symbol(Sym3D.dosPuntos,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 34: break;
        case 4: 
          { return new Symbol(Sym3D.parentesisI,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 35: break;
        case 1: 
          { Interprete.errores.add(new ErrorC("Error Lexico",  "Cadena no valida: "+yytext(), yyline+1, yychar-posicion));
          }
        case 36: break;
        case 23: 
          { return new Symbol(Sym3D.returnS,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 37: break;
        case 18: 
          { return new Symbol(Sym3D.temporal,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 38: break;
        case 24: 
          { return new Symbol(Sym3D.printf,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 39: break;
        case 16: 
          { posicion = yychar;
          }
        case 40: break;
        case 8: 
          { return new Symbol(Sym3D.corcheteI,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 41: break;
        case 14: 
          { return new Symbol(Sym3D.igual,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 42: break;
        case 5: 
          { return new Symbol(Sym3D.parentesisD,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 43: break;
        case 7: 
          { return new Symbol(Sym3D.llaveD,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 44: break;
        case 3: 
          { return new Symbol(Sym3D.numero,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 45: break;
        case 2: 
          { return new Symbol(Sym3D.id,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 46: break;
        case 22: 
          { return new Symbol(Sym3D.call,new CompLex(yytext(), yyline+1, yychar-posicion));
          }
        case 47: break;
        case 17: 
          { 
          }
        case 48: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {   return new Symbol(Sym3D.EOF,new CompLex(yytext(), yyline+1, yychar-posicion));
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
