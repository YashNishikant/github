package test;
//A Java Program to illustrate Caesar Cipher Decryption Technique
public class CaesarCipher2
{


    public static void main(String[] args)
    {

		/* NOTE:
		   The Caeser Cipher illustrates how codes work, but it is not at all a strong cipher in the computer age.
		   When you think about it, there are only 25 different ways to encrypt using a Caeser cipher. (0 or 26 don't count)
		   With a computer, you can easily just try all possibilities!   Below I make an array to hold all possibilities
		   then look through the strings for the string "the" which will appear in just about any String of some length in the
		   English language.

		*/

		String encryptedStr1 = "Oxa cx px jb j yjbbnwpna hxd vdbc wnnmb qjen j ydabn, jwm j ydabn rb kdc j ajp dwunbb hxd qjen bxvncqrwp rw rc.";
		String encryptedStr2 = "Yz, hspy T rz ez dpl, T rz ld l dtxawp dltwzc, ctrse mpqzcp esp xlde, awfxm ozhy tyez esp qzcpnldewp, lwzqe espcp ez esp czjlw xlde-splo.";
		String encryptedStr3 = "Xli xverwmxmsr mw e oiir sri, M ewwyvi csy, jvsq e wglsspqewxiv xs e wempsv, erh viuymviw e wxvsrk higsgxmsr sj Wirige erh xli Wxsmgw xs irefpi csy xs kvmr erh fiev mx.";
		String encryptedStr4 = "Patm hy bm, by lhfx hew angdl hy t lxt-vtimtbg hkwxkl fx mh zxm t ukhhf tgw lpxxi whpg max wxvdl?";
		String encryptedStr5 = "Fq fp nrfqb xp jrze xp F zxk al ql qxhb zxob lc jvpbic, tfqelrq qxhfkd zxob lc pefmp, yxonrbp, yofdp, pzellkbop, xka texq klq.";
		String encryptedStr6 = "X adkt id hpxa udgqxsstc htph, pcs apcs dc qpgqpgdjh rdphih.";
		String encryptedStr7 = "Qb abwwl wv i apizx jtmis kwzvmz, epmzm bpib bmuxmabcwca eqvl Mczwktglwv smxb cx i ewzam pwetqvo bpiv mdmz qb lql ijwcb xwwz Xict'a bwaaml kzinb.";
		String encryptedStr8 = "Mdmz ivl ivwv i jzqopb, jcb, itia, lmkmxbqdm qlmi ewctl lizb gwc bpzwcop.";
		String encryptedStr9 = "Kitt um Qapuimt.";

		String x = encryptedStr9;
		

		System.out.println("Encrypted String  :" + x);

		String possibleDecryptions [] = new String[26];

		for (int i = 0; i< 26;i++)
			possibleDecryptions[i] = encrypt(x,i);

		System.out.println("Possible Decryptions:");
		for (int i = 0; i< 26;i++)
			//if (possibleDecryptions[i].indexOf() >= 0)
				System.out.println("Shift of "+i+" - "+possibleDecryptions[i]);

    }

   public static String encrypt(String text, int s)
    {
        String result= "";
        char c;
        int charInt;
        if (s <0)
        	s = 26 + s;

        for (int i=0; i<text.length(); i++)
        {
			c = text.charAt(i);
			charInt = (int)c;
            if (Character.isUpperCase(c))
            {
				/* NOTE THE CODE BELOW COULD HAVE BEEN DONE IN LESS LINES BUT STRETCHED INTO 5 LINES TO SHOW ALL STEPS */
    			charInt = (charInt + s);       // add a shift of s
    			charInt = (charInt -65) % 26;  // get the number of chars beyond 65, if beyond end of alphabet (26 chars) restart from 0
    			charInt += 65;  			   // move this many characters beyond A (represented by 65)
                char ch = (char)charInt;	   // cast the int back to a char
                result += ch;				   // append the char to the result String
            }
            else if (Character.isLowerCase(c))
            {
               // You write the code to handle lowercase.   Copy and modify code used for uppercase
               charInt = (charInt + s);       // add a shift of s
			   charInt = (charInt -97) % 26;  // get the number of chars beyond 65, if beyond end of alphabet (26 chars) restart from 0
			   charInt += 97;  			   // move this many characters beyond A (represented by 65)
			   char ch = (char)charInt;	   // cast the int back to a char
               result += ch;				   // append the char to the result String

            }
            else
            {
				result += c;
			}
        }
        return result;
    }


}