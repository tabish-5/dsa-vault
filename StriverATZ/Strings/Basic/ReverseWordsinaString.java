package StriverATZ.Strings.Basic;

import java.util.StringTokenizer;

public class ReverseWordsinaString {
    public String reverseWords(String s) {
        if(s.length()<2) return s;

        StringBuilder ans=new StringBuilder();
        StringTokenizer st=new StringTokenizer(s);

        while(st.hasMoreTokens())
        {
            ans.insert(0," ");
            ans.insert(0,st.nextToken());
        }
        return ans.toString().trim();
        
    }
}
