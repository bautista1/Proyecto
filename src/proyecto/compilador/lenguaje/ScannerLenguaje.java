/* The following code was generated by JFlex 1.4.3 on 7/3/14 11:57 PM */


package proyecto.compilador.lenguaje;

import proyecto.compilador.util.ErrorC;
import proyecto.compilador.util.CompLex;
import java.util.LinkedList;
import java.util.List;
import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 7/3/14 11:57 PM from the specification file
 * <tt>ScannerLenguaje.lex</tt>
 */
public class ScannerLenguaje implements java_cup.runtime.Scanner {

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
    "\11\0\1\73\1\2\1\0\2\73\22\0\1\73\1\30\1\70\1\56"+
    "\2\0\1\53\1\67\1\40\1\41\1\3\1\32\1\34\1\33\1\26"+
    "\1\1\12\25\1\50\1\0\1\31\1\27\1\31\1\51\1\0\1\36"+
    "\7\71\1\66\2\71\1\60\2\71\1\63\1\64\1\71\1\62\1\71"+
    "\1\61\6\71\1\42\1\0\1\43\1\35\1\72\1\0\1\11\1\17"+
    "\1\21\1\24\1\7\1\10\1\20\1\22\1\14\1\71\1\45\1\12"+
    "\1\57\1\15\1\16\1\54\1\71\1\5\1\13\1\4\1\6\1\23"+
    "\1\44\1\55\1\37\1\65\1\46\1\52\1\47\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\14\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\5"+
    "\1\17\1\20\1\21\1\22\1\5\1\23\1\24\1\25"+
    "\1\26\1\1\1\27\1\5\1\1\6\5\2\1\2\30"+
    "\1\0\1\31\12\5\1\32\11\5\1\33\1\0\1\12"+
    "\1\34\1\35\2\5\1\36\1\37\2\5\1\0\6\5"+
    "\2\0\1\40\1\0\7\5\1\41\2\5\1\42\1\43"+
    "\7\5\1\44\5\5\1\0\7\5\1\45\1\46\1\47"+
    "\1\5\1\50\4\5\1\51\3\5\1\52\3\5\1\0"+
    "\13\5\1\53\1\54\1\5\1\55\1\56\2\5\1\0"+
    "\1\5\1\57\1\60\1\5\1\61\2\5\1\62\1\5"+
    "\1\63\2\5\1\64\1\0\1\65\3\5\1\66\1\5"+
    "\1\67\3\5\1\70\1\5\1\71\1\72\1\5\1\73";

  private static int [] zzUnpackAction() {
    int [] result = new int[194];
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
    "\0\0\0\74\0\170\0\74\0\264\0\360\0\u012c\0\u0168"+
    "\0\u01a4\0\u01e0\0\u021c\0\u0258\0\u0294\0\u02d0\0\u030c\0\u0348"+
    "\0\u0384\0\u03c0\0\74\0\u03fc\0\u03fc\0\u03fc\0\u0438\0\u0474"+
    "\0\74\0\74\0\u04b0\0\74\0\74\0\74\0\74\0\u04ec"+
    "\0\74\0\74\0\74\0\74\0\u0528\0\u0564\0\u05a0\0\u05dc"+
    "\0\u0618\0\u0654\0\u0690\0\u06cc\0\u0708\0\u0744\0\u0780\0\u07bc"+
    "\0\74\0\u07f8\0\u0834\0\74\0\u0870\0\u08ac\0\u08e8\0\u0924"+
    "\0\u0960\0\u099c\0\u09d8\0\u0a14\0\u0a50\0\u0a8c\0\u0168\0\u0ac8"+
    "\0\u0b04\0\u0b40\0\u0b7c\0\u0bb8\0\u0bf4\0\u0c30\0\u0c6c\0\u0ca8"+
    "\0\u0168\0\u0ce4\0\74\0\74\0\74\0\u0d20\0\u0d5c\0\74"+
    "\0\74\0\u0d98\0\u0dd4\0\u0e10\0\u0e4c\0\u0e88\0\u0ec4\0\u0f00"+
    "\0\u0f3c\0\u0f78\0\u0fb4\0\u07bc\0\74\0\u0ff0\0\u102c\0\u1068"+
    "\0\u10a4\0\u10e0\0\u111c\0\u1158\0\u1194\0\u0168\0\u11d0\0\u120c"+
    "\0\u0168\0\u0168\0\u1248\0\u1284\0\u12c0\0\u12fc\0\u1338\0\u1374"+
    "\0\u13b0\0\u0ce4\0\u13ec\0\u1428\0\u1464\0\u14a0\0\u14dc\0\u1518"+
    "\0\u1554\0\u1590\0\u15cc\0\u1608\0\u1644\0\u1680\0\u16bc\0\74"+
    "\0\u0168\0\u0168\0\u16f8\0\u0168\0\u1734\0\u1770\0\u17ac\0\u17e8"+
    "\0\u0168\0\u1824\0\u1860\0\u189c\0\u0168\0\u18d8\0\u1914\0\u1950"+
    "\0\u198c\0\u19c8\0\u1a04\0\u1a40\0\u1a7c\0\u1ab8\0\u1af4\0\u1b30"+
    "\0\u1b6c\0\u1ba8\0\u1be4\0\u1c20\0\u0168\0\u0168\0\u1c5c\0\u0168"+
    "\0\u0168\0\u1c98\0\u1cd4\0\u1d10\0\u1d4c\0\u0168\0\u0168\0\u1d88"+
    "\0\u0168\0\u1dc4\0\u1e00\0\u0168\0\u1e3c\0\u0168\0\u1e78\0\u1eb4"+
    "\0\u0168\0\u1ef0\0\u0168\0\u1f2c\0\u1f68\0\u1fa4\0\u0168\0\u1fe0"+
    "\0\74\0\u201c\0\u2058\0\u2094\0\u0168\0\u20d0\0\u0168\0\u0168"+
    "\0\u210c\0\u0168";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[194];
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
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\2\10\1\13\1\14\1\15\1\10\1\16\1\10"+
    "\1\17\1\10\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\10\1\34"+
    "\1\35\1\36\1\37\1\40\1\10\1\41\1\42\1\43"+
    "\1\44\1\45\1\46\1\47\1\10\1\50\1\10\1\51"+
    "\1\52\1\53\1\54\1\55\1\10\1\56\1\57\1\60"+
    "\1\10\1\2\1\61\75\0\1\62\1\0\1\63\23\0"+
    "\1\64\73\0\1\64\50\0\1\10\1\65\14\10\1\66"+
    "\3\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\3\10\1\67\16\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\22\10\10\0\2\10\4\0\2\10\6\0"+
    "\2\10\1\0\10\10\2\0\2\10\5\0\6\10\1\70"+
    "\13\10\10\0\2\10\4\0\2\10\6\0\1\10\1\71"+
    "\1\0\10\10\2\0\2\10\5\0\5\10\1\72\1\73"+
    "\3\10\1\74\7\10\10\0\2\10\4\0\2\10\6\0"+
    "\2\10\1\0\10\10\2\0\2\10\5\0\1\75\21\10"+
    "\10\0\2\10\4\0\1\76\1\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\4\10\1\77\4\10\1\100"+
    "\10\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\3\10\1\101\16\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\1\10\1\102\10\10\1\103\7\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\5\10\1\104\1\105\3\10\1\106\3\10"+
    "\1\107\3\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\12\10\1\110\7\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\12\10\1\111\7\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\26\0\1\22\1\112\74\0\1\113\73\0\1\64\2\0"+
    "\1\114\70\0\1\64\3\0\1\115\44\0\1\10\1\116"+
    "\20\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\16\10\1\117\3\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\53\0\1\120\74\0\1\121\24\0\1\10\1\122"+
    "\1\123\17\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\15\0\1\124\63\0\10\10"+
    "\1\125\11\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\3\10\1\126\16\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\3\10\1\127\16\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\17\10\1\130\2\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\12\10"+
    "\1\131\7\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\22\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\1\132\7\10\2\0"+
    "\2\10\1\0\2\133\1\0\71\133\40\134\2\0\26\134"+
    "\1\135\3\134\2\62\1\0\71\62\3\63\1\136\70\63"+
    "\4\0\2\10\1\137\17\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\10\10"+
    "\1\140\11\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\1\141\21\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\7\10\1\142\12\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\10\10\2\0\2\10\5\0"+
    "\1\143\21\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\6\10\1\144\13\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\12\10\1\145\7\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\1\10\1\146\20\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\1\10"+
    "\1\147\20\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\10\10\1\150\11\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\1\151\21\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\10\10\2\0\2\10\5\0"+
    "\22\10\10\0\2\10\4\0\1\152\1\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\3\10\1\153\16\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\12\10\1\154\7\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\7\10\1\155\12\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\5\10"+
    "\1\156\14\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\11\10\1\157\10\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\5\10\1\160\14\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\10\10\1\161\11\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\26\0\1\162"+
    "\52\0\1\10\1\163\13\10\1\164\4\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\10\10\1\165\11\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\10\10"+
    "\1\166\11\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\13\10\1\167\6\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\60\0\1\170\20\0\3\10\1\171\5\10"+
    "\1\172\10\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\22\10\10\0\2\10"+
    "\4\0\2\10\6\0\1\10\1\173\1\0\10\10\2\0"+
    "\2\10\5\0\15\10\1\174\4\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\10\10\2\0\2\10\5\0"+
    "\5\10\1\175\14\10\10\0\2\10\4\0\2\10\6\0"+
    "\2\10\1\0\10\10\2\0\2\10\5\0\6\10\1\176"+
    "\13\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\22\10\10\0\2\10\4\0"+
    "\2\10\6\0\1\177\1\10\1\0\10\10\2\0\2\10"+
    "\70\0\1\200\5\0\1\61\76\0\3\10\1\201\16\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\7\10\1\202\12\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\2\10\1\203\17\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\3\10"+
    "\1\204\16\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\3\10\1\205\16\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\7\10\1\137\12\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\5\10\1\100\14\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\10\10"+
    "\1\206\11\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\1\207\21\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\5\10\1\210\14\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\10\10\2\0\2\10\5\0"+
    "\6\10\1\151\13\10\10\0\2\10\4\0\2\10\6\0"+
    "\2\10\1\0\10\10\2\0\2\10\5\0\3\10\1\211"+
    "\16\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\7\10\1\212\12\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\1\213\21\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\1\10"+
    "\1\151\20\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\20\10\1\151\1\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\5\10\1\214\14\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\12\10\1\215\7\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\6\10"+
    "\1\216\13\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\17\10\1\217\2\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\6\10\1\220\13\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\55\0\1\221\23\0\11\10\1\222\10\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\3\10\1\223\16\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\1\224"+
    "\21\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\1\225\21\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\6\10\1\226\13\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\10\10"+
    "\1\227\11\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\1\10\1\230\20\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\1\10\1\231\20\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\11\10\1\232\10\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\11\10"+
    "\1\233\10\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\15\10\1\234\4\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\22\10\10\0\2\10\4\0\1\10"+
    "\1\235\6\0\2\10\1\0\10\10\2\0\2\10\5\0"+
    "\7\10\1\236\12\10\10\0\2\10\4\0\2\10\6\0"+
    "\2\10\1\0\10\10\2\0\2\10\5\0\10\10\1\237"+
    "\11\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\22\10\10\0\1\10\1\240"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\3\10\1\241\16\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\5\10"+
    "\1\242\14\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\10\10\1\243\11\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\17\0\1\244\61\0\22\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\6\10\1\245\1\10"+
    "\2\0\2\10\5\0\5\10\1\246\14\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\12\10\1\247\7\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\5\10"+
    "\1\250\14\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\12\10\1\251\7\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\14\10\1\252\5\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\10\10\1\253\11\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\11\10"+
    "\1\254\10\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\20\10\1\255\1\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\14\10\1\151\5\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\16\10\1\256\3\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\11\10"+
    "\1\257\10\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\1\260\21\10\10\0"+
    "\2\10\4\0\2\10\6\0\2\10\1\0\10\10\2\0"+
    "\2\10\5\0\15\10\1\261\4\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\10\10\2\0\2\10\6\0"+
    "\1\262\72\0\12\10\1\263\7\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\10\10\2\0\2\10\5\0"+
    "\11\10\1\264\10\10\10\0\2\10\4\0\2\10\6\0"+
    "\2\10\1\0\10\10\2\0\2\10\5\0\12\10\1\265"+
    "\7\10\10\0\2\10\4\0\2\10\6\0\2\10\1\0"+
    "\10\10\2\0\2\10\5\0\22\10\10\0\2\10\4\0"+
    "\2\10\6\0\2\10\1\0\1\266\7\10\2\0\2\10"+
    "\5\0\7\10\1\267\12\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\2\10"+
    "\1\270\17\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\3\10\1\261\16\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\1\271\73\0\14\10\1\272\5\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\11\10\1\273\10\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\10\10\1\274\11\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\3\10"+
    "\1\275\16\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\2\10\1\276\17\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\5\0\12\10\1\277\7\10\10\0\2\10"+
    "\4\0\2\10\6\0\2\10\1\0\10\10\2\0\2\10"+
    "\5\0\1\10\1\300\20\10\10\0\2\10\4\0\2\10"+
    "\6\0\2\10\1\0\10\10\2\0\2\10\5\0\6\10"+
    "\1\301\13\10\10\0\2\10\4\0\2\10\6\0\2\10"+
    "\1\0\10\10\2\0\2\10\5\0\12\10\1\302\7\10"+
    "\10\0\2\10\4\0\2\10\6\0\2\10\1\0\10\10"+
    "\2\0\2\10\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8520];
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
    "\1\0\1\11\1\1\1\11\16\1\1\11\5\1\2\11"+
    "\1\1\4\11\1\1\4\11\14\1\1\11\1\1\1\0"+
    "\1\11\25\1\1\0\3\11\2\1\2\11\2\1\1\0"+
    "\6\1\2\0\1\11\1\0\31\1\1\0\7\1\1\11"+
    "\20\1\1\0\22\1\1\0\15\1\1\0\6\1\1\11"+
    "\11\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[194];
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
  public ScannerLenguaje(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public ScannerLenguaje(java.io.InputStream in) {
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
    while (i < 156) {
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
        case 48: 
          { return new Symbol(SymLenguaje.funTexto, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 60: break;
        case 27: 
          { return new Symbol(SymLenguaje.doS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 61: break;
        case 45: 
          { return new Symbol(SymLenguaje.array, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 62: break;
        case 18: 
          { return new Symbol(SymLenguaje.corcheteD, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 63: break;
        case 7: 
          { return new Symbol(SymLenguaje.punto, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 64: break;
        case 38: 
          { return new Symbol(SymLenguaje.valBool, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 65: break;
        case 37: 
          { return new Symbol(SymLenguaje.caracter, new CompLex(yytext().substring(1,yytext().length()-1), yyline+1, yychar-posicion, yychar));
          }
        case 66: break;
        case 28: 
          { return new Symbol(SymLenguaje.incremento, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 67: break;
        case 2: 
          { return new Symbol(SymLenguaje.div, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 68: break;
        case 50: 
          { return new Symbol(SymLenguaje.returnS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 69: break;
        case 58: 
          { return new Symbol(SymLenguaje.funImprimir, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 70: break;
        case 57: 
          { return new Symbol(SymLenguaje.funPoligono, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 71: break;
        case 1: 
          { CompiladorLenguaje.errores.add(new ErrorC("Lexico", "Cadena no valida: "+yytext(), yyline+1, yychar-posicion));
          }
        case 72: break;
        case 33: 
          { return new Symbol(SymLenguaje.forS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 73: break;
        case 25: 
          { return new Symbol(SymLenguaje.igualOperador, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 74: break;
        case 55: 
          { return new Symbol(SymLenguaje.importar, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 75: break;
        case 4: 
          { return new Symbol(SymLenguaje.multi, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 76: break;
        case 9: 
          { return new Symbol(SymLenguaje.notS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 77: break;
        case 47: 
          { return new Symbol(SymLenguaje.funLinea, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 78: break;
        case 40: 
          { return new Symbol(SymLenguaje.elseS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 79: break;
        case 39: 
          { return new Symbol(SymLenguaje.thisS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 80: break;
        case 19: 
          { return new Symbol(SymLenguaje.llaveI, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 81: break;
        case 5: 
          { return new Symbol(SymLenguaje.id, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 82: break;
        case 30: 
          { return new Symbol(SymLenguaje.orS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 83: break;
        case 42: 
          { return new Symbol(SymLenguaje.funArco, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 84: break;
        case 22: 
          { return new Symbol(SymLenguaje.interrogacion, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 85: break;
        case 34: 
          { return new Symbol(SymLenguaje.tipoDato, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 86: break;
        case 41: 
          { return new Symbol(SymLenguaje.caseS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 87: break;
        case 26: 
          { return new Symbol(SymLenguaje.ifS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 88: break;
        case 16: 
          { return new Symbol(SymLenguaje.pareD, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 89: break;
        case 17: 
          { return new Symbol(SymLenguaje.corcheteI, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 90: break;
        case 51: 
          { return new Symbol(SymLenguaje.switchS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 91: break;
        case 46: 
          { return new Symbol(SymLenguaje.whileS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 92: break;
        case 44: 
          { return new Symbol(SymLenguaje.clase, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 93: break;
        case 3: 
          { posicion = yychar; /*return new Symbol(SymLenguaje.saltoLinea, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));*/
          }
        case 94: break;
        case 12: 
          { return new Symbol(SymLenguaje.menos, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 95: break;
        case 14: 
          { return new Symbol(SymLenguaje.expo, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 96: break;
        case 29: 
          { return new Symbol(SymLenguaje.decremento, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 97: break;
        case 35: 
          { return new Symbol(SymLenguaje.newS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 98: break;
        case 21: 
          { return new Symbol(SymLenguaje.dosPuntos, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 99: break;
        case 59: 
          { return new Symbol(SymLenguaje.funRectangulo, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 100: break;
        case 31: 
          { return new Symbol(SymLenguaje.andS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 101: break;
        case 54: 
          { return new Symbol(SymLenguaje.extendsS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 102: break;
        case 11: 
          { return new Symbol(SymLenguaje.mas, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 103: break;
        case 56: 
          { return new Symbol(SymLenguaje.continueS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 104: break;
        case 15: 
          { return new Symbol(SymLenguaje.pareI, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 105: break;
        case 13: 
          { return new Symbol(SymLenguaje.coma, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 106: break;
        case 8: 
          { return new Symbol(SymLenguaje.igual, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 107: break;
        case 53: 
          { return new Symbol(SymLenguaje.funLienzo, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 108: break;
        case 43: 
          { return new Symbol(SymLenguaje.breakS, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 109: break;
        case 10: 
          { return new Symbol(SymLenguaje.opComparacion, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 110: break;
        case 32: 
          { return new Symbol(SymLenguaje.cadena, new CompLex(yytext().substring(1,yytext().length()-1), yyline+1, yychar-posicion, yychar));
          }
        case 111: break;
        case 23: 
          { return new Symbol(SymLenguaje.ampersand, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 112: break;
        case 49: 
          { return new Symbol(SymLenguaje.funOvalo, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 113: break;
        case 36: 
          { return new Symbol(SymLenguaje.numDecimal, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 114: break;
        case 20: 
          { return new Symbol(SymLenguaje.llaveD, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 115: break;
        case 52: 
          { return new Symbol(SymLenguaje.ambito, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 116: break;
        case 6: 
          { return new Symbol(SymLenguaje.numEntero, new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
          }
        case 117: break;
        case 24: 
          { 
          }
        case 118: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {   return new Symbol(SymLenguaje.EOF,new CompLex(yytext(), yyline+1, yychar-posicion, yychar));
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
