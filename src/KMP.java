/*
 * @author huyunmeng@163.com
 * @version 1.0
 */
public class KMP {
	public static int getIndex(String str, String pat){
		if (str == null || pat == null || str.length() <pat.length() || pat.length() <1) {
			return -1;
		}
		char[] s=str.toCharArray();
		char[] p=pat.toCharArray();
		int si=0,pi=0;
		int[] next=getNextArray(p);
		for(int j:next){
			System.out.print(j+" ");
		}
		System.out.println("-----end----- ");
		while (si<s.length&&pi<p.length) {
			if(s[si] == p[pi]){//字符串匹配则两个指针不断向前移动
				si++;
				pi++;
			}
			else if (next[pi] == -1) {//和模式字符串的第一个字符不匹配，则指向s的字符串向前移动
				si++;
			}else {
				pi=next[pi];   //匹配失败，则重新定位模式串的该匹配字符
			}
		}
		return pi == p.length ? si-pi:-1;
	}
	public static int[] getNextArray(char[] pat){
		if (pat.length==1) {
			return new int[]{-1};
		}
		int[] next=new int[pat.length];
		next[0]=-1;
		next[1]=0;
		int pos=2;
		int cn=0;//注意cn总是记录着next[pos-1]的值
		while (pos<next.length) {
			if (pat[pos-1]==pat[cn]) {
				next[pos++]=++cn;
			}else if (cn>0) {
				cn=next[cn];
			}else {
				next[pos++]=0;
			}
		}
		return next;
	}
	public  static void main(String[] args) {
		String str="acabaabaabcacaabc";
		String pat="abaabcac";
		System.out.println(getIndex(str, pat));
	}
}
