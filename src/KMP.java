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
			if(s[si] == p[pi]){//�ַ���ƥ��������ָ�벻����ǰ�ƶ�
				si++;
				pi++;
			}
			else if (next[pi] == -1) {//��ģʽ�ַ����ĵ�һ���ַ���ƥ�䣬��ָ��s���ַ�����ǰ�ƶ�
				si++;
			}else {
				pi=next[pi];   //ƥ��ʧ�ܣ������¶�λģʽ���ĸ�ƥ���ַ�
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
		int cn=0;//ע��cn���Ǽ�¼��next[pos-1]��ֵ
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
