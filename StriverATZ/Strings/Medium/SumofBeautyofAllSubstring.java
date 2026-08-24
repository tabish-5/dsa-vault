package StriverATZ.Strings.Medium;

public class SumofBeautyofAllSubstring {
    public int beautySum(String s) {
        int sum = 0;
        for(int i=0;i<s.length();i++){
            int[] freq = new int[26];
            int max = Integer.MIN_VALUE;
            for(int j=i;j<s.length();j++){
                
               freq[s.charAt(j)-'a']++;
                max = Math.max(max,freq[s.charAt(j)-'a']);
                int min = Integer.MAX_VALUE;// for duplicate values;
                for(int val : freq){
                    if(val>0){
                        min= Math.min(val,min);
                    }

                }
                sum += (max-min);
            }
        }
        return sum;
    }
}
